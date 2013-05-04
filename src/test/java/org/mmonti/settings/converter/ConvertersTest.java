package org.mmonti.settings.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mmonti.settings.converters.Converter;
import org.mmonti.settings.converters.impl.Converters;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test case for Converters.
 *
 * @author: monti.mauro
 */
public class ConvertersTest {

    private String stringType = null;
    private String integerType = null;
    private String longType = null;
    private String doubleType = null;
    private String floatType = null;

    @Before
    public void setUp() throws Exception {
        this.stringType = String.class.getName();
        this.integerType = "1";
        this.longType = "1";
        this.doubleType = "1.0";
        this.floatType = "1.0";
    }

    @Test
    public void testStringConverterNotNull() throws Exception {
        final Converter<String> stringConverter = Converters.stringConverter();
        Assert.assertNotNull(stringConverter);
    }

    @Test
    public void testStringConverterCheckType() throws Exception {
        final Converter<String> stringConverter = Converters.stringConverter();
        Assert.assertThat(stringConverter.getConversionType(), is(equalTo(String.class)));
    }

    @Test
    public void testStringConverterApply() throws Exception {
        final Converter<String> stringConverter = Converters.stringConverter();
        final String instance = stringConverter.apply(stringType);
        Assert.assertThat(instance, is(equalTo(stringType)));
    }

    @Test
    public void testLongConverterNotNull() throws Exception {
        final Converter<Long> converter = Converters.longConverter();
        Assert.assertNotNull(converter);
    }

    @Test
    public void testLongConverterCheckType() throws Exception {
        final Converter<Long> converter = Converters.longConverter();
        Assert.assertThat(converter.getConversionType(), is(equalTo(Long.class)));
    }

    @Test
    public void testLongConverterApply() throws Exception {
        final Converter<Long> converter = Converters.longConverter();
        final Long instance = converter.apply(longType);
        Assert.assertThat(instance, is(equalTo(1L)));
    }

    @Test
    public void testIntegerConverterNotNull() throws Exception {
        final Converter<Integer> converter = Converters.integerConverter();
        Assert.assertNotNull(converter);
    }

    @Test
    public void testIntegerConverterCheckType() throws Exception {
        final Converter<Integer> converter = Converters.integerConverter();
        Assert.assertThat(converter.getConversionType(), is(equalTo(Integer.class)));
    }

    @Test
    public void testIntegerConverterApply() throws Exception {
        final Converter<Integer> converter = Converters.integerConverter();
        final Integer instance = converter.apply(integerType);
        Assert.assertThat(instance, is(equalTo(1)));
    }

    @Test
    public void testDoubleConverterNotNull() throws Exception {
        final Converter<Double> converter = Converters.doubleConverter();
        Assert.assertNotNull(converter);
    }

    @Test
    public void testDoubleConverterCheckType() throws Exception {
        final Converter<Double> converter = Converters.doubleConverter();
        Assert.assertThat(converter.getConversionType(), is(equalTo(Double.class)));
    }

    @Test
    public void testDoubleConverterApply() throws Exception {
        final Converter<Double> converter = Converters.doubleConverter();
        final Double instance = converter.apply(doubleType);
        Assert.assertThat(instance, is(equalTo(1D)));
    }

    @Test
    public void testFloatConverterNotNull() throws Exception {
        final Converter<Float> converter = Converters.floatConverter();
        Assert.assertNotNull(converter);
    }

    @Test
    public void testFloatConverterCheckType() throws Exception {
        final Converter<Float> converter = Converters.floatConverter();
        Assert.assertThat(converter.getConversionType(), is(equalTo(Float.class)));
    }

    @Test
    public void testFloatConverterApply() throws Exception {
        final Converter<Float> converter = Converters.floatConverter();
        final Float instance = converter.apply(floatType);
        Assert.assertThat(instance, is(equalTo(1F)));
    }
}
