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
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            String redirectURL;
            
            if (user.getRole() == 1) {
            	response.sendRedirect("Admin/Index.jsp");
			} else {
				String value = user.getUsername();
				response.sendRedirect("Welcome.jsp?username="+value+"");
            }

        } else {
        	request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
    }
}
