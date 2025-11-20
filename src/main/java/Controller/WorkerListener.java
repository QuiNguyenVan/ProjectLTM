package Controller;

import Worker.PlagiarismWorker;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WorkerListener implements ServletContextListener {
	private Thread workerThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PlagiarismWorker worker = new PlagiarismWorker();
        workerThread = new Thread(worker);
        workerThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();
        }
    }
	
}
