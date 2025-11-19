package Model.Bean;

import java.sql.Timestamp;

public class Result {
	private int resultId;
    private int taskId;
    private double similarityPercent;
    private String matchedTemplate;     // có thể để null
    private Timestamp checkedAt;
}
