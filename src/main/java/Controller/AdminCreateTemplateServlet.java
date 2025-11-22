package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Model.Bean.User;

@WebServlet("/AdminCreateTemplateServlet")
public class AdminCreateTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Giả định đây là đoạn code trong Servlet xử lý việc tạo Template
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
	    // 1. Lấy dữ liệu từ form
	    String templateName = request.getParameter("templateName");
	    String content = request.getParameter("content");

	    // 2. LẤY ID NGƯỜI DÙNG TỪ SESSION (BẮT BUỘC)
	    HttpSession session = request.getSession();
	    User loggedInUser = (User) session.getAttribute("user");

	    if (loggedInUser == null) {
	        // Xử lý nếu người dùng chưa đăng nhập (Ví dụ: Chuyển hướng đến trang đăng nhập)
	        response.sendRedirect("Login.jsp");
	        return;
	    }
	    
	    // Lấy ID người dùng (kiểu int)
	    int userId = loggedInUser.getId();

	    // 3. GỌI BO VỚI USER ID THỰC
	    Model.BO.AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
	    boolean isCreated = adminTemplateBO.createTemplate(userId, templateName, content);
	    
	    if (isCreated) {
	        // Xử lý thành công
	        response.sendRedirect("AdminListTemplateServlet");
	    } else {
	        // Xử lý thất bại
	        request.setAttribute("errorMessage", "Không thể tạo mẫu. Vui lòng kiểm tra dữ liệu.");
	        request.getRequestDispatcher("Create.jsp").forward(request, response);
	    }
	}

}
