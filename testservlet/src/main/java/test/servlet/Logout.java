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
@WebServlet("/logout.view")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession httpSession = request.getSession();
        String user = (String) httpSession.getAttribute("login");
        httpSession.invalidate();

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>注销</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>用户 " + user + " 已注销</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
