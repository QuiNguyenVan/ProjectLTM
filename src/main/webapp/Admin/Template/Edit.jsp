<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Import các lớp Java cần thiết --%>
<%@ page import="Model.Bean.AdminTemplate" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Mẫu</title>
    
    <%-- Icon (Dùng chung) --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    
    <%-- Base css --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Style.css?v0" rel="stylesheet" type="text/css">
    
    <%-- Global CSS --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Global.css?v0" rel="stylesheet" type="text/css">
    
    <%-- Template CSS (Đã đổi tên file dựa trên phong cách bạn đã có) --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Template/CreateTemplate.css?v1" rel="stylesheet" type="text/css">
</head>
<body>

<%
    // 1. Lấy đối tượng template từ request attribute
    AdminTemplate template = (AdminTemplate)request.getAttribute("template");
    
    // 2. Lấy thông báo lỗi (từ Servlet sau khi xử lý thất bại)
    String errorMessage = (String)request.getAttribute("errorMessage");
    
    // 3. Khởi tạo biến cho form (sử dụng giá trị từ template hoặc từ param nếu không có template object)
    String documentId = "";
    String templateName = "";
    String content = "";
    
    if (template != null) {
        // Nếu có đối tượng template, lấy dữ liệu từ đó
        documentId = String.valueOf(template.getId()); // Chuyển int sang String
        templateName = template.getTemplateName() != null ? template.getTemplateName() : "";
        content = template.getContent() != null ? template.getContent() : "";
    } else {
        // Trường hợp lỗi (ví dụ: Servlet forward lại sau khi update thất bại)
        // Lấy lại dữ liệu từ request parameter để điền lại form
        documentId = request.getParameter("templateId") != null ? request.getParameter("templateId") : "";
        templateName = request.getParameter("templateName") != null ? request.getParameter("templateName") : "";
        content = request.getParameter("content") != null ? request.getParameter("content") : "";
    }
%>

<div class="create-container">
    <h3 class="inner-title">Chỉnh Sửa Mẫu</h3>
    
    <%-- Hiển thị thông báo lỗi (Sử dụng Scriptlet/Expression) --%>
    <% if (errorMessage != null) { %>
        <div style="color: red; padding: 10px; border: 1px solid red; margin-bottom: 15px;">
            Lỗi: <%= errorMessage %>
        </div>
    <% } %>
    
    <form id="editForm" action="${pageContext.request.contextPath}/AdminEditTemplateServlet" method="post">
        
        <%-- ID mẫu (Trường ẩn bắt buộc) --%>
        <input type="hidden" name="templateId" value="<%= documentId %>">
        
        <label for="doc_title">Tiêu Đề Mẫu:</label>
        <%-- Điền lại giá trị cũ (sử dụng Expression) --%>
        <input type="text" id="doc_title" name="templateName" value="<%= templateName %>">
        <span id="title_error" class="error-message"></span>
        
        <label for="doc_content">Nội Dung Mẫu:</label>
        <%-- Điền lại giá trị cũ cho textarea --%>
        <textarea id="doc_content" name="content"><%= content %></textarea>
        <span id="content_error" class="error-message"></span>
        
        <input type="submit" value="Cập Nhật Mẫu" class="submit-button">
        
    </form>
</div>

<%-- Script Validation (Dùng EL cho đường dẫn) --%>
<script src="${pageContext.request.contextPath}/Public/Admin/Assets/JS/Template/Create.js?v1"></script>

</body>
</html>