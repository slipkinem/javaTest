package test.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by slipkinem on 2017/4/17.
 */

// @WebServlet("/hello.view") // 可使用web.xml替代
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(request.getHeader("Date"));
        System.out.println(request.getRemoteAddr());

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello " + name + " </h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
