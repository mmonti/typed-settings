package org.mmonti.settings;

import java.lang.reflect.Method;

/**
 * Defines the method to retrieve the value of a key, based on the method call.
 *
 * @author: monti.mauro
 */
public interface PropertyKeyEntryLookup {

    /**
     *
     * @param method
     * @param <T>
     * @return
     */
    <T> T lookup(final Method method);

}
