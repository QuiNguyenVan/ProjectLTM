package Model.BO;

import java.util.List;

import Model.Bean.AdminTemplate;
import Model.DAO.AdminTemplateDAO;

public class AdminTemplateBO {
	AdminTemplateDAO templateDAO = new AdminTemplateDAO();
	
	public List<AdminTemplate> retrieveAllTemplates() {
        return templateDAO.getAllTemplates();
    }
	
	public boolean createTemplate(int userId , String templateName, String content) {
		return templateDAO.insertTemplate(userId ,templateName, content);
	}
	
	public AdminTemplate getTemplateById(int templateId) {
		return templateDAO.getTemplateById(templateId);
	}
	
	public boolean updateTemplate(int templateId, String templateName, String content) {
		return templateDAO.updateTemplateById(templateId, templateName, content);
	}
	
	public boolean deleteTemplateById(int templateId) {
		return templateDAO.deleteTemplateById(templateId);
	}
}
