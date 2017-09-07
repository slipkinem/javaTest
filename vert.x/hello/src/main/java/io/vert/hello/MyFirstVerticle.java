package io.vert.hello;

import io.vertx.core.AbstractVerticle;

/**
 * Created by slipkinem on 9/7/2017.
 */
public class MyFirstVerticle extends AbstractVerticle {
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            System.out.println(req.absoluteURI());
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("hello word");
        }).listen(8888);
    }
}
