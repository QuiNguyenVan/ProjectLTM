package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import Model.BO.UserBO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_password=request.getParameter("confirm_password");
        if(!password.equals(confirm_password))
        {
        	response.sendRedirect("Register.jsp?msg=pass_not_match");
        }
        UserBO userBO = new UserBO();

        boolean result = userBO.register(username, password);

        if (result) {
            // Đăng ký thành công → chuyển sang Login.jsp
            response.sendRedirect("Login.jsp?msg=register_success");
        } else {
            // Đăng ký thất bại → quay lại Register.jsp
        	  response.sendRedirect("Register.jsp");
//            response.sendRedirect("Register.jsp?error=1");
        }
    }
}
