// Register.jsp	

// register_validate.js

document.addEventListener('DOMContentLoaded', function() {
    // 1. Lấy form và các trường input (Sử dụng ID đã định nghĩa trong Register.jsp)
    const registerForm = document.getElementById('registerForm');
	
	// 2. Lấy các trường input
    const usernameInput = document.getElementById('reg_username');
    const passwordInput = document.getElementById('reg_password');
    const confirmPasswordInput = document.getElementById('reg_confirm_password');
    
    if (registerForm) {
        registerForm.addEventListener('submit', function(event) {
            // Ngăn form gửi đi mặc định
            event.preventDefault(); 
            
            clearErrorMessages();

            // Thực hiện xác thực
            if (validateRegistrationForm()) {
                // Nếu xác thực thành công, cho phép form gửi đi
                this.submit(); 
            }
        });
    }

    // 2. Hàm chính xác thực form Đăng ký
    function validateRegistrationForm() {
        let isValid = true;

        // --- Kiểm tra Tên đăng nhập ---
        const usernameValue = usernameInput.value.trim();
        if (usernameValue.length < 4) {
            displayError(usernameInput, 'Tên đăng nhập phải có ít nhất 4 ký tự.');
            isValid = false;
        }

        // --- Kiểm tra Mật khẩu ---
        const passwordValue = passwordInput.value;
        if (passwordValue.length < 6) {
            displayError(passwordInput, 'Mật khẩu phải có ít nhất 6 ký tự.');
            isValid = false;
        }

        // --- Kiểm tra Xác nhận Mật khẩu ---
        const confirmPasswordValue = confirmPasswordInput.value;
        if (confirmPasswordValue === "") {
            displayError(confirmPasswordInput, 'Vui lòng xác nhận mật khẩu.');
            isValid = false;
        } else if (passwordValue !== confirmPasswordValue) {
            displayError(confirmPasswordInput, 'Mật khẩu xác nhận không khớp.');
            isValid = false;
        }
        
        return isValid;
    }

    // 3. Hàm hiển thị thông báo lỗi (Dùng lại style từ Login)
    function displayError(inputElement, message) {
        let errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.style.color = 'red';
        errorDiv.style.fontSize = '0.9em';
        errorDiv.style.marginTop = '-10px';
        errorDiv.style.marginBottom = '10px';
        errorDiv.textContent = message;

        inputElement.parentNode.insertBefore(errorDiv, inputElement.nextSibling);
        // Chỉ focus vào trường lỗi đầu tiên
        if (isValid) inputElement.focus();
    }
    
    // 4. Hàm xóa thông báo lỗi cũ
    function clearErrorMessages() {
        const errors = document.querySelectorAll('.error-message');
        errors.forEach(error => error.remove());
    }

});

// End register.jsp	
