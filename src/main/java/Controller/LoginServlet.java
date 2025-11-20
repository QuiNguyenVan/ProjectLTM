package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import Model.Bean.User;
import Model.BO.UserBO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBO userBO = new UserBO();
        User user = userBO.login(username, password);

        if (user != null) {
            // Lưu user vào session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Chuyển sang Welcome.jsp
            RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
            rd.forward(request, response);

        } else {
            // Sai tài khoản
        		response.sendRedirect("Login.jsp");
//            response.sendRedirect("Login.jsp?error=1"); 
        }
    }
}
