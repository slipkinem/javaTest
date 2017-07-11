package org.smart4j.framework.proxy;

/**
 * Created by slipkinem on 7/10/2017.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
