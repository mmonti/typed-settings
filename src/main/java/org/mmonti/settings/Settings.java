package org.mmonti.settings;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;
import org.mmonti.settings.converters.Converter;
import org.mmonti.settings.impl.PropertyKeyEntryLookupImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class in charge of generate the proxy objects to retrieve and cache the properties key/values.
 *
 * @author: monti.mauro
 */
public class Settings {

    private Properties properties;
    private Map<Class, Object> cache;
    private Map<Class<?>, Converter<?>> convertersMap;

    /**
     *
     * @param properties
     */
    public Settings(final Properties properties) {
        this(properties, new ArrayList<Converter<?>>());
    }

    /**
     *
     * @param properties
     * @param converters
     */
    public Settings(final Properties properties, final List<Converter<?>> converters) {
        this.properties = properties;
        this.cache = new ConcurrentHashMap<>();
        this.convertersMap = new ConcurrentHashMap<>();

        for (final Converter<?> converter : converters) {
            convertersMap.put(converter.getConversionType(), converter);
        }
    }

    /**
     *
     * @param targetInterface
     * @param <T>
     * @return
     */
    public <T> T getSettings(Class<T> targetInterface) {
        // = check if the proxy is already in cache.
        if (cache.containsKey(targetInterface)) {
            return (T) cache.get(targetInterface);
        }

        final Map<String, PropertyKeyEntry> entryInformationHashMap = new HashMap<>();
        final Method[] methods = targetInterface.getMethods();

        for (final Method method : methods) {
            final Annotation[] annotations = method.getAnnotations();
            for (final Annotation annotation : annotations) {
                if (javax.inject.Named.class.isAssignableFrom(annotation.annotationType())) {
                    final javax.inject.Named named = (javax.inject.Named) annotation;
                    final String key = named.value();

                    entryInformationHashMap.put(method.getName(), new PropertyKeyEntry(method, key, properties.getProperty(key)));
                }
            }
        }

        final PropertyKeyEntryLookup propertyKeyEntryLookup = new PropertyKeyEntryLookupImpl(entryInformationHashMap, convertersMap);
        final TargetInterfaceInvocationHandler handler = new TargetInterfaceInvocationHandler(propertyKeyEntryLookup);
        final Object proxy = Reflection.newProxy(targetInterface, handler);

        // = adds the proxy to the cache.
        this.cache.put(targetInterface, proxy);

        return (T) proxy;
    }

    /**
     * @author mauro.monti
     */
    private class TargetInterfaceInvocationHandler extends AbstractInvocationHandler {

        private PropertyKeyEntryLookup propertyKeyEntryLookup = null;

        /**
         *
         * @param propertyKeyEntryLookup
         */
        private TargetInterfaceInvocationHandler(PropertyKeyEntryLookup propertyKeyEntryLookup) {
            this.propertyKeyEntryLookup = propertyKeyEntryLookup;
        }

        /**
         *
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            return propertyKeyEntryLookup.lookup(method);
        }
    }

}
