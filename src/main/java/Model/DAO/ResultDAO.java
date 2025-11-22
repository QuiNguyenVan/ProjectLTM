package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Model.Bean.Result;

public class ResultDAO {

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

    // Lấy kết quả theo taskId
    public Result getResultByTaskId(int taskId) {
        String sql = "SELECT * FROM result WHERE taskID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Result r = new Result();
                r.setResultId(rs.getInt("resultId"));
                r.setTaskId(rs.getInt("taskID"));
                r.setSimilarityPercent(rs.getDouble("similarityPercent"));
                r.setMatchedTemplate(rs.getString("matchedTemplate"));
                r.setCheckedAt(rs.getTimestamp("checkedAt"));
                return r;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
