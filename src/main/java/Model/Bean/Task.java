package Model.Bean;

import java.sql.Timestamp;

public class Task {
	private int taskId;
    private int userId;
    private String fileName;
    private String fileContent;      // LONGTEXT → String
    private Timestamp createdAt;
    private String status;           // pending / processing / done
}
