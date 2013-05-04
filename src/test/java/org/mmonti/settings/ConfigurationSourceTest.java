package org.mmonti.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mmonti.settings.impl.MapConfigurationSource;
import org.mmonti.settings.impl.PropertiesConfigurationSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test Case for ConfigurationSource interface.
 *
 * @author: monti.mauro
 */
public class ConfigurationSourceTest {

    private Map<String, Object> mapConfiguration = null;
    private Properties propertiesConfiguration = null;
    private ConfigurationSource mapConfigurationSource = null;
    private ConfigurationSource propertiesConfigurationSource = null;

    @Before
    public void setUp() throws Exception {
        mapConfiguration = new HashMap<>();
        mapConfiguration.put("settings.map.string", "string");
        mapConfiguration.put("settings.map.integer", 1);
        mapConfiguration.put("settings.map.long", 1L);
        mapConfiguration.put("settings.map.double", 1D);
        mapConfiguration.put("settings.map.float", 1F);
        mapConfiguration.put("settings.map.date", "04/05/2013");

        propertiesConfiguration = new Properties();
        propertiesConfiguration.setProperty("settings.properties.string", "string");
        propertiesConfiguration.setProperty("settings.properties.integer", "1");
        propertiesConfiguration.setProperty("settings.properties.long", "1");
        propertiesConfiguration.setProperty("settings.properties.double", "1.0");
        propertiesConfiguration.setProperty("settings.properties.float", "1.0");
        propertiesConfiguration.setProperty("settings.properties.date", "04/05/2013");

        mapConfigurationSource = new MapConfigurationSource(mapConfiguration);
        propertiesConfigurationSource = new PropertiesConfigurationSource(propertiesConfiguration);
    }

    @Test
    public void testMapConfigurationNotNull() throws Exception {
        Assert.assertNotNull(mapConfigurationSource);
    }

    @Test
    public void testPropertiesConfigurationNotNull() throws Exception {
        Assert.assertNotNull(propertiesConfigurationSource);
    }

    @Test
    public void testMapConfigurationProperties() throws Exception {
        Assert.assertNotNull(mapConfigurationSource.getConfiguration());
        Assert.assertThat(mapConfigurationSource.getConfiguration().size(), equalTo(6));
    }

    @Test
    public void testPropertiesConfigurationProperties() throws Exception {
        Assert.assertNotNull(propertiesConfigurationSource);
        Assert.assertThat(mapConfigurationSource.getConfiguration().size(), equalTo(6));
    }
}
