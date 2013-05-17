package org.mmonti.settings;

import com.google.common.base.Preconditions;
import org.mmonti.settings.converters.Converter;
import org.mmonti.settings.impl.MapConfigurationSource;
import org.mmonti.settings.impl.PropertiesConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Builder for Settings object.
 *
 * @author: monti.mauro
 */
public class SettingsBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SettingsBuilder.class);

    private Deque<ConfigurationSource> configurationSources = null;
    private List<Converter<?>> converters = null;

    private SettingsBuilder() {
        this.configurationSources = new ConcurrentLinkedDeque<>();
        this.converters = new ArrayList<>();
    }

    public static SettingsBuilder create() {
        return new SettingsBuilder();
    }

    /**
     *
     * @param configurationSource
     * @return
     */
    public SettingsBuilder add(final ConfigurationSource configurationSource) {
        Preconditions.checkNotNull(configurationSource, "configurationSource argument cannot be null.");

        this.configurationSources.push(configurationSource);
        return this;
    }
    /**
     *
     * @param properties
     * @return
     */
    public SettingsBuilder add(final Map<String, Object> properties) {
        Preconditions.checkNotNull(properties, "properties argument cannot be null.");

        this.configurationSources.push(new MapConfigurationSource(properties));
        return this;
    }

    /**
     *
     * @param properties
     * @return
     */
    public SettingsBuilder add(final Properties properties) {
        Preconditions.checkNotNull(properties, "properties argument cannot be null.");

        this.configurationSources.push(new PropertiesConfigurationSource(properties));
        return this;
    }

    /**
     *
     * @param converter
     * @return
     */
    public SettingsBuilder withConverter(final Converter<?> converter) {
        Preconditions.checkNotNull(converter, "converter argument cannot be null.");

        this.converters.add(converter);
        return this;
    }

    /**
     *
     * @return
     */
    public Settings build() {
        final Properties properties = new Properties();

        if (configurationSources.isEmpty()) {
            logger.warn("no configuration sources specified.");
        } else {
            while (!configurationSources.isEmpty()) {
                final ConfigurationSource configurationSource = configurationSources.pop();
                properties.putAll(configurationSource.getConfiguration());
            }
        }

        if (converters.isEmpty()) {
            logger.warn("no custom converters added. using defaults.");
            return new SettingsImpl(properties);
        }

        return new SettingsImpl(properties, converters);
    }

    public Collection<ConfigurationSource> getConfigurationSources() {
        return Collections.unmodifiableCollection(configurationSources);
    }
}
