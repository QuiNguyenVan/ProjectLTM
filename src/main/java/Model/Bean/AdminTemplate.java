package Model.Bean;

import java.time.LocalDateTime; 

public class AdminTemplate {

    private int id; 
    private String templateName;
    private String content; 
    private int userId; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; 
    
    public AdminTemplate() {
    }

    public AdminTemplate(int id, String templateName, String content, int userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.templateName = templateName;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}