package org.mmonti.settings.converters.impl;

import org.apache.commons.lang.time.FastDateFormat;
import org.mmonti.settings.converters.Converter;

import java.text.ParseException;
import java.util.Date;
import java.util.UnknownFormatConversionException;

/**
 * Custom Converter for Date objects.
 *
 * @author: monti.mauro
 */
public class DateConverterImpl implements Converter<Date> {

    private final static FastDateFormat fastDateFormat= FastDateFormat.getInstance("MM/dd/yyyy");

    @Override
    public Class<Date> getConversionType() {
        return Date.class;
    }

    @Override
    public Date apply(String instance) {
        try {
            return (Date) fastDateFormat.parseObject(instance);
        } catch (ParseException e) {
            throw new UnknownFormatConversionException("there was an error trying to convert=["+instance+"] with the specified format");
        }
    }
}
