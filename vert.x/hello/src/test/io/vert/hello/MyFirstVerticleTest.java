package io.vert.hello;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by slipkinem on 9/7/2017.
 */
@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(8888, "localhost", "/",
                res -> res.handler(body -> {
                    context.assertTrue(body.toString().contains("hello"));
                    async.complete();
                }));
    }

}
