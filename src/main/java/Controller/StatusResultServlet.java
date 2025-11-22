package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/StatusResultServlet")
public class StatusResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public StatusResultServlet() {
  
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp"); 
            return;
        }
        int taskId=Integer.parseInt(request.getParameter("taskId"));
        response.sendRedirect("Processing.jsp?taskId=" + taskId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
