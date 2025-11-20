<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Đăng Ký</title>
<link rel="stylesheet" href="Public/Assets/CSS/Style.css"> 
<style>
    /* CSS RIÊNG CỦA REGISTER */
    .form-container {
        width: 350px; 
    }
    input[type="submit"] {
        width: 100%;
        background-color: #007bff; 
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        margin-top: 10px;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .info-link-section a {
        color: #5cb85c; 
    }
    /* Thêm style cho thông báo lỗi */
    .error-message { 
        /* Nếu bạn có style chung trong Style.css thì không cần định nghĩa lại */
    }
</style>
</head>
<body>

<div class="form-container">
    <h2>Đăng Ký Tài Khoản Mới</h2>
    
    <form id="registerForm" action="RegisterServlet" method="post">
        
        <label for="reg_username">Tên đăng nhập:</label><input type="text" id="reg_username" name="username"> 
       <!--   <label for="reg_email">Email:</label> <input type="email" id="reg_email" name="email"> -->
        <label for="reg_password">Mật khẩu:</label> <input type="password" id="reg_password" name="password"> 
        <label for="reg_confirm_password">Xác nhận Mật khẩu:</label><input type="password" id="reg_confirm_password" name="confirm_password"> 
        <input type="submit" value="Đăng Ký">
        
    </form>
    
    <div class="info-link-section">
        Đã có tài khoản? <a href="Login.jsp">Đăng nhập</a>
    </div>
</div>
<script src="Public/Assets/JS/Auth/Register.js?v=1.2"></script>

</body>
</html>