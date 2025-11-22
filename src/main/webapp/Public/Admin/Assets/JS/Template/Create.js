// Create.js

document.addEventListener('DOMContentLoaded', function() {
    
    const createForm = document.getElementById('createForm');
    const titleInput = document.getElementById('doc_title');
    const contentInput = document.getElementById('doc_content');
    
    // Các phần tử hiển thị lỗi
    const titleErrorSpan = document.getElementById('title_error');
    const contentErrorSpan = document.getElementById('content_error');

    if (createForm) {
        createForm.addEventListener('submit', function(event) {
            
            event.preventDefault(); 
            clearErrorMessages();

            if (validateCreateForm()) {
                console.log("Xác thực thành công. Đang gửi tài liệu...");
                this.submit(); 
            } else {
                console.log("Xác thực thất bại.");
            }
        });
    }

    // Hàm xóa lỗi (đặt ở phạm vi toàn cục hoặc trong DOMContentLoaded)
    function clearErrorMessages() {
        titleErrorSpan.textContent = '';
        contentErrorSpan.textContent = '';
    }

    // Hàm chính xác thực form
    function validateCreateForm() {
        let isValid = true;
        const titleValue = titleInput.value.trim();
        const contentValue = contentInput.value.trim();

        // --- Kiểm tra Tiêu đề ---
        if (titleValue.length < 5) {
            displayError(titleErrorSpan, titleInput, 'Tiêu đề phải có ít nhất 5 ký tự.');
            isValid = false;
        } else if (titleValue.length > 255) {
             displayError(titleErrorSpan, titleInput, 'Tiêu đề không được vượt quá 255 ký tự.');
            isValid = false;
        }

        // --- Kiểm tra Nội dung ---
        if (contentValue.length < 50) {
            displayError(contentErrorSpan, contentInput, 'Nội dung phải có ít nhất 50 ký tự.');
            isValid = false;
        }
        
        return isValid;
    }

    // Hàm hiển thị thông báo lỗi
    function displayError(errorElement, inputElement, message) {
        errorElement.textContent = message;
        inputElement.focus();
    }

});