package app;
import java.util.Random;

import controllers.UserController;
import models.User;
import util.DatabaseConn;
import util.EmailUtil;
import view.LoginForm;

public class StartUp {

	public static void main(String[] args) {
	       new Thread(() -> {
	    	   DatabaseConn conn =   DatabaseConn.getInstance();
// 	    	   conn.execAction("DELETE FROM users");
//	    	   conn.execAction("DROP TABLE users");
 	            new LoginForm();
	        }).start();

	}

}
