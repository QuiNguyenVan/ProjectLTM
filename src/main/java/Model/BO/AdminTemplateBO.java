package Model.BO;

import java.util.List;

import Model.Bean.AdminTemplate;
import Model.DAO.AdminTemplateDAO;

public class AdminTemplateBO {
	AdminTemplateDAO templateDAO = new AdminTemplateDAO();
	public List<AdminTemplate> retrieveAllTemplates() {
        return templateDAO.getAllTemplates();
    }
}
