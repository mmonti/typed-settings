package org.mmonti.settings.impl;

import org.mmonti.settings.ConfigurationSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties configuration source.
 *
 * @author: monti.mauro
 */
public class PropertiesConfigurationSource implements ConfigurationSource {

    private Properties properties;

    public PropertiesConfigurationSource(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Object> getConfiguration() {
        final Map<String, Object> configuration = new HashMap<>();
        for (Object key : properties.keySet()) {
            configuration.put(key.toString(), properties.get(key));
        }
        return configuration;
    }
}
