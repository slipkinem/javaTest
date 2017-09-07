package io.vert.hello;

import io.vertx.core.Vertx;

/**
 * Created by slipkinem on 9/7/2017.
 */
public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
