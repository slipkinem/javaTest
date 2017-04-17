package test.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by slipkinem on 2017/4/17.
 */

/**
 * dispatcher 派遣，调用 用户将一个请求传递给另一个servlet
 * include: 可以将另一个servlet也插入流程之中
 */

@WebServlet("/dispatcher")
public class Dispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        out.println("some do one...");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("other.view");
        requestDispatcher.include(request, response);
        out.println("some do two");
        out.close();
    }
}
