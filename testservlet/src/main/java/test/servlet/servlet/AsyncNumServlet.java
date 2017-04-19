package test.servlet.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by slipkinem on 2017/4/19.
 */

@WebServlet(
        name="AsyncNumServlet", urlPatterns = {"/asyncNum.do"},
        asyncSupported = true
)
public class AsyncNumServlet extends HttpServlet {
    private List<AsyncContext> asyncContexts;

    @Override
    public void init() {
        asyncContexts = (List<AsyncContext>) getServletContext().getAttribute("asyncs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext context = request.startAsync();
        synchronized (asyncContexts) {
            asyncContexts.add(context);
        }
    }

}
