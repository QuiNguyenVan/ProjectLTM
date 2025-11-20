package Model.BO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import Model.Bean.Task;
import Model.DAO.PlagiarismDAO;
import Model.DAO.TaskDAO;
import jakarta.servlet.http.Part;

public class TaskBO {
	private TaskDAO dao = new TaskDAO();

    public int createTask(int userId,String fileName,Part filePart, String textInput) throws IOException {
        String content = "";

        if(filePart != null && filePart.getSize() > 0) {
            try (InputStream is = filePart.getInputStream()) {
                content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } else {
            content = textInput;
            fileName =null;
        }
        System.out.print(content);
        Task task = new Task();
        task.setUserId(userId);
        task.setFileName(fileName);
        task.setFileContent(content);
        task.setStatus("processing");

        return dao.createTask(task); 
    }
    public List<Task> getPendingTasks() {
        return dao.getPendingTasks();
    }
    public void updateTaskStatus(int taskId, String status) {
        dao.updateTaskStatus(taskId, status);
    }
    public String getTaskStatus(int taskId)
    {
    	return dao.getTaskById(taskId).getStatus();
    }
    
}
