package ohtu.authentication;

import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

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
	
	private boolean validUsername(String username) {
		return Pattern.matches("^[a-z-]{3,}$", username);
	}
	
	private boolean validPassword(String password) {
		return Pattern.matches("^[\\w\\W](?=.*[\\W\\d]).{8,}$", password);
	}

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (!validUsername(username)) {
            status.addError("username should have at least 3 characters consisting characters from a-z");
        }
		
		if (!validPassword(password)) {
			status.addError("username should have at least 8 characters and at least one nonword character (!#._ . . .) or at least one number");
		}
		
		if(!password.equals(passwordConfirmation)) {
			status.addError("passwords should match");
		}

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
