package Model.DAO;

import Model.Bean.AdminTemplate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminTemplateDAO {
    private static final String SELECT_ALL_TEMPLATES = "SELECT id, templateName, content FROM template";

    public List<AdminTemplate> getAllTemplates() {
        List<AdminTemplate> templates = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.err.println("AdminTemplateDAO.getAllTemplates: Unable to obtain DB connection (null)");
                return null; // propagate null so caller knows there was an error
            }
            preparedStatement = conn.prepareStatement(SELECT_ALL_TEMPLATES);
            rs = preparedStatement.executeQuery();

            boolean hasAny = false;
            while (rs.next()) {
                hasAny = true;
                int id = rs.getInt("id");
                String name = rs.getString("templateName");
                String content = rs.getString("content");
                // Ánh xạ dữ liệu từ ResultSet sang Bean
                AdminTemplate template = new AdminTemplate(id, name, content);
                templates.add(template);
            }
            if (!hasAny) {
                System.out.println("AdminTemplateDAO.getAllTemplates: Query executed, but no rows returned.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi kết nối hoặc truy vấn
            return null;
        } finally {
            // Close resources safely
            try { if (rs != null) rs.close(); } catch (Exception ex) { /* ignore */ }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ex) { /* ignore */ }
            try { if (conn != null) conn.close(); } catch (Exception ex) { /* ignore */ }
        }
        return templates;
    }
    
    public boolean insertTemplate(String templateName, String content) {
		String INSERT_TEMPLATE_SQL = "INSERT INTO template (templateName, content) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DBConnection.getConnection();
			if (conn == null) {
				System.err.println("AdminTemplateDAO.insertTemplate: Unable to obtain DB connection (null)");
				return false; // propagate false so caller knows there was an error
			}
			preparedStatement = conn.prepareStatement(INSERT_TEMPLATE_SQL);
			preparedStatement.setString(1, templateName);
			preparedStatement.setString(2, content);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi kết nối hoặc truy vấn
			return false;
		} finally {
			// Close resources safely
			try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ex) { /* ignore */ }
			try { if (conn != null) conn.close(); } catch (Exception ex) { /* ignore */ }
		}
	}     
    
    public AdminTemplate getTemplateById(int templateId) {
		String SELECT_TEMPLATE_BY_ID = "SELECT id, templateName, content FROM template WHERE id = ?";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			if (conn == null) {
				System.err.println("AdminTemplateDAO.getTemplateById: Unable to obtain DB connection (null)");
				return null; // propagate null so caller knows there was an error
			}
			preparedStatement = conn.prepareStatement(SELECT_TEMPLATE_BY_ID);
			preparedStatement.setInt(1, templateId);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String name = rs.getString("templateName");
				String content = rs.getString("content");
				return new AdminTemplate(templateId, name, content);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi kết nối hoặc truy vấn
			return null;
		} finally {
			// Close resources safely
			try { if (rs != null) rs.close(); } catch (Exception ex) { /* ignore */ }
			try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ex) { /* ignore */ }
			try { if (conn != null) conn.close(); } catch (Exception ex) { /* ignore */ }
		}
		return null; // Template not found
	}
    
    public boolean updateTemplateById(int templateId, String templateName, String content) {
    	String UPDATE_TEMPLATE_SQL = "UPDATE template SET templateName = ?, content = ? WHERE id = ?";
    	Connection conn = null;
    	PreparedStatement preparedStatement = null;
    	try {
			conn = DBConnection.getConnection();
			if (conn == null) {
				System.err.println("AdminTemplateDAO.updateTemplateById: Unable to obtain DB connection (null)");
				return false; // propagate false so caller knows there was an error
			}
			preparedStatement = conn.prepareStatement(UPDATE_TEMPLATE_SQL);
			preparedStatement.setString(1, templateName);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, templateId);				
			int affectedRows = preparedStatement.executeUpdate();					
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi kết nối hoặc truy vấn
			return false;
		} finally {					// Close resources safely
			try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ex) { /* ignore */ }
			try { if (conn != null) conn.close(); } catch (Exception ex) { /* ignore */ }
		}
    }
    
    public boolean deleteTemplateById(int templateId) {
		String DELETE_TEMPLATE_SQL = "DELETE FROM template WHERE id = ?";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DBConnection.getConnection();
			if (conn == null) {
				System.err.println("AdminTemplateDAO.deleteTemplateById: Unable to obtain DB connection (null)");
				return false; // propagate false so caller knows there was an error
			}
			preparedStatement = conn.prepareStatement(DELETE_TEMPLATE_SQL);
			preparedStatement.setInt(1, templateId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi kết nối hoặc truy vấn
			return false;
		} finally {
			// Close resources safely
			try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ex) { /* ignore */ }
			try { if (conn != null) conn.close(); } catch (Exception ex) { /* ignore */ }
		}
    }
}