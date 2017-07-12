package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 实现切面代理
 * Created by slipkinem on 7/10/2017.
 */
public class AspectProxy implements Proxy {
    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] targetParams = proxyChain.getMethodParams();

        begin();

        try {
            if (intercept(cls, targetMethod, targetParams)) {
                before(cls, targetMethod, targetParams);
                result = proxyChain.doProxyChain();
                after(cls, targetMethod, targetParams, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(cls, targetMethod, targetParams, e);
        } finally {
            end();
        }

        return result;
    }

    public void begin () {}

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {}

    public boolean intercept (Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {}

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {

    }
    public void end () {}
}
