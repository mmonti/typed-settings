package org.mmonti.settings.impl;

import org.mmonti.settings.PropertyKeyEntry;
import org.mmonti.settings.PropertyKeyEntryLookup;
import org.mmonti.settings.converters.Converter;
import org.mmonti.settings.converters.ConverterManager;
import org.mmonti.settings.converters.impl.Converters;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author: monti.mauro
 */
public class PropertyKeyEntryLookupImpl implements PropertyKeyEntryLookup {

    private Map<String, PropertyKeyEntry> propertyKeyEntryMap = null;
    private Map<Class<?>, Converter<?>> convertersMap = null;

    private ConverterManager converterManager;

    /**
     *
     * @param propertyKeyEntryMap
     * @param convertersMap
     */
    public PropertyKeyEntryLookupImpl(
            final Map<String, PropertyKeyEntry> propertyKeyEntryMap,
            final Map<Class<?>, Converter<?>> convertersMap) {

        this.propertyKeyEntryMap = propertyKeyEntryMap;
        this.convertersMap = convertersMap;

        this.converterManager = new ConverterManager();

        registerDefaultConvertions();
    }

    /**
     *
     */
    private void registerDefaultConvertions() {
        if (convertersMap == null) {
            convertersMap = new HashMap<>();
        }

        converterManager.register(String.class, Converters.stringConverter());
        converterManager.register(Integer.class, Converters.integerConverter());
        converterManager.register(int.class, Converters.integerConverter());
        converterManager.register(Long.class, Converters.longConverter());
        converterManager.register(long.class, Converters.longConverter());
        converterManager.register(Float.class, Converters.floatConverter());
        converterManager.register(float.class, Converters.floatConverter());
        converterManager.register(Double.class, Converters.doubleConverter());
        converterManager.register(double.class, Converters.doubleConverter());
        converterManager.register(Date.class, Converters.dateConverter());
    }

    /**
     *
     * @param method
     * @param <T>
     * @return
     */
    @Override
    public <T> T lookup(Method method) {
        final PropertyKeyEntry propertyKeyEntry = propertyKeyEntryMap.get(method.getName());
        return (T) converterManager.getConverterForType(propertyKeyEntry.getReturnType()).apply(propertyKeyEntry.getValue());
    }

}
