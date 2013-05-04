package org.mmonti.settings;

import java.util.Map;

/**
 * Configuration Source Interface. @see MapConfigurationSource and PropertiesConfigurationSource implementation.
 *
 * @author: monti.mauro
 */
public interface ConfigurationSource {

    /**
     *
     * @return
     */
    Map<String, Object> getConfiguration();

}
