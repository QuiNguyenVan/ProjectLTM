// Login.js

document.addEventListener('DOMContentLoaded', function() {
    
    // 1. Lấy form bằng ID (Cách tối ưu hơn)
    const loginForm = document.getElementById('loginForm');
    
    // Lấy các trường input
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
	
    // 2. Thêm trình lắng nghe sự kiện 'submit'
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            
            // Ngăn form gửi đi mặc định
            event.preventDefault(); 
            
            // Xóa thông báo lỗi cũ
            clearErrorMessages();

            // Thực hiện xác thực
            if (validateLoginForm()) {
                // Nếu xác thực thành công, cho phép form gửi đến LoginServlet
                console.log("Xác thực thành công. Đang gửi form...");
                this.submit(); 
            } else {
                console.log("Xác thực thất bại.");
            }
        });
    }

    // 3. Hàm chính xác thực form
    function validateLoginForm() {
        let isValid = true;
        const usernameValue = usernameInput.value.trim();
        const passwordValue = passwordInput.value.trim();

        // --- Kiểm tra Tên đăng nhập ---
        if (usernameValue === "") {
            displayError(usernameInput, 'Tên đăng nhập không được để trống.');
            isValid = false;
        } else if (usernameValue.length < 4) {
             displayError(usernameInput, 'Tên đăng nhập phải có ít nhất 4 ký tự.');
             isValid = false;
        }

        // --- Kiểm tra Mật khẩu ---
        if (passwordValue === "") {
            displayError(passwordInput, 'Mật khẩu không được để trống.');
            isValid = false;
        } else if (passwordValue.length < 6) {
             displayError(passwordInput, 'Mật khẩu phải có ít nhất 6 ký tự.');
             isValid = false;
        }
        
        return isValid;
    }

    // 4. Hàm hiển thị thông báo lỗi
    function displayError(inputElement, message) {
        let errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        // Style CSS có thể được định nghĩa trong file Style.css chung
        errorDiv.style.color = 'red'; 
        errorDiv.style.fontSize = '0.9em';
        errorDiv.style.marginTop = '-10px';
        errorDiv.style.marginBottom = '10px';
        errorDiv.textContent = message;

        // Chèn thông báo lỗi ngay sau trường input
        inputElement.parentNode.insertBefore(errorDiv, inputElement.nextSibling);
        inputElement.focus();
    }
    
    // 5. Hàm xóa thông báo lỗi cũ
    function clearErrorMessages() {
        const errors = document.querySelectorAll('.error-message');
        errors.forEach(error => error.remove());
    }

});

// End login.js