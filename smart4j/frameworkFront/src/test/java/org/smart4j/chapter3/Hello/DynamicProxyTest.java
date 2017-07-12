package org.smart4j.chapter3.Hello;

/**
 * 动态代理
 * Created by slipkinem on 7/7/2017.
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
//        Hello hello = new HelloImpl();
//        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//
//        Hello helloProxy = (Hello) Proxy.newProxyInstance(
//                hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(),
//                dynamicProxy
//        );
//        helloProxy.say("Jack");
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("吕森");
    }
}
