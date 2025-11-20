<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kiểm Tra Đạo Văn - Trang Chủ</title>
<link rel="stylesheet" href="Public/Assets/CSS/Style.css">
<style>
    /* ... (CSS giữ nguyên) ... */
    /* Bổ sung/Ghi đè style cho Home.jsp */
    body {
        /* Đặt body về kiểu hiển thị bình thường cho trang lớn */
        display: block; 
        height: auto;
        min-height: 100vh;
        padding: 20px;
        background-color: #e9ecef;
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
        max-width: 900px;
        margin: 0 auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }
    h1 {
        margin: 0;
        font-size: 2em;
    }
    .tab-nav {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
        border-bottom: 2px solid #ccc;
    }
    .tab-nav button {
        background-color: transparent;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        font-size: 1.1em;
        color: #6c757d;
        border-bottom: 3px solid transparent;
        transition: all 0.3s;
    }
    .tab-nav button.active {
        color: #007bff;
        border-bottom: 3px solid #007bff;
        font-weight: bold;
    }
    /* Input Area */
    textarea {
        width: 100%;
        min-height: 300px;
        padding: 15px;
        border: 1px solid #ced4da;
        border-radius: 4px;
        resize: vertical;
        font-size: 16px;
        box-sizing: border-box;
    }
    /* File Upload Area */
    .file-upload-area {
        border: 2px dashed #007bff;
        padding: 40px;
        text-align: center;
        border-radius: 6px;
        background-color: #f8f9fa;
        cursor: pointer;
    }
    .file-upload-area:hover {
        background-color: #e2e6ea;
    }
    .file-upload-area input[type="file"] {
        display: none;
    }
    .check-button {
        display: block;
        width: 100%;
        padding: 15px;
        margin-top: 20px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 1.2em;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    .check-button:hover {
        background-color: #218838;
    }
    .hidden {
        display: none;
    }
</style>
</head>
<body>

<div class="header">
    <h1>Công Cụ Kiểm Tra Đạo Văn (Plagiarism Checker)</h1>
    <p>Nhập văn bản hoặc tải lên file để bắt đầu kiểm tra.</p>
</div>

<div class="main-content">
    
    <div class="tab-nav">
        <button id="tab-text" class="active">Nhập Văn Bản</button>
        <button id="tab-file">Tải Lên File</button>
    </div>
    
    <form action="CheckPlagiarismServlet" method="post" enctype="multipart/form-data">

        <div id="text-input">
            <label for="text_content">Dán văn bản của bạn vào đây:</label>
            <textarea id="text_content" name="content" placeholder="Bắt đầu nhập hoặc dán nội dung văn bản..."></textarea>
        </div>

        <div id="file-upload" class="hidden">
            <label for="file_input">Chọn file để kiểm tra (hỗ trợ .doc, .docx, .txt, .pdf):</label>
            <div class="file-upload-area"> 
                <p>Kéo và thả file vào đây, hoặc nhấn để chọn file</p>
                <input type="file" id="file_input" name="file" accept=".doc,.docx,.txt,.pdf">
            </div>
            <p id="file-name-display" style="margin-top: 10px; color: #6c757d;"></p>
        </div>
        
        <button type="submit" class="check-button">Kiểm Tra Đạo Văn Ngay!</button>
        
    </form>
    
</div>

<script src="Public/Assets/JS/Home/Home.js"></script>

</body>
</html>