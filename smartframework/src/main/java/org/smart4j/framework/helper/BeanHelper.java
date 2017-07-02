package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by slipkinem on 6/30/2017.
 */
public final class BeanHelper {
    /**
     * 存放Bean类和bean实例的映射关系
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    /**
     * 先运行此静态代码块
     */
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, object);
        }
    }

    /**
     * 获取bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap () {
        return BEAN_MAP;
    }

    /**
     * 通过class获取类的实例
     * @param cls class
     * @param <T> 获取的类的类例
     * @return 类的实例
     */
    @SuppressWarnings("unchecked") // 不检查类型，不然返回值会报warning
    public static <T> T getBean (Class<?> cls) {
        if (BEAN_MAP.containsKey(cls)) {
            return (T) BEAN_MAP.get(cls);
        } else {
            throw new RuntimeException("无法通过" + cls + "获取到实例");
        }
    }


}

