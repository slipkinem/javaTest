package io.vert.hello;

import io.vertx.core.AbstractVerticle;

/**
 * Created by slipkinem on 9/7/2017.
 */
public class MainVerticle extends AbstractVerticle {
    public void start() {
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
