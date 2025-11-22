package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Model.BO.AdminTemplateBO;

@WebServlet("/AdminEditTemplateServlet")
public class AdminEditTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
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
				request.setAttribute("template", template);
			}
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		} catch (NumberFormatException ex) {
			request.setAttribute("errorMessage", "Template ID không phải là số.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
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
			AdminTemplateBO adminTemplateBO = new Model.BO.AdminTemplateBO();
			boolean isUpdated = adminTemplateBO.updateTemplate(id, templateName, content);
			
			if (isUpdated) {
				response.sendRedirect("AdminListTemplateServlet");
			} else {
				request.setAttribute("errorMessage", "Không thể cập nhật template.");
				request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
			}
		} catch (NumberFormatException ex) {
			request.setAttribute("errorMessage", "Template ID không phải là số.");
			request.getRequestDispatcher("/Admin/Template/Edit.jsp").forward(request, response);
		}
	}

}