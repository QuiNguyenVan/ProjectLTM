package Worker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import Model.BO.PlagiarismBO;
import Model.BO.ResultBO;
import Model.BO.TaskBO;
import Model.Bean.Task;
import Model.Bean.Result;

public class PlagiarismWorker implements Runnable {

    private boolean running = true;
    private TaskBO taskBO = new TaskBO();
    private ResultBO resultBO = new ResultBO();
    private PlagiarismBO plagiarismBO = new PlagiarismBO();

    // Khoảng thời gian sleep giữa các lần check DB (tính bằng giây)
    private int pollInterval = 5;

    @Override
    public void run() {
        while(running) {
            try {
                List<Task> tasks = taskBO.getPendingTasks();

                for(Task task : tasks) {
                    Result result = plagiarismBO.checkPlagiarism(task.getTaskId(), task.getFileContent());
                    resultBO.saveResult(result);
                    taskBO.updateTaskStatus(task.getTaskId(), "done");
                }
                // Nghỉ 5 giây trước khi check tiếp
                TimeUnit.SECONDS.sleep(pollInterval);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
