package org.mmonti.settings.converters;

/**
 * Interface to describe type converters.
 *
 * @author: monti.mauro
 */
public interface Converter<T> {

    /**
     *
     * @return
     */
    Class<T> getConversionType();

    /**
     *
     * @param instance
     * @return
     */
    T apply(String instance);

}
