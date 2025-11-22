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

/**
 * Servlet này dùng để phục vụ các yêu cầu AJAX Polling từ TaskHistory.jsp.
 * Nó trả về danh sách Task mới nhất dưới dạng JSON.
 */
@WebServlet("/TaskStatusController")
public class TaskStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private TaskBO taskBO; 

    public TaskStatusController() {
        super();
        this.taskBO = new TaskBO(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // 1. Kiểm tra Session và Đăng nhập
        HttpSession session = request.getSession(false); 
        
        if (session == null || session.getAttribute("user") == null) {
            // Trả về lỗi 401 nếu chưa đăng nhập, vì đây là API endpoint
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Phiên đăng nhập không hợp lệ.");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId(); 

        try {
            // 2. Lấy danh sách Task (Gọi TaskBO -> TaskDAO)
            List<Task> taskList = taskBO.getTasksByUserId(userId);
            
            // 3. Cấu hình HTTP Response là JSON và UTF-8
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            // 4. CHUYỂN ĐỔI List<Task> sang chuỗi JSON thủ công
            String jsonResponse = convertTaskListToJson(taskList);
            
            // 5. Gửi chuỗi JSON về cho client
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            
            // Xử lý lỗi
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Lỗi máy chủ khi tải dữ liệu Task.\"}");
        }
	}
    
    /**
     * Phương thức thủ công chuyển đổi List<Task> sang chuỗi JSON.
     * Chuyển đổi thành các thuộc tính cần thiết: taskId, fileName, createdAt (dạng timestamp), status.
     */
    private String convertTaskListToJson(List<Task> taskList) {
        if (taskList == null || taskList.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            
            // Lấy thời gian dưới dạng mili giây (timestamp) để JavaScript dễ xử lý
            long createdAtTime = (task.getCreatedAt() != null) ? task.getCreatedAt().getTime() : 0; 
            
            // Bắt đầu đối tượng Task JSON
            sb.append("{");
            sb.append("\"taskId\": ").append(task.getTaskId()).append(",");
            sb.append("\"fileName\": \"").append(escapeJson(task.getFileName())).append("\",");
            sb.append("\"createdAt\": ").append(createdAtTime).append(",");
            sb.append("\"status\": \"").append(task.getStatus()).append("\"");
            sb.append("}");
            
            // Thêm dấu phẩy nếu không phải là phần tử cuối cùng
            if (i < taskList.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Phương thức đơn giản để escape dấu ngoặc kép trong chuỗi.
     */
    private String escapeJson(String s) {
        if (s == null) return "";
        // Thay thế " thành \"
        return s.replace("\"", "\\\""); 
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}