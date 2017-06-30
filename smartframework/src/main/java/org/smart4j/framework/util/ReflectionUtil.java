package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by slipkinem on 6/30/2017.
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 实例化类
     * @param cls 类
     * @return instance
     */
    public static Object newInstance (Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("实例化类失败");
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 方法调用 类似angular的  invoke
     * @param object 调用的对象
     * @param method 调用方法
     * @param args 参数
     * @return 方法执行结果
     */
    public static Object invokeMethod (Object object, Method method, Object ... args) {
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(object, args);
        } catch (Exception e) {
            LOGGER.error("调用方法失败");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param object 对象
     * @param field 成员变量
     * @param value 成员变量的值
     */
    public static void setField (Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("设置成员变量失败");
        }
    }

}
