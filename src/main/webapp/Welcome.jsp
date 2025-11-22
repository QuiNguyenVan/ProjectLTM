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
    String username = request.getParameter("username");
%>

<div class="welcome-box">
    <h1>Chào Mừng, <%= username %>! 👋</h1>
    <p>Bạn đã đăng nhập thành công vào hệ thống.</p>
    <a href="HomeServlet" >Kiểm tra đạo văn ở đây!</a>
    <a href="LogoutServlet" class="logout-link">Đăng Xuất</a>
</div>

</body>
</html>