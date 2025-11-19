// Home.jsp

// Hàm chuyển đổi giữa tab Nhập Văn Bản và Tải Lên File
function showTab(tabId) {
    const textInput = document.getElementById('text-input');
    const fileUpload = document.getElementById('file-upload');
    const textContent = document.getElementById('text_content');
    const fileInput = document.getElementById('file_input');
    const tabText = document.getElementById('tab-text');
    const tabFile = document.getElementById('tab-file');

    if (tabId === 'text-input') {
        textInput.classList.remove('hidden');
        fileUpload.classList.add('hidden');
        tabText.classList.add('active');
        tabFile.classList.remove('active');
        
        // CHỈ ĐẶT 'REQUIRED' CHO TRƯỜNG ĐANG HOẠT ĐỘNG
        textContent.setAttribute('required', 'required');
        
        // XÓA 'REQUIRED' HOÀN TOÀN KHỎI TRƯỜNG BỊ ẨN
        fileInput.removeAttribute('required');

    } else if (tabId === 'file-upload') {
        textInput.classList.add('hidden');
        fileUpload.classList.remove('hidden');
        tabText.classList.remove('active');
        tabFile.classList.add('active');
        
        // CHỈ ĐẶT 'REQUIRED' CHO TRƯỜNG ĐANG HOẠT ĐỘNG
        fileInput.setAttribute('required', 'required');
        
        // XÓA 'REQUIRED' HOÀN TOÀN KHỎI TRƯỜNG BỊ ẨN
        textContent.removeAttribute('required');
    }
}

// Hàm khởi tạo các sự kiện khi DOM đã tải xong
document.addEventListener('DOMContentLoaded', () => {
    // 1. Khởi tạo tab mặc định
    showTab('text-input');
    
    // 2. Lắng nghe sự kiện click cho các nút tab
    document.getElementById('tab-text').addEventListener('click', () => showTab('text-input'));
    document.getElementById('tab-file').addEventListener('click', () => showTab('file-upload'));

    // 3. Hiển thị tên file khi người dùng chọn file
    const fileInput = document.getElementById('file_input');
    const fileNameDisplay = document.getElementById('file-name-display');

    fileInput.addEventListener('change', function() {
        if (this.files.length > 0) {
            fileNameDisplay.textContent = 'File đã chọn: ' + this.files[0].name;
        } else {
            fileNameDisplay.textContent = '';
        }
    });
    
    // 4. Kích hoạt hộp thoại chọn file khi click vào khu vực kéo thả (FILE UPLOAD AREA FIX)
    const fileUploadArea = document.querySelector('.file-upload-area');
    fileUploadArea.addEventListener('click', function() {
        fileInput.click();
    });
});

// End home.jsp