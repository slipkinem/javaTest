package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by slipkinem on 6/30/2017.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取所有类
     * @return
     */
    public static Set<Class<?>> getClassSet () {
        return CLASS_SET;
    }

    /**
     * 获取所有@Service注解
     * @return
     */
    public static Set<Class<?>> getServiceClassSet () {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet) {
            // 判断类有没有@Service
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet () {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取包下面所有的Bean类，包括 Service Controller
     * @return beanClassSet
     */
    public static Set<Class<?>> getBeanClassSet () {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }

}
