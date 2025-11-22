<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đang kiểm tra đạo văn</title>
<style>
    body { font-family: Arial; text-align: center; padding: 50px; background-color: #f0f0f0; }
    .status { font-size: 1.5em; color: #007bff; margin-bottom: 20px; }
    .result { font-size: 1.2em; color: #28a745; }
</style>
<script>
    function checkStatus() {
        var taskId = "<%= request.getParameter("taskId") %>";
        fetch("CheckStatusServlet?taskId=" + taskId)
        .then(response => response.json())
        .then(data => {
            if(data.status === "done") {
                // Khi xong, hiển thị kết quả
                document.getElementById("status").innerText = "Đã kiểm tra xong!";
                document.getElementById("result").innerHTML =
                    "<p>Tỉ lệ giống: " + data.similarityPercent + "%</p>" +
                    "<p>Mẫu giống nhất: " + data.matchedTemplate + "</p>";
            } else {
                // Nếu chưa xong, tiếp tục polling
                setTimeout(checkStatus, 2000);
            }
        })
        .catch(err => console.error(err));
    }

    window.onload = checkStatus;
</script>
</head>
<body>
    <h1>Đang xử lý kiểm tra đạo văn...</h1>
    <p id="status" class="status">Vui lòng chờ trong giây lát.</p>
    <div id="result" class="result"></div>
    <input type="button" onclick=history.back() value="Quay lại">
</body>
</html>
