package org.mmonti.settings.impl;

import org.mmonti.settings.ConfigurationSource;

import java.util.Map;

/**
 * Map Configuration Source.
 *
 * @author: monti.mauro
 */
public class MapConfigurationSource implements ConfigurationSource {

    private Map<String, Object> mapConfiguration = null;

    /**
     *
     * @param mapConfiguration
     */
    public MapConfigurationSource(final Map<String, Object> mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    @Override
    public Map<String, Object> getConfiguration() {
        return mapConfiguration;
    }

    @Override
    public boolean contains(final String key) {
        return this.mapConfiguration.containsKey(key);
    }
}
