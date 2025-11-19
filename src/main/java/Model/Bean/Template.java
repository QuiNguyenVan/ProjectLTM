package Model.Bean;

import java.sql.Timestamp;

public class Template {
	private int id;
    private String templateName;
    private String content;          // LONGTEXT → String
    private Timestamp createdAt;     // DATETIME → Timestamp
}
