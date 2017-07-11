package org.smart4j.chapter3.Hello;

/**
 * Created by slipkinem on 7/10/2017.
 */
public class CGLibProxySingletonTest {
    public static void main(String[] args) {
        Hello helloProxy = CGLibProxySingleton.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("lvsen");
        TestHello testHello = CGLibProxySingleton.getInstance().getProxy(TestHello.class);
        testHello.say("吕森");
    }
}
