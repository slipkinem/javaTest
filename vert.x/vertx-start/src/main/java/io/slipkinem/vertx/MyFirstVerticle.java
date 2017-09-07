package io.slipkinem.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * Created by slipkinem on 9/7/2017.
 */
public class MyFirstVerticle extends AbstractVerticle {

    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello Word");
        }).listen(8888);
    }
}
