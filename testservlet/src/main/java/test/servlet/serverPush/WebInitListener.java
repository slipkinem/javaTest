package test.servlet.serverPush;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by slipkinem on 2017/4/19.
 */
public class WebInitListener implements ServletContextListener {
    private List<AsyncContext> asyncContexts = new ArrayList<AsyncContext>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("asyncs", asyncContexts);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((int)(Math.random() * 10000));
                        double num = Math.random() * 10;
                        synchronized (asyncContexts) {
                            for (AsyncContext context:
                                 asyncContexts) {
                                context.getResponse().getWriter().println(num);
                                context.complete();
                            }
                            asyncContexts.clear();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
