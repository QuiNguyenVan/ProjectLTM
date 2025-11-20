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
}
