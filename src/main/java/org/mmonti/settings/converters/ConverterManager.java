package org.mmonti.settings.converters;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Describe your class here.
 *
 * @author: monti.mauro
 */
public class ConverterManager {

    private Map<Class<?>, Converter<?>> convertersMap = null;

    public ConverterManager() {
        this.convertersMap = new HashMap<>();
    }

    /**
     *
     * @param type
     * @param converter
     * @param <T>
     */
    public <T> void register(final Class<T> type, final Converter<T> converter) {
        this.convertersMap.put(type, converter);
    }

    /**
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Converter<?> getConverterForType(final Class<T> type) {
        Preconditions.checkNotNull(type, "type argument cannot be null.");

        if (this.convertersMap.containsKey(type)) {
            return convertersMap.get(type);
        }
        throw new UnsupportedOperationException("unknown converter for type=["+type+"]. Register a custom converter and try again.");
    }
}
