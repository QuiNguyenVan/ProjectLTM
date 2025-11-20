package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import Model.BO.PlagiarismBO;
import Model.Bean.Result;

@WebServlet("/CheckPlagiarismServlet")
@MultipartConfig
public class CheckPlagiarismServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = "";

        // Lấy nội dung từ file hoặc textarea
        Part filePart = request.getPart("file");
        if(filePart != null && filePart.getSize() > 0) {
            InputStream is = filePart.getInputStream();
            content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } else {
            content = request.getParameter("content");
        }

        // taskId tạm thời có thể random hoặc auto increment
        int taskId = (int)(System.currentTimeMillis()/1000); // ví dụ dùng timestamp

        PlagiarismBO bo = new PlagiarismBO();
        Result result = bo.checkPlagiarism(taskId, content);

        // Chuyển sang JSP hiển thị kết quả
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("Check_Result.jsp");
        rd.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
}
