package test.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by slipkinem on 2017/4/17.
 */
@WebServlet("/user.view")
public class UserView extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {
            response.sendRedirect("login.html");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>欢迎 " + session.getAttribute("login") + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>用户" + session.getAttribute("login") + " 已登录</h1>");
            out.println("<a href='logout.view'>注销</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
