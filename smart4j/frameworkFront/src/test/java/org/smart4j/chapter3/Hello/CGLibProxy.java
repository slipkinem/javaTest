package org.smart4j.chapter3.Hello;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by slipkinem on 7/10/2017.
 */
public class CGLibProxy implements MethodInterceptor {

    @SuppressWarnings("unchecked")
    public <T> T getProxy (Class<T> tClass) {
        return (T) Enhancer.create(tClass, this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public void before () {
        System.out.println("before");
    }

    public void after () {
        System.out.println("after");
    }
}
