package org.mmonti.settings;

import java.lang.reflect.Method;

/**
 * Class to hold information about a annotation key, method, value and return type.
 *
 * @author: monti.mauro
 */
public class PropertyKeyEntry {

    private String methodName;
    private Method method;
    private String key;
    private String value;
    private Class<?> returnType;

    /**
     *
     * @param method
     * @param key
     * @param value
     */
    public PropertyKeyEntry(final Method method, final String key, final String value) {
        this.method = method;
        this.methodName = method.getName();
        this.returnType = method.getReturnType();
        this.key = key;
        this.value = value;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getMethod() {
        return method;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Class<?> getReturnType() {
        return returnType;
    }
}
