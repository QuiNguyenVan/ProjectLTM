package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Task;

public class TaskDAO {

    // Tạo task mới, trả về taskId vừa tạo
    public int createTask(Task task) {
        String sql = "INSERT INTO task (userId, fileName, fileContent, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, task.getUserId());
            ps.setString(2, task.getFileName());
            ps.setString(3, task.getFileContent());
            ps.setString(4, task.getStatus());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // taskId tự tăng
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // lỗi
    }

    // Lấy task đang pending (status = 'processing') hoặc theo userId nếu muốn
    public Task getPendingTask() {
        String sql = "SELECT * FROM task WHERE status = 'processing' ORDER BY createdAt ASC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setUserId(rs.getInt("userId"));
                task.setFileName(rs.getString("fileName"));
                task.setFileContent(rs.getString("fileContent"));
                task.setCreatedAt(rs.getTimestamp("createdAt"));
                task.setStatus(rs.getString("status"));
                return task;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getPendingTasks()
    {
    	List<Task> list=new ArrayList<Task>();
    	String sql = "SELECT * FROM task WHERE status = 'processing' ORDER BY createdAt ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setUserId(rs.getInt("userId"));
                task.setFileName(rs.getString("fileName"));
                task.setFileContent(rs.getString("fileContent"));
                task.setCreatedAt(rs.getTimestamp("createdAt"));
                task.setStatus(rs.getString("status"));
                list.add(task);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTaskStatus(int taskId, String status) {
        String sql = "UPDATE task SET status = ? WHERE taskId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, taskId);
            int updated = ps.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM task WHERE taskId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("taskId"));
                    task.setUserId(rs.getInt("userId"));
                    task.setFileName(rs.getString("fileName"));
                    task.setFileContent(rs.getString("fileContent"));
                    task.setCreatedAt(rs.getTimestamp("createdAt"));
                    task.setStatus(rs.getString("status"));
                    return task;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
