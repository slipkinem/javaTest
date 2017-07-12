package org.smart4j.framework.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * 拦截controller
 * Created by slipkinem on 7/10/2017.
 */

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void begin() {
        super.begin();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("------------end-----------------");
    }

    @Override
    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return super.intercept(cls, method, params);
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("----------------begin----------------");
        LOGGER.debug(String.format("class: %s", cls.getName()));
        LOGGER.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
        super.error(cls, method, params, e);
    }

    @Override
    public void end() {
        super.end();
    }
}
