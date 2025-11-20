package Model.Bean;

import java.sql.Timestamp;

public class Result {
	private int resultId;
    private int taskId;
    private double similarityPercent;
    private String matchedTemplate;     // có thể để null
    private Timestamp checkedAt;
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public double getSimilarityPercent() {
		return similarityPercent;
	}
	public void setSimilarityPercent(double similarityPercent) {
		this.similarityPercent = similarityPercent;
	}
	public String getMatchedTemplate() {
		return matchedTemplate;
	}
	public void setMatchedTemplate(String matchedTemplate) {
		this.matchedTemplate = matchedTemplate;
	}
	public Timestamp getCheckedAt() {
		return checkedAt;
	}
	public void setCheckedAt(Timestamp checkedAt) {
		this.checkedAt = checkedAt;
	}
}
