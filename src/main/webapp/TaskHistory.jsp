<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Bean.Task" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Bean.User" %>

<%
    // Khai báo các đối tượng cần thiết
    List<Task> taskList = (List<Task>) request.getAttribute("taskList");
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    
    // Lấy User từ Session để có thể hiển thị thông tin chào mừng (Tùy chọn)
    User currentUser = (User) session.getAttribute("user");
    String username = (currentUser != null) ? currentUser.getUsername() : "Người dùng";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch Sử Kiểm Tra - Plagiarism Checker</title>
<link rel="stylesheet" href="Public/Assets/CSS/Style.css">
<style>
    /* ---------------------------------------------------- */
    /* CSS CƠ BẢN ĐỒNG BỘ VỚI HOME.JSP */
    /* ---------------------------------------------------- */
    body {
        display: block; 
        min-height: 100vh;
        padding: 20px;
        background-color: #e9ecef;
        font-family: Arial, sans-serif;
    }
    .header {
        text-align: center;
        padding: 20px;
        background-color: #007bff;
        color: white;
        border-radius: 8px 8px 0 0;
        margin-bottom: 20px;
    }
    .main-content {
        max-width: 1200px;
        margin: 0 auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }
    h2 {
        color: #333;
        border-bottom: 2px solid #ccc;
        padding-bottom: 10px;
        margin-bottom: 20px;
        font-size: 1.8em;
    }
    /* ---------------------------------------------------- */
    /* CSS BẢNG VÀ TRẠNG THÁI TASK */
    /* ---------------------------------------------------- */
    table { 
        width: 100%; 
        border-collapse: separate; 
        border-spacing: 0;
        margin-top: 20px; 
        overflow: hidden; /* Dùng để bo góc */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
    }
    th, td { 
        padding: 15px; 
        text-align: left; 
        border-bottom: 1px solid #dee2e6;
    }
    th { 
        background-color: #007bff; /* Màu xanh đậm cho header */
        color: white; 
        font-weight: bold;
    }
    /* Bo góc cho header */
    thead tr:first-child th:first-child { border-top-left-radius: 6px; }
    thead tr:first-child th:last-child { border-top-right-radius: 6px; }

    /* Màu cho Trạng Thái */
    .status-pending { color: #ffc107; font-weight: bold; } /* Vàng */
    .status-processing { color: #007bff; font-weight: bold; } /* Xanh dương */
    .status-completed { color: #28a745; font-weight: bold; } /* Xanh lá */
    .status-failed { color: #dc3545; font-weight: bold; } /* Đỏ */

    /* Nút Hành Động */
    .result-btn { 
        padding: 8px 12px; 
        background-color: #28a745; 
        color: white; 
        border: none; 
        border-radius: 4px; 
        cursor: pointer; 
        text-decoration: none; 
        display: inline-block; 
        transition: background-color 0.3s;
        font-size: 0.9em;
    }
    .result-btn:hover:not(.disabled) {
        background-color: #218838;
    }
    .result-btn.disabled { 
        background-color: #6c757d; /* Xám */
        cursor: not-allowed; 
        opacity: 0.8;
    }
</style>
</head>
<body>

<div class="header">
    <h1>Lịch Sử Kiểm Tra Đạo Văn</h1>
    <p>Chào mừng <%= username %>! Theo dõi trạng thái và xem kết quả các tác vụ đã thực hiện.</p>
</div>

<div class="main-content">
    
    <h2>📜 Danh Sách Tác Vụ (Tasks)</h2>
    
    <p style="margin-bottom: 20px;"><a href="HomeServlet" style="text-decoration: none; color: #007bff;">&larr; Quay lại Trang Kiểm Tra</a></p>

    <table>
        <thead>
            <tr>
                <th>ID Task</th>
                <th>Tên File</th>
                <th>Thời Gian Tạo</th>
                <th>Trạng Thái</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody id = "taskTableBody">
            <% 
            if (taskList == null || taskList.isEmpty()) {
            %>
                <tr>
                    <td colspan="5" style="text-align: center; padding: 20px; color: #6c757d;">
                        Bạn chưa có Task nào được tạo.
                    </td>
                </tr>
            <% 
            } else {
                // Bắt đầu vòng lặp qua danh sách Task
                for (Task task : taskList) {
                    // Lấy các thuộc tính cần thiết
                    String status = task.getStatus();
                    String statusClass = "status-" + status.toLowerCase();
                    boolean isCompleted = status.equals("COMPLETED");
                    
                    // Xác định văn bản hiển thị cho trạng thái và nút hành động
                    String statusText;
                    if (status.equals("PENDING")) {
                        statusText = "Đang Chờ";
                    } else if (status.equals("PROCESSING")) {
                        statusText = "Đang Xử Lý...";
                    } else if (status.equals("COMPLETED")) {
                        statusText = "Hoàn Thành";
                    } else {
                        statusText = "Lỗi Xử Lý";
                    }
            %>
                    <tr>
                        <td><%= task.getTaskId() %></td>
                        <td><%= task.getFileName() %></td>
                        <td>
                            <%= dateFormat.format(task.getCreatedAt()) %>
                        </td>
                        <td>
                            <span class="<%= statusClass %>"><%= statusText %></span>
                        </td>
                        <td>
                            <% if (isCompleted) { %>
                                <a href="ResultController?taskId=<%= task.getTaskId() %>" class="result-btn">
                                    Xem Kết Quả
                                </a>
                            <% } else { %>
                                <button class="result-btn disabled" disabled>
                                    <%= statusText %>
                                </button>
                            <% } %>
                        </td>
                    </tr>
            <% 
                } // Kết thúc vòng lặp for
            } // Kết thúc if/else kiểm tra rỗng
            %>
        </tbody>
    </table>
    
</div>

</body>
</html>