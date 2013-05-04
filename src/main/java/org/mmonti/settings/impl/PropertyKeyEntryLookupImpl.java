package org.mmonti.settings.impl;

import org.mmonti.settings.PropertyKeyEntry;
import org.mmonti.settings.PropertyKeyEntryLookup;
import org.mmonti.settings.converters.Converter;

import java.lang.reflect.Method;
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

        initialize();
    }

    /**
     *
     */
    private void initialize() {
        if (convertersMap == null) {
            convertersMap = new HashMap<>();
        }

        convertersMap.put(String.class, new Converter<String>() {
            @Override
            public Class<String> getConversionType() {
                return String.class;
            }
            @Override
            public String apply(String instance) {
                return instance;
            }
        });

        final Converter<Integer> integerConverter = new Converter<Integer>() {
            @Override
            public Class<Integer> getConversionType() {
                return Integer.TYPE;
            }
            @Override
            public Integer apply(String instance) {
                return Integer.parseInt(instance);
            }
        };
        convertersMap.put(Integer.class, integerConverter);
        convertersMap.put(int.class, integerConverter);

        final Converter<Long> longConverter = new Converter<Long>() {
            @Override
            public Class<Long> getConversionType() {
                return Long.TYPE;
            }

            @Override
            public Long apply(String instance) {
                return Long.parseLong(instance);
            }
        };
        convertersMap.put(Long.class, longConverter);
        convertersMap.put(long.class, longConverter);

        final Converter<Float> floatConverter = new Converter<Float>() {
            @Override
            public Class<Float> getConversionType() {
                return Float.TYPE;
            }
            @Override
            public Float apply(String instance) {
                return Float.parseFloat(instance);
            }
        };
        convertersMap.put(Float.class, floatConverter);
        convertersMap.put(float.class, floatConverter);

        final Converter<Double> doubleConverter = new Converter<Double>() {
            @Override
            public Class<Double> getConversionType() {
                return Double.TYPE;
            }
            @Override
            public Double apply(String instance) {
                return Double.parseDouble(instance);
            }
        };
        convertersMap.put(Double.class, doubleConverter);
        convertersMap.put(double.class, doubleConverter);
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
        return (T) convert(propertyKeyEntry.getReturnType(), propertyKeyEntry.getValue());
    }

    /**
     *
     * @param returningType
     * @param instance
     * @param <T>
     * @return
     */
    private <T> T convert(Class<T> returningType, String instance) {
        final Converter<T> converter = (Converter<T>) this.convertersMap.get(returningType);
        if (converter == null) {
            throw new UnsupportedOperationException("unknown converter for type=["+returningType+"]. Register a custom converter and try again.");
        }
        return converter.apply(instance);
    }

}
