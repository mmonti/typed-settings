package org.mmonti.settings.converters.impl;

import com.google.common.base.Preconditions;
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

    private static FastDateFormat fastDateFormat = null;

    public DateConverterImpl() {
        this("MM/dd/yyyy");
    }

    public DateConverterImpl(String dateFormat) {
        Preconditions.checkNotNull("dateFormat argument cannot be null.");
        fastDateFormat = FastDateFormat.getInstance(dateFormat);
    }

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
