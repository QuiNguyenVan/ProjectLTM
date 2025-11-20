package Model.BO;

import Model.Bean.User;
import Model.DAO.UserDAO;

public class UserBO {
    UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.checkLogin(username, password);
    }

    public boolean register(String username, String password) {
    	if(userDAO.isExistUser(username))
    	{
    		return false;
    	}
        return userDAO.registerUser(username, password);
    }
}
