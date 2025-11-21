package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminEditTemplateServlet")
public class AdminEditTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// Lấy thông tin templateId từ request
		String templateId = request.getParameter("templateId");
		if (templateId == null || templateId.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Template ID không hợp lệ.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
			return;
		}
		
		try {
			int id = Integer.parseInt(templateId);
			Model.BO.AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
			Model.Bean.AdminTemplate template = adminTemplateBO.getTemplateById(id);
			if (template == null) {
				request.setAttribute("errorMessage", "Không tìm thấy template.");
			} else {
				// Đưa template vào request để JSP có thể sử dụng
				request.setAttribute("template", template);
			}
			
			// Chuyển hướng đến trang chỉnh sửa template
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		} catch (NumberFormatException ex) {
			request.setAttribute("errorMessage", "Template ID không phải là số.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// Lấy thông tin template từ form
		String templateId = request.getParameter("templateId");
		String templateName = request.getParameter("templateName");
		String content = request.getParameter("content");
		
		if (templateId == null || templateId.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Template ID không hợp lệ.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
			return;
		}
		
		try {
			int id = Integer.parseInt(templateId);
			// Cập nhật template trong cơ sở dữ liệu (giả sử có phương thức updateTemplate trong AdminTemplateBO)
			Model.BO.AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
			boolean isUpdated = adminTemplateBO.updateTemplate(id, templateName, content);
			
			if (isUpdated) {
				// Nếu cập nhật thành công, chuyển hướng về danh sách template
				response.sendRedirect("AdminListTemplateServlet");
			} else {
				// Nếu cập nhật thất bại, hiển thị thông báo lỗi
				request.setAttribute("errorMessage", "Không thể cập nhật template.");
				request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
			}
		} catch (NumberFormatException ex) {
			request.setAttribute("errorMessage", "Template ID không phải là số.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		}
	}

}