package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminDeleteTemplateServlet
 */
@WebServlet("/AdminDeleteTemplateServlet")
public class AdminDeleteTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// Lấy thông tin templateId từ request
		String templateId = request.getParameter("templateId");
		if (templateId == null || templateId.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Template ID không hợp lệ.");
			request.getRequestDispatcher("/Admin/Template/List.jsp").forward(request, response);
			return;
		}
		
		try {
			int id = Integer.parseInt(templateId);
			Model.BO.AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
			boolean isDeleted = adminTemplateBO.deleteTemplateById(id);
			if (!isDeleted) {
				request.setAttribute("errorMessage", "Không thể xóa template.");
			}
			
			// Chuyển hướng về danh sách template sau khi xóa
			response.sendRedirect("AdminListTemplateServlet");
		} catch (NumberFormatException ex) {
			request.setAttribute("errorMessage", "Template ID không phải là số.");
			request.getRequestDispatcher("/Admin/Template/List.jsp").forward(request, response);
		}
	}

}
