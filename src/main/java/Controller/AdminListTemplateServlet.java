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

/**
 * Servlet implementation class AdminListTemplateServlet
 */
@WebServlet("/AdminListTemplateServlet")
public class AdminListTemplateServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AdminTemplateBO adminTemplateBO = new AdminTemplateBO();
		List<AdminTemplate> templates = adminTemplateBO.retrieveAllTemplates();
		System.out.println(templates.size()); // Dòng mới
		System.out.println("--------------------------------------------------------------------------"); // Dòng mới
		if (templates != null) {
			// Put templates in request scope and forward to the list JSP to "rewrite" (render) the current page
			for (Iterator iterator = templates.iterator(); iterator.hasNext();) {
				AdminTemplate adminTemplate = (AdminTemplate) iterator.next();
				System.out.println(adminTemplate.getId());
				System.out.println(adminTemplate.getTemplateName());
				System.out.println(adminTemplate.getContent());
			}
			request.setAttribute("templates", templates);
			request.getRequestDispatcher("/Admin/Template/List.jsp").forward(request, response);
			request.getRequestDispatcher("List.jsp").forward(request, response);
		} else {
			System.out.println("Controller null"); // Dòng mới
			request.setAttribute("errorMessage", "Không thể tải danh sách template.");
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Delegate POST to GET so the same rendering behavior occurs
		doGet(req, resp);
	}
	
}