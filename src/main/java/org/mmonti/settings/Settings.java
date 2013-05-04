package org.mmonti.settings;

/**
 * Settings API.
 *
 * @author: monti.mauro
 */
public interface Settings {

    /**
     *
     * @param targetInterface
     * @param <T>
     * @return
     */
    <T> T getSettings(Class<T> targetInterface);

    /**
     *
     * @return
     */
    boolean isEmpty();

    /**
     *
     * @return
     */
    boolean hasConverters();
}
