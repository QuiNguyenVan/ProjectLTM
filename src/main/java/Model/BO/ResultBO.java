package Model.BO;

import Model.Bean.Result;
import Model.DAO.ResultDAO;

public class ResultBO {

    private ResultDAO dao = new ResultDAO();

    public void saveResult(Result result) {
         dao.saveResult(result);
    }

    public Result getResultByTaskId(int taskId) {
        return dao.getResultByTaskId(taskId);
    }
}
