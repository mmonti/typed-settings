package org.mmonti.settings.converters.impl;

import org.mmonti.settings.converters.Converter;

import java.util.Date;

/**
 * Some useful converters
 *
 * @author: monti.mauro
 */
public class Converters {

    /**
     *
     * @return
     */
    public static Converter<Date> dateConverter() {
        return new DateConverterImpl();
    }

    /**
     *
     * @param dateFormat
     * @return
     */
    public static Converter<Date> dateConverter(String dateFormat) {
        return new DateConverterImpl(dateFormat);
    }
}
