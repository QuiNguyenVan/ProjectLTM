package Filter; 

import java.io.IOException;

// THAY ĐỔI: Sử dụng thư viện Jakarta thay thế cho javax
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Model.Bean.User; // Giả định class User của bạn nằm ở đây

//Giả định các import đã đúng (sử dụng jakarta.servlet.*)
//Lưu ý: Cần thêm import java.io.IOException; và jakarta.servlet.*

@WebFilter("/Admin/*") 
public class AuthenticationFilter implements Filter {

 // THAY ĐỔI: Đổi INDEX_URL thành HOME_URL để chuyển hướng người không có quyền
 private static final String HOME_URL = "/Home.jsp"; 
 private static final String LOGIN_URL = "/Login.jsp"; 

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {

     // ... (Phần Ép kiểu và lấy Session giữ nguyên) ...
     HttpServletRequest httpRequest = (HttpServletRequest) request;
     HttpServletResponse httpResponse = (HttpServletResponse) response;
     HttpSession session = httpRequest.getSession(false); 
     String contextPath = httpRequest.getContextPath();
     
     // 1. KIỂM TRA XÁC THỰC (AUTHENTICATION)
     if (session == null || session.getAttribute("user") == null) {
         httpResponse.sendRedirect(contextPath + LOGIN_URL); 
         return; 
     }
     
     User user = (User) session.getAttribute("user");
     
     // 2. KIỂM TRA PHÂN QUYỀN (AUTHORIZATION / ROLE CHECK)
     // Nếu người dùng KHÔNG PHẢI là Admin (role != 1)
     if (user.getRole() != 1) { 
         
         // CHUYỂN HƯỚNG NGƯỜI DÙNG VỀ TRANG AN TOÀN (HOME_URL)
         httpResponse.sendRedirect(contextPath + HOME_URL); 
         return; 
     }
     
     // Nếu Admin (role == 1) hoặc đã đăng nhập, cho phép tiếp tục đến tài nguyên đích
     // Đây là điểm mà Admin được phép vào khu vực /Admin/*
     chain.doFilter(request, response);
 }
 // ... (Các phương thức init và destroy) ...
 public void init(FilterConfig fConfig) throws ServletException {}
 public void destroy() {}
}