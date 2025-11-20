package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Bean.Template;
import Model.Bean.Result;
import Model.Bean.User;

public class PlagiarismDAO {
	
    public List<Template> getAllTemplates() {
        List<Template> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM template";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Template t = new Template();
                t.setId(rs.getInt("id"));
                t.setTemplateName(rs.getString("templateName"));
                t.setContent(rs.getString("content"));
                list.add(t);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lưu kết quả kiểm tra vào DB
    public void saveResult(Result r) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO result(taskId, similarityPercent, matchedTemplate, checkedAt) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, r.getTaskId());
            ps.setDouble(2, r.getSimilarityPercent());
            ps.setString(3, r.getMatchedTemplate());
            ps.setTimestamp(4, r.getCheckedAt());
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
