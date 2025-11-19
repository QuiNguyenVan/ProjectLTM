<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chào Mừng</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #e9ecef;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        text-align: center;
    }
    .welcome-box {
        background-color: #fff;
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    }
    h1 {
        color: #007bff;
    }
    p {
        font-size: 1.2em;
        color: #333;
    }
    .logout-link {
        margin-top: 20px;
        display: block;
        color: #dc3545;
        text-decoration: none;
    }
</style>
</head>
<body>

<%
    // Lấy giá trị của trường 'username' đã được gửi từ form (login.jsp)
    // request.getParameter() là phương thức quan trọng trong Servlet/JSP
    String username = request.getParameter("username");
    
    // Lưu ý: Không nên hiển thị trực tiếp mật khẩu đã gửi!
    // String password = request.getParameter("password"); 
%>

<div class="welcome-box">
    <h1>Chào Mừng, <%= username %>! 👋</h1>
    <p>Bạn đã đăng nhập thành công vào hệ thống.</p>
    
    <a href="Login.jsp" class="logout-link">Đăng Xuất</a>
</div>

</body>
</html>