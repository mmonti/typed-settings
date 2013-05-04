package org.mmonti.settings;

import javax.inject.Named;
import java.util.Date;

/**
 * Available keys.
 *
 * key=["settings.map.string", "string"]
 * key=["settings.map.integer", 1]
 * key=["settings.map.long", 1L]
 * key=["settings.map.double", 1D]
 * key=["settings.map.float", 1F]
 * key=["settings.map.date", "04/05/2013"]
 *
 * @author: monti.mauro
 */
public interface MapInterface {

    @Named("settings.map.string")
    String getString();

    @Named("settings.map.integer")
    Integer getInteger();

    @Named("settings.map.integer")
    int getPrimitiveInteger();

    @Named("settings.map.long")
    Long getLong();

    @Named("settings.map.long")
    long getPrimitiveLong();

    @Named("settings.map.double")
    Double getDouble();

    @Named("settings.map.double")
    double getPrimitiveDouble();

    @Named("settings.map.float")
    Float getFloat();

    @Named("settings.map.float")
    float getPrimitiveFloat();

    @Named("settings.map.date")
    Date getDate();

    @Named("settings.inexistent")
    Object getInexistent();
}
