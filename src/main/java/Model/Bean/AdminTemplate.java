package Model.Bean;

public class AdminTemplate {
    private int id;
    private String templateName;
    private String content;
    // Có thể thêm các trường khác như createdDate, updatedDate

    // Constructor mặc định
    public AdminTemplate() {}

    // Constructor đầy đủ
    public AdminTemplate(int id, String templateName, String content) {
        this.id = id;
        this.templateName = templateName;
        this.content = content;
    }

    // --- Getters và Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    // (Có thể override toString() để debug)
}