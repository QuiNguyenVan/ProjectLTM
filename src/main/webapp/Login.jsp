<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Đăng Nhập</title>
<link rel="stylesheet" href="Public/Assets/CSS/Style.css"> 
<style>
    /* CSS RIÊNG CỦA LOGIN (NẾU CÓ) */
    .form-container {
        width: 300px; /* Đảm bảo chiều rộng cụ thể cho trang login */
    }
    input[type="submit"] {
        width: 100%;
        background-color: #5cb85c; /* Màu riêng cho nút Đăng Nhập */
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        margin-bottom: 15px; 
    }
    input[type="submit"]:hover {
        background-color: #4cae4c;
    }
    .info-link-section a {
        color: #007bff; /* Màu riêng cho link Đăng ký */
    }
    
    /* CSS cho hộp thông báo lỗi */
    .login-error-box {
        color: red;
        margin-bottom: 15px; 
        border: 1px solid red; 
        padding: 10px; 
        background-color: #ffebeb;
        border-radius: 4px;
        font-size: 0.9em;
        text-align: center;
    }
</style>
</head>
<body>

<div class="form-container">
    <h2>Đăng Nhập Hệ Thống</h2>
    
    <%-- ----------------------------------------------------------------- --%>
    <%-- LÓGIC HIỂN THỊ LỖI KHI SERVLET FORWARD TRỞ LẠI --%>
    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { 
    %>
        <div class="login-error-box">
            <%= errorMessage %> 
        </div>
    <% 
        } 
    %>
    <%-- ----------------------------------------------------------------- --%>
    
    <form id="loginForm" action="LoginServlet" method="post">
    
    	<label for="username">Tên đăng nhập:</label>
    	<input type="text" id="username" name="username"> 
    
    	<label for="password">Mật khẩu:</label>
    	<input type="password" id="password" name="password">
    
    	<input type="submit" value="Đăng Nhập">
    
	</form>
    
    <div class="info-link-section">
        Chưa có tài khoản? 
        <a href="Register.jsp">Đăng ký ngay!</a>
    </div>
</div>

<script src="Public/Assets/JS/Auth/Login.js?v=1.1"></script>

</body>
</html>