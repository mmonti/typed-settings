package org.mmonti.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test case for SettingsBuilder
 *
 * @author: monti.mauro
 */
public class SettingsBuilderTest {

    private Map<String, Object> mapConfiguration = null;
    private Properties propertiesConfiguration = null;
    private SettingsBuilder settingsBuilder = null;

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
    }

    @Test
    public void testBuilderNotNull() throws Exception {
        settingsBuilder = SettingsBuilder.create();
        Assert.assertNotNull(settingsBuilder);
    }

    @Test
    public void testBuilderAddMapConfigurationMap() throws Exception {
        settingsBuilder = SettingsBuilder.create();
        Assert.assertThat(settingsBuilder.getConfigurationSources().size(), is(equalTo(0)));

        settingsBuilder.add(mapConfiguration);
        Assert.assertNotNull(settingsBuilder);
        Assert.assertThat(settingsBuilder.getConfigurationSources().size(), is(equalTo(1)));
    }

    @Test
    public void testBuilderAddPropertiesConfiguration() throws Exception {
        settingsBuilder = SettingsBuilder.create();
        Assert.assertThat(settingsBuilder.getConfigurationSources().size(), is(equalTo(0)));

        settingsBuilder.add(propertiesConfiguration);
        Assert.assertNotNull(settingsBuilder);
        Assert.assertThat(settingsBuilder.getConfigurationSources().size(), is(equalTo(1)));
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderWithConvertersNull() throws Exception {
        settingsBuilder = SettingsBuilder.create();
        settingsBuilder.withConverter(null);
    }

}
