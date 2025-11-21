package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminCreateTemplateServlet")
public class AdminCreateTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String templateName = request.getParameter("templateName");
		String content = request.getParameter("content");

		Model.BO.AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
		boolean isCreated = adminTemplateBO.createTemplate(templateName, content);

		if (isCreated) {
			// Nếu tạo thành công, chuyển hướng về danh sách template
			response.sendRedirect("AdminListTemplateServlet");
		} else {
			// Nếu tạo thất bại, hiển thị thông báo lỗi
			request.setAttribute("errorMessage", "Không thể tạo template mới.");
			request.getRequestDispatcher("/Admin/Template/Create.jsp").forward(request, response);
		}
	}

}
