<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Chỉ import các lớp Java cần thiết --%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Bean.AdminTemplate" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Template</title>
    
    <%-- Dùng EL cho đường dẫn CSS (Cách làm tốt) --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Template/ListTemplate.css" rel="stylesheet" type="text/css">
</head>
<body>

    <div class="container">
        <h1>Danh Sách Các Mẫu Bản Ghi</h1>
        
        <%-- Hiển thị thông báo lỗi (Nếu có, dùng EL) --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div style="color: red; padding: 10px; border: 1px solid red; margin-bottom: 15px;">
                Lỗi: <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
		
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Mẫu</th>
                    <th>Nội Dung (Tóm tắt)</th>
                    <th>Thao Tác</th>
                </tr>
            </thead>
            <tbody>
                
                <%
                    // 1. Lấy danh sách từ request và kiểm tra NULL
                    List<AdminTemplate> templates = (List<AdminTemplate>)request.getAttribute("templates");
                    System.out.println("List.jsp - Số lượng template: " + (templates != null ? templates.size() : "null"));
                    if (templates != null && !templates.isEmpty()) {
                        // 2. Vòng lặp bằng Scriptlet
                        for (AdminTemplate template : templates) {
                            String contentSummary = template.getContent();
                            
                            // 3. Logic cắt chuỗi bằng Scriptlet
                            if (contentSummary != null && contentSummary.length() > 80) {
                                contentSummary = contentSummary.substring(0, 80) + "...";
                            } 
                %>
                
                <tr>
                    <%-- 4. Hiển thị dữ liệu bằng Expression --%>
                    <td><%= template.getId() %></td>
                    <td><%= template.getTemplateName() %></td>
                    <td><%= contentSummary %></td>
                    <td class="actions-cell">
                        <%-- 5. Đường dẫn action dùng kết hợp EL và Expression --%>
                        <a href="${pageContext.request.contextPath}/Admin/Template/Edit?id=<%= template.getId() %>" class="action-edit">Sửa</a>
                        <a href="${pageContext.request.contextPath}/Admin/Template/Copy?id=<%= template.getId() %>" class="action-copy">Chép</a>
                        <a href="${pageContext.request.contextPath}/Admin/Template/Delete?id=<%= template.getId() %>" class="action-delete" 
                            onclick="return confirm('Bạn có chắc chắn muốn xóa mẫu này?');">Xóa bỏ</a>
                    </td>
                </tr>
                
                <%
                        } // Kết thúc vòng lặp
                    } else {
                %>
                
                <%-- Trường hợp không có dữ liệu --%>
                <tr>
                    <td colspan="4" style="text-align: center;">
                        <p style="margin-top: 10px; color: #888;">Chưa có bản ghi Template nào.</p>
                    </td>
                </tr>
                
                <%
                    } // Kết thúc khối if/else
                %>
                
            </tbody>
        </table>
    </div>
</body>
</html>