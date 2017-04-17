package test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by slipkinem on 2017/4/17.
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if ("lvsen".equals(user) && "111".equals(password)) {
            session.setAttribute("login", user);
            session.setAttribute("password", password);
            request.getRequestDispatcher("user.view")
                    .forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }
}
