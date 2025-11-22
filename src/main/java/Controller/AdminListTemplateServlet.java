package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import Model.BO.AdminTemplateBO;
import Model.Bean.AdminTemplate;

@WebServlet("/AdminListTemplateServlet")
public class AdminListTemplateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AdminTemplateBO adminTemplateBO = new AdminTemplateBO();
		List<AdminTemplate> templates = adminTemplateBO.retrieveAllTemplates();
		if (templates != null) {
			request.setAttribute("templates", templates);
			request.getRequestDispatcher("/Admin/Template/List.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Không thể tải danh sách template.");
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}