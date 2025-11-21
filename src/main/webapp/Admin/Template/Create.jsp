<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo Mới Mẫu</title>

<%-- Base css --%>
<link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Style.css?v0" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Template/CreateTemplate.css?v1" rel="stylesheet" type="text/css">
</head>
<body>

<div class="create-container">
    <h3 class="inner-title">Tạo Mẫu Mới</h3>
    
    <form id="createForm" action="../../AdminCreateTemplateServlet" method="post">
        
        <label for="doc_title">Tiêu Đề Mẫu:</label>
        <input type="text" id="doc_title" name="templateName">
        <span id="title_error" class="error-message"></span>
        
        <label for="doc_content">Nội dung Mẫu:</label>
        <textarea id="doc_content" name="content"></textarea>
        <span id="content_error" class="error-message"></span>
        
        <input type="submit" value="Lưu Mẫu" class="submit-button">
        
    </form>
</div>

<script src="../../Public/Admin/Assets/JS/Template/Create.js?v1"></script>

</body>
</html>