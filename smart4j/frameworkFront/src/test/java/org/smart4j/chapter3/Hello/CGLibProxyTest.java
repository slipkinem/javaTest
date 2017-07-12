package org.smart4j.chapter3.Hello;

/**
 * Created by slipkinem on 7/10/2017.
 */
public class CGLibProxyTest {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello hello = cgLibProxy.getProxy(HelloImpl.class);
        hello.say("name");
    }
}
