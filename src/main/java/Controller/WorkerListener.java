package Controller;

import java.util.List;

import Model.BO.TaskBO;
import Model.Bean.Task;
import Worker.PlagiarismWorker;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WorkerListener implements ServletContextListener {
	private PlagiarismWorker worker;
	private Thread workerThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	worker = new PlagiarismWorker(5); // Thread pool 5

        // Lấy tất cả task chưa xong từ DB
        TaskBO taskBO = new TaskBO();
        List<Task> processingTasks = taskBO.getPendingTasks();

        processingTasks.forEach(worker::addTask);

        // Start Worker
        workerThread = new Thread(worker);
        workerThread.start();
        sce.getServletContext().setAttribute("plagiarismWorker", worker);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();;
        }
    }
	
}
