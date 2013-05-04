package org.mmonti.settings;

import org.mmonti.settings.converters.Converter;
import org.mmonti.settings.impl.MapConfigurationSource;
import org.mmonti.settings.impl.PropertiesConfigurationSource;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Builder for Settings object.
 *
 * @author: monti.mauro
 */
public class SettingsBuilder {

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
     * @param properties
     * @return
     */
    public SettingsBuilder add(final Map<String, Object> properties) {
        this.configurationSources.push(new MapConfigurationSource(properties));
        return this;
    }

    /**
     *
     * @param properties
     * @return
     */
    public SettingsBuilder add(final Properties properties) {
        this.configurationSources.push(new PropertiesConfigurationSource(properties));
        return this;
    }

    /**
     *
     * @param converter
     * @return
     */
    public SettingsBuilder withConverter(final Converter<?> converter) {
        this.converters.add(converter);
        return this;
    }

    /**
     *
     * @return
     */
    public Settings build() {
        final Properties properties = new Properties();
        while (!configurationSources.isEmpty()) {
            final ConfigurationSource configurationSource = configurationSources.pop();
            properties.putAll(configurationSource.getConfiguration());
        }

        if (converters.isEmpty()) {
            return new Settings(properties);
        }
        return new Settings(properties, converters);
    }
}
