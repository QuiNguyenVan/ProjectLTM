package Worker;

import java.util.concurrent.*;
import java.util.List;

import Model.BO.PlagiarismBO;
import Model.BO.ResultBO;
import Model.BO.TaskBO;
import Model.Bean.Task;
import Model.Bean.Result;

public class PlagiarismWorker implements Runnable {

    private volatile boolean running = true;

    private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private ExecutorService executor;

    private PlagiarismBO plagiarismBO = new PlagiarismBO();
    private ResultBO resultBO = new ResultBO();
    private TaskBO taskBO = new TaskBO();

    public PlagiarismWorker(int poolSize) {
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public void stop() {
        running = false;
        executor.shutdown();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Task task = taskQueue.take();
                executor.submit(() -> processTask(task));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processTask(Task task) {
        try {
            Result result = plagiarismBO.checkPlagiarism(task.getTaskId(), task.getFileContent());

            resultBO.saveResult(result);

            taskBO.updateTaskStatus(task.getTaskId(), "done");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                taskBO.updateTaskStatus(task.getTaskId(), "error");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
