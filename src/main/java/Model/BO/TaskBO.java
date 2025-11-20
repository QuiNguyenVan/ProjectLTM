package Model.BO;

import Model.Bean.Task;
import Model.DAO.TaskDAO; 
import java.util.List;

/**
 * TaskBO (Business Object) chứa các logic nghiệp vụ liên quan đến Task.
 */
public class TaskBO {
    
    private TaskDAO taskDAO;

    public TaskBO() {
        // Khởi tạo DAO để giao tiếp với tầng dữ liệu
        this.taskDAO = new TaskDAO(); 
    }

    /**
     * 1. Phương thức NGHIỆP VỤ: Lấy danh sách Task của một người dùng.
     * Được gọi bởi TaskHistoryController.
     * @param userId ID của người dùng.
     * @return Danh sách các Task, sắp xếp theo thời gian tạo mới nhất.
     */
    public List<Task> getTasksByUserId(int userId) {
        // [Logic nghiệp vụ tại đây, ví dụ: kiểm tra quyền, caching]
        
        // Gọi DAO để truy xuất CSDL
        return taskDAO.findTasksByUserId(userId);
    }
    
    /**
     * 2. Phương thức NGHIỆP VỤ: Tạo một Task mới khi người dùng gửi file/text.
     * Phương thức này sẽ được sử dụng bởi CheckSubmitController.
     * @param userId ID người dùng tạo Task.
     * @param fileName Tên file (hoặc "Text Input").
     * @param fileContent Nội dung file/text.
     * @return true nếu tạo Task thành công.
     */
    public boolean createNewTask(int userId, String fileName, String fileContent) {
        // Tạo đối tượng Task và thiết lập trạng thái ban đầu
        Task task = new Task();
        task.setUserId(userId);
        task.setFileName(fileName);
        task.setFileContent(fileContent);
        task.setStatus("PENDING"); // Trạng thái ban đầu: Đang chờ xử lý
        
        // Gọi DAO để lưu Task vào CSDL
        return taskDAO.createTask(task);
    }
    
    // [Các phương thức nghiệp vụ khác sẽ được thêm sau, ví dụ: getNextPendingTask(), updateTaskStatus()]
}