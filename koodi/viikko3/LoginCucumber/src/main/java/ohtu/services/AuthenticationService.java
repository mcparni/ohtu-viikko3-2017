package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }
	
	private boolean validUsername(String username) {
		return Pattern.matches("^[a-z-]{3,}$", username);
	}
	
	private boolean validPassword(String password) {
		return Pattern.matches("^[\\w\\W](?=.*[\\W\\d]).{8,}$", password);
	}

    private boolean invalid(String username, String password) {
		if(validUsername(username) && validPassword(password))
			return false;
		else
			return true;
		
    }
}
