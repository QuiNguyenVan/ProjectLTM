package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import java.io.IOException;
import java.util.List;

import Model.BO.TaskBO;
import Model.Bean.User; 
import Model.Bean.Task;

@WebServlet("/TaskHistoryServlet")
public class TaskHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private TaskBO taskBO; 

    public TaskHistoryServlet() {
        super();
        this.taskBO = new TaskBO(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp"); 
            return;
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId(); 

        try {
            List<Task> taskList = taskBO.getTasksByUserId(userId);
 
            request.setAttribute("taskList", taskList);
            request.getRequestDispatcher("TaskHistory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi tải lịch sử Task.");
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}