package com.poly.schoolDataManager.payload;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Payload mapper.
 *
 * @param <A> the type parameter
 * @param <B> the type parameter
 */
public class PayloadMapper<A, B> {
    private final A fromCls;
    private final B toCls;
    private final Map<String, Boolean> includedProps;

    /**
     * Instantiates a new Payload mapper.
     *
     * @param fromCls the from cls
     * @param toCls   the to cls
     */
    public PayloadMapper(A fromCls, B toCls) {
        this.fromCls = fromCls;
        this.toCls = toCls;
        this.includedProps = new HashMap<>();
    }

    private <T> void getIncludedPropsFrom(T classType) {
        Class<?> cls = classType.getClass();
        List<Field> fields = List.of(cls.getDeclaredFields());
        fields.forEach(f -> includedProps.put(f.getName(), true));
    };

    /**
     * Map to.
     *
     * @param <T>       the type parameter
     * @param classType the class type
     */
    public <T> void mapTo(T classType) {
        getIncludedPropsFrom(classType);

        includedProps.keySet().forEach(
                key -> {
                    StringBuilder getter = new StringBuilder("get")
                            .append(key.substring(0, 1).toUpperCase())
                            .append(key.substring(1));
                    StringBuilder setter = new StringBuilder("set")
                            .append(key.substring(0, 1).toUpperCase())
                            .append(key.substring(1));;
                    try {
                        Class<?> from = fromCls.getClass();
                        Class<?> to = toCls.getClass();
                        Method fromMethod = from.getMethod(getter.toString());
                        Class<?> returnType = fromMethod.getReturnType();
                        Method toMethod = to.getMethod(setter.toString(), returnType);
                        Object arg = fromMethod.invoke(fromCls);

                        if (arg != null) {
                            toMethod.invoke(toCls, arg);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
