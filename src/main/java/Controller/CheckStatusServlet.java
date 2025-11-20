package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Model.BO.ResultBO;
import Model.BO.TaskBO;
import Model.Bean.Result;

@WebServlet("/CheckStatusServlet")
public class CheckStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckStatusServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int taskId = Integer.parseInt(request.getParameter("taskId"));
	        TaskBO taskBO = new TaskBO();
	        String status = taskBO.getTaskStatus(taskId); // trả về "processing" hoặc "done"
	        ResultBO resultBO = new ResultBO();
	        Result result = resultBO.getResultByTaskId(taskId);
	        double similarity = (result != null) ? result.getSimilarityPercent() : 0;
	        String matched = (result != null && result.getMatchedTemplate() != null) ? result.getMatchedTemplate() : "";
	        response.setContentType("application/json");
	        response.getWriter().write("{"
	                + "\"status\":\"" + status + "\","
	                + "\"similarityPercent\":" + similarity + ","
	                + "\"matchedTemplate\":\"" + matched + "\""
	                + "}");
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
