package org.mmonti.settings;

import javax.inject.Named;
import java.util.Date;

/**
 * Available keys.
 *
 * key=["settings.properties.string", "string"]
 * key=["settings.properties.integer", 1]
 * key=["settings.properties.long", 1L]
 * key=["settings.properties.double", 1D]
 * key=["settings.properties.float", 1F]
 * key=["settings.properties.date", "04/05/2013"]
 *
 * @author: monti.mauro
 */
public interface PropertiesInterface {

    @Named("settings.properties.string")
    String getString();

    @Named("settings.properties.integer")
    Integer getInteger();

    @Named("settings.properties.integer")
    int getPrimitiveInteger();

    @Named("settings.properties.long")
    Long getLong();

    @Named("settings.properties.long")
    long getPrimitiveLong();

    @Named("settings.properties.double")
    Double getDouble();

    @Named("settings.properties.double")
    double getPrimitiveDouble();

    @Named("settings.properties.float")
    Float getFloat();

    @Named("settings.properties.float")
    float getPrimitiveFloat();

    @Named("settings.properties.date")
    Date getDate();

    @Named("settings.inexistent")
    Object getInexistent();
    
}
