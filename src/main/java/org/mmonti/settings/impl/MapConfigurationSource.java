package org.mmonti.settings.impl;

import org.mmonti.settings.ConfigurationSource;

import java.util.Map;

/**
 * Map Configuration Source.
 *
 * @author: monti.mauro
 */
public class MapConfigurationSource implements ConfigurationSource {

    private Map<String, Object> objectMap;

    /**
     *
     * @param objectMap
     */
    public MapConfigurationSource(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    @Override
    public Map<String, Object> getConfiguration() {
        return objectMap;
    }
}
