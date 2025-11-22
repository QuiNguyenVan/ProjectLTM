package Model.BO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import Model.DAO.PlagiarismDAO;

import Model.Utils.FingerPrinting;
import Model.Bean.Template;
import Model.Bean.Result;

public class PlagiarismBO {

    private PlagiarismDAO dao = new PlagiarismDAO();

    public Result checkPlagiarism(int taskId, String inputContent) {
        List<Template> templates = dao.getAllTemplates();

        double maxSimilarity = 0;
        String matchedTemplate = null;
        for(Template t : templates) {
        	
            double sim = similarity(inputContent, t.getContent());
            if(sim > maxSimilarity) {
                maxSimilarity = sim;
                matchedTemplate = t.getTemplateName();
            }
        }

        Result r = new Result();
        r.setTaskId(taskId);
        r.setSimilarityPercent(maxSimilarity);
        r.setMatchedTemplate(matchedTemplate);
        r.setCheckedAt(new Timestamp(System.currentTimeMillis()));
        return r;
    }
    private FingerPrinting fp = new FingerPrinting(5, 4);

    private double similarity(String a, String b) {
    	Set<Integer> f1 = fp.fingerprints(a);
        Set<Integer> f2 = fp.fingerprints(b);

        double sim = fp.similarity(f1, f2);

        return sim * 100;
    }
}
