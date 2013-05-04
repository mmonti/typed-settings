package org.mmonti.settings.converters.impl;

import com.google.common.base.Preconditions;
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

    public static Converter<Integer> integerConverter() {
        return new Converter<Integer>() {
            @Override
            public Class<Integer> getConversionType() {
                return Integer.class;
            }

            @Override
            public Integer apply(String instance) {
                Preconditions.checkNotNull(instance, "instance argument cannot be null.");
                return Integer.valueOf(instance);
            }
        };
    }

    public static Converter<Float> floatConverter() {
        return new Converter<Float>() {
            @Override
            public Class<Float> getConversionType() {
                return Float.class;
            }

            @Override
            public Float apply(String instance) {
                Preconditions.checkNotNull(instance, "instance argument cannot be null.");
                return Float.valueOf(instance);
            }
        };
    }

    public static Converter<Double> doubleConverter() {
        return new Converter<Double>() {
            @Override
            public Class<Double> getConversionType() {
                return Double.class;
            }

            @Override
            public Double apply(String instance) {
                Preconditions.checkNotNull(instance, "instance argument cannot be null.");
                return Double.valueOf(instance);
            }
        };
    }

    public static Converter<Long> longConverter() {
        return new Converter<Long>() {
            @Override
            public Class<Long> getConversionType() {
                return Long.class;
            }

            @Override
            public Long apply(String instance) {
                Preconditions.checkNotNull(instance, "instance argument cannot be null.");
                return Long.valueOf(instance);
            }
        };
    }

    public static Converter<String> stringConverter() {
        return new Converter<String>() {
            @Override
            public Class<String> getConversionType() {
                return String.class;
            }

            @Override
            public String apply(String instance) {
                Preconditions.checkNotNull(instance, "instance argument cannot be null.");
                return String.valueOf(instance);
            }
        };
    }
}
