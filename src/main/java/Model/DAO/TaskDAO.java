package Model.DAO;

import Model.Bean.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // Phương thức tiện ích để đóng tài nguyên CSDL
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

    // ----------------------------------------------------------------------
    // 1. PHƯƠNG THỨC: LẤY LỊCH SỬ TASK (Cho TaskHistoryController)
    // ----------------------------------------------------------------------
    /**
     * Truy vấn CSDL để lấy danh sách Task dựa trên ID người dùng.
     * @param userId ID của người dùng.
     * @return Danh sách các đối tượng Task (không bao gồm fileContent).
     */
    public List<Task> findTasksByUserId(int userId) {
        List<Task> taskList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // Chỉ chọn các cột cần thiết cho trang lịch sử, sắp xếp theo Task mới nhất (DESC)
        String sql = "SELECT taskId, userId, fileName, createdAt, status FROM task WHERE userId = ? ORDER BY createdAt DESC"; 

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setUserId(rs.getInt("userId"));
                task.setFileName(rs.getString("fileName"));
                // Không cần set fileContent ở đây
                task.setCreatedAt(rs.getTimestamp("createdAt")); 
                task.setStatus(rs.getString("status"));
                
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return taskList;
    }

    // ----------------------------------------------------------------------
    // 2. PHƯƠNG THỨC: TẠO TASK MỚI (Cho CheckSubmitController)
    // ----------------------------------------------------------------------
    /**
     * Tạo một Task mới trong CSDL.
     * @return true nếu tạo Task thành công.
     */
    public boolean createTask(Task task) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        // Sử dụng NOW() để CSDL tự điền thời gian tạo (createAt)
        String sql = "INSERT INTO task (userId, fileName, fileContent, status, createdAt) VALUES (?, ?, ?, ?, NOW())";

        try {
            conn = DBConnection.getConnection();
            // Sử dụng Statement.RETURN_GENERATED_KEYS để lấy ID Task vừa được tạo
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, task.getUserId());
            ps.setString(2, task.getFileName());
            ps.setString(3, task.getFileContent());
            ps.setString(4, task.getStatus()); // 'PENDING'

            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                // Lấy taskId tự tăng và set vào đối tượng Task
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    task.setTaskId(rs.getInt(1)); 
                }
                rs.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }
        return false;
    }
    
}