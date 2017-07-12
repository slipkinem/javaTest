package org.smart4j.chapter3.Hello;

/**
 * Created by slipkinem on 7/7/2017.
 */
public class HelloProxyTest {
    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Jack");
    }
}
