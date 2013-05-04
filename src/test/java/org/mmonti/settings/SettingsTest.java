package org.mmonti.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.core.Is.is;

/**
 * Test Case for Settings class.
 *
 * @author: monti.mauro
 */
public class SettingsTest {

    private Map<String, Object> mapConfiguration = null;
    private Properties propertiesConfiguration = null;
    private SettingsBuilder settingsBuilder = SettingsBuilder.create();
    private Settings settings = null;

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

        settings = settingsBuilder.build();
    }

    @Test
    public void testSettingsNotNull() throws Exception {
        Assert.assertNotNull(settings);
    }

    @Test
    public void testSettingsConvertersEmpty() throws Exception {
        Assert.assertNotNull(settings);
        Assert.assertThat(settings.hasConverters(), is(Boolean.FALSE));
    }

    @Test
    public void testSettingsPropertiesEmpty() throws Exception {
        Assert.assertNotNull(settings);
        Assert.assertThat(settings.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void testSettingsPropertiesConvertersEmpty() throws Exception {
        settings = settingsBuilder.add(propertiesConfiguration).build();

        Assert.assertNotNull(settings);
        Assert.assertThat(settings.hasConverters(), is(Boolean.FALSE));
    }

    @Test
    public void testSettingsMapConvertersEmpty() throws Exception {
        settings = settingsBuilder.add(mapConfiguration).build();

        Assert.assertNotNull(settings);
        Assert.assertThat(settings.hasConverters(), is(Boolean.FALSE));
    }

    @Test
    public void testSettingsMapNotNull() throws Exception {
        settings = settingsBuilder.add(mapConfiguration).build();
        Assert.assertNotNull(settings);

        final MapInterface mapInterface = settings.getSettings(MapInterface.class);
        Assert.assertNotNull(mapInterface);
    }

    @Test
    public void testSettingsPropertiesNotNull() throws Exception {
        settings = settingsBuilder.add(propertiesConfiguration).build();
        Assert.assertNotNull(settings);

        final PropertiesInterface propertiesInterface = settings.getSettings(PropertiesInterface.class);
        Assert.assertNotNull(propertiesInterface);
    }

    @Test
    public void testSettingsMapEmptyNotNull() throws Exception {
        final MapInterface mapInterface = settings.getSettings(MapInterface.class);
        Assert.assertNotNull(mapInterface);
    }

    @Test
    public void testSettingsMapEmptyInterfaceEmptyNotNull() throws Exception {
        final MapInterfaceEmpty mapInterface = settings.getSettings(MapInterfaceEmpty.class);
        Assert.assertNotNull(mapInterface);
    }

    @Test
    public void testSettingsPropertiesEmptyNotNull() throws Exception {
        final PropertiesInterface propertiesInterface = settings.getSettings(PropertiesInterface.class);
        Assert.assertNotNull(propertiesInterface);
    }

    @Test
    public void testSettingsPropertiesEmptyInterfaceEmptyNotNull() throws Exception {
        final PropertiesInterfaceEmpty propertiesInterface = settings.getSettings(PropertiesInterfaceEmpty.class);
        Assert.assertNotNull(propertiesInterface);
    }

}
