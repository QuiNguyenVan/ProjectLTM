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
    String username;
    if (currentUser != null) {
        username = currentUser.getUsername();
    } else {
        username = "Người dùng";
    }
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

            %>
                    <tr>
                        <td><%= task.getTaskId() %></td>
                        <td><%= task.getFileName() %></td>
                        <td>
                            <%= dateFormat.format(task.getCreatedAt()) %>
                        </td>
                        <td>
                        	<%=status %>
                        </td>
                        <td>
                                <a href="StatusResultServlet?taskId=<%= task.getTaskId() %>" class="result-btn">
                                    Xem Kết Quả
                                </a>
                        </td>
                    </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>
    
</div>

<script>
    // ----------------------------------------------------
    // LOGIC AJAX POLLING
    // ----------------------------------------------------

    const tableBody = document.getElementById('taskTableBody');
    const dateFormatOptions = { 
        hour: '2-digit', 
        minute: '2-digit', 
        day: '2-digit', 
        month: '2-digit', 
        year: 'numeric' 
    };

    // Hàm chuyển đổi Timestamp từ JSON (mili giây) sang định dạng dễ đọc
    function formatDate(timestamp) {
        if (!timestamp) return '';
        const date = new Date(timestamp); 
        // Thay thế dấu phẩy (nếu có) để phù hợp với định dạng bạn dùng
        return date.toLocaleString('vi-VN', dateFormatOptions).replace(',', ''); 
    }

    // Hàm chuyển đổi trạng thái tiếng Anh sang tiếng Việt (đồng bộ với Java Scriptlet)
    function getStatusText(status) {
        switch (status) {
            case 'PENDING':
                return 'Đang Chờ';
            case 'PROCESSING':
                return 'Đang Xử Lý...';
            case 'COMPLETED':
                return 'Hoàn Thành';
            default:
                return 'Lỗi Xử Lý';
        }
    }

    // Hàm chính thực hiện Polling
    function fetchTaskHistory() {
        // Gọi đến TaskStatusController (Endpoint trả về JSON)
        fetch('TaskStatusController') 
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(tasks => {
                let html = '';
                
                if (tasks.length === 0) {
                     // Hiển thị thông báo không có task nếu danh sách rỗng
                     html = `<tr>
                                <td colspan="5" style="text-align: center; padding: 20px; color: #6c757d;">
                                    Bạn chưa có Task nào được tạo.
                                </td>
                            </tr>`;
                } else {
                    // Xây dựng lại các hàng (<tr>) dựa trên dữ liệu JSON mới
                    tasks.forEach(task => {
                        const status = task.status;
                        const statusText = getStatusText(status);
                        const statusClass = `status-${status.toLowerCase()}`;
                        const isCompleted = status === 'COMPLETED';

                        html += `<tr>
                                    <td>\${task.taskId}</td>
                                    <td>\${task.fileName}</td>
                                    <td>\${formatDate(task.createdAt)}</td>
                                    <td><span class="\${statusClass}">\${statusText}</span></td>
                                    <td>
                                        \${isCompleted 
                                            ? `<a href="ResultController?taskId=\${task.taskId}" class="result-btn">Xem Kết Quả</a>`
                                            : `<button class="result-btn disabled" disabled>\${statusText}</button>`}
                                    </td>
                                </tr>`;
                    });
                }
                
                // Cập nhật nội dung bảng
                tableBody.innerHTML = html;
            })
            .catch(error => {
                console.error('Lỗi tải Task History:', error);
                // Có thể hiển thị thông báo lỗi nhỏ trên màn hình
            });
    }


</script>

</body>
</html>