package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 集中加载IOC,CLASS,CONTROLLER和所有的bean
 * Created by slipkinem on 7/3/2017.
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
