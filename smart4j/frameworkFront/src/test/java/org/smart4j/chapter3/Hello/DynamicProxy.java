package org.smart4j.chapter3.Hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by slipkinem on 7/7/2017.
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object object) {
        this.target = object;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }
}
