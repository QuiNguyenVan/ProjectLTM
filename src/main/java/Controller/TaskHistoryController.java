package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Cần thiết để lấy Session
import java.io.IOException;
import java.util.List;

import Model.BO.TaskBO;
import Model.Bean.User; // Cần thiết để lấy User từ Session
import Model.Bean.Task; // Cần thiết để lưu trữ danh sách Task

@WebServlet("/TaskHistoryController")
public class TaskHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private TaskBO taskBO; 

    public TaskHistoryController() {
        super();
        // Khởi tạo TaskBO khi Controller được tạo
        this.taskBO = new TaskBO(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // 1. Kiểm tra Session và Đăng nhập
        HttpSession session = request.getSession(false); // Không tạo Session mới
        
        if (session == null || session.getAttribute("user") == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("LoginServlet"); 
            return;
        }

        // 2. Lấy User ID của người dùng hiện tại từ Session
        User user = (User) session.getAttribute("user");
        int userId = user.getId(); // Giả định class User có phương thức getId()

        try {
            // 3. GỌI TASK BO để lấy danh sách Task
            // TaskBO sẽ gọi TaskDAO.findTasksByUserId(userId)
            List<Task> taskList = taskBO.getTasksByUserId(userId);
            
            // 4. ĐẶT DỮ LIỆU VÀO REQUEST để TaskHistory.jsp có thể truy cập
            // Tên attribute là "taskList"
            request.setAttribute("taskList", taskList);
            
            // 5. CHUYỂN TIẾP (FORWARD) SANG TRANG VIEW
            // TaskHistory.jsp sẽ hiển thị bảng lịch sử
            request.getRequestDispatcher("TaskHistory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi hệ thống/CSDL
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi tải lịch sử Task.");
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Chức năng xem lịch sử thường dùng GET
		doGet(request, response);
	}

}