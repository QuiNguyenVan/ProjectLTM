package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Model.BO.AdminTemplateBO;
import Model.Bean.User;

@WebServlet("/AdminCreateTemplateServlet")
public class AdminCreateTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
	    String templateName = request.getParameter("templateName");
	    String content = request.getParameter("content");

	    HttpSession session = request.getSession();
	    User loggedInUser = (User) session.getAttribute("user");

	    if (loggedInUser == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
	    
	    int userId = loggedInUser.getId();

	    AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
	    boolean isCreated = adminTemplateBO.createTemplate(userId, templateName, content);
	    
	    if (isCreated) {
	        response.sendRedirect("AdminListTemplateServlet");
	    } else {
	        request.setAttribute("errorMessage", "Không thể tạo mẫu. Vui lòng kiểm tra dữ liệu.");
	        request.getRequestDispatcher("Create.jsp").forward(request, response);
	    }
	}

}
