package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by slipkinem on 7/3/2017.
 */
public class Handler {
    private Class<?> controllerClass;

    private Method actionMethod;

    /**
     * 初始化controller类和method（controller类里面的方法）
     * @param controllerClass controller类
     * @param actionMethod 方法 不是requestMethod
     */
    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
