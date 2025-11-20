package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import Model.BO.PlagiarismBO;
import Model.BO.TaskBO;
import Model.Bean.Result;
import Model.Bean.User;

@WebServlet("/CheckPlagiarismServlet")
@MultipartConfig
public class CheckPlagiarismServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int userId = ((User)(request.getSession().getAttribute("user"))).getId();
        Part filePart = request.getPart("file");
        String fileName = (filePart != null && filePart.getSize() > 0) ? filePart.getSubmittedFileName() : null;
        String textInput = request.getParameter("content");
        
        TaskBO taskBO = new TaskBO();
        int taskId = taskBO.createTask(userId,fileName,filePart, textInput);

        response.sendRedirect("Processing.jsp?taskId=" + taskId);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
}
