package org.smart4j.chapter3.Hello;

/**
 * Created by slipkinem on 7/7/2017.
 */
public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy() {
        hello = new HelloImpl();
    }

    public void say(String mame) {
        before();
        hello.say(mame);
        after();
    }

    public void before() {
        System.out.println("Before");
    }

    public void after() {
        System.out.println("After");
    }
}
