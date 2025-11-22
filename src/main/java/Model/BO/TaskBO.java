package Model.BO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import Model.Bean.Task;
import Model.DAO.PlagiarismDAO;
import Model.DAO.TaskDAO;
import Worker.PlagiarismWorker;
import jakarta.servlet.http.Part;

public class TaskBO {
	private TaskDAO taskDao = new TaskDAO();
	private PlagiarismWorker worker;
	public TaskBO(PlagiarismWorker worker) {
        this.worker = worker;
    }
	public TaskBO() {
    }
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
        Task task = new Task();
        task.setUserId(userId);
        task.setFileName(fileName);
        task.setFileContent(content);
        task.setStatus("processing");
        int taskId=taskDao.createTask(task);
        task.setTaskId(taskId);
        
        if (worker != null) {
            worker.addTask(task);
        }
        return taskId;
    }
    public List<Task> getPendingTasks() {
        return taskDao.getPendingTasks();
    }
    public void updateTaskStatus(int taskId, String status) {
        taskDao.updateTaskStatus(taskId, status);
    }
    public String getTaskStatus(int taskId)
    {
    	return taskDao.getTaskById(taskId).getStatus();
    }
    
}
