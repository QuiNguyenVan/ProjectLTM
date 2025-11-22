package Model.Bean; // Đặt tên package thích hợp

import java.time.LocalDateTime; // Cần thiết cho kiểu DATETIME của SQL

public class AdminTemplate {

    // 1. Dữ liệu chính (Khóa và Thông tin cơ bản)
    private int id; // int(11) trong MySQL
    private String templateName; // varchar(30) trong MySQL
    private String content; // longtext trong MySQL
    
    // 2. Dữ liệu quản lý (Các trường bổ sung)
    private int userId; // user_id (int(11))
    private LocalDateTime createdAt; // created_at (datetime)
    private LocalDateTime updatedAt; // updated_at (datetime)
    
    // Lưu ý: Cột 'status' (TINYINT) thường được ánh xạ là boolean hoặc int.
    // Nếu bạn không thêm cột này, hãy bỏ qua nó. Nếu có, nên dùng int hoặc boolean.

    // ----------------------------------------------------
    // CONSTRUCTORS (Tùy chọn, nhưng khuyến nghị)
    // ----------------------------------------------------
    
    public AdminTemplate() {
        // Constructor mặc định (cần thiết cho nhiều Framework)
    }

    // Constructor đầy đủ (Ví dụ)
    public AdminTemplate(int id, String templateName, String content, int userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.templateName = templateName;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // ----------------------------------------------------
    // GETTERS & SETTERS (Bắt buộc)
    // ----------------------------------------------------
    
    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // TemplateName
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    // Content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // User ID
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Created At (DATETIME)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Updated At (DATETIME)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}