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
	
	private String invalidUsername(String username) {
		String error = "";
		if(username.length() < 3) {
			error = "username should have at least 3 characters";
		} else if(!Pattern.matches("^[a-z-]{3,}$", username)) {
			error = "username should have only letter from a-z";
		}
		return error;
	}

	private String invalidPassword(String password) {
		String error = "";
		if(password.length() < 8) {
			error = "password should have at least 8 characters";
		} else if(!Pattern.matches("^[\\w\\W](?=.*[\\W\\d]).{8,}$", password)) {
			error = "password should have at least 8 characters and at least one nonword character (!#._ . . .) or at least one number";
		} 
		return error;
	}

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
		String invalidPass = invalidPassword(password);
		String invalidUser = invalidUsername(username);
		
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (invalidPass.length() > 0) {
            status.addError(invalidPass);
        }
		
		if (invalidUser.length() > 0) {
            status.addError(invalidUser);
        }
			
		
		if(!password.equals(passwordConfirmation)) {
			status.addError("password and password confirmation do not match");
		}

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
