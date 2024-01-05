package ui;

import javax.swing.JOptionPane;
import java.util.ArrayList;

class UserOps {
	
	public void greet(LoginRegister user) {
		
		String[] option = {"Show info", "Change name", "Change mail",
				"Change DOB", "Logout"};
		
		int choice = 0;
		while (choice != 4) {
			
			choice = JOptionPane.showOptionDialog(null, "Choose an option", "Hello, "+ user,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
					option, option[0]);
			
			switch (choice) {
				case 0 :
					userInfo(user);
					break;
				case 1:
					changeName(user);
					break;
				case 2:
					changeMail(user);
					break;
				case 3:
					changeDOB(user);
					break;
				case 4:
					return;
			}
		}
	}

	public void userInfo(LoginRegister user) {
		JOptionPane.showMessageDialog(null, "Name: "+ user.name + "\nE-mail: "
				+user.email+"\nDOB: "+ user.dob);
	}

	public void changeName(LoginRegister user) {
		String newName = JOptionPane.showInputDialog("Enter new name");
		if (newName != null) {
			user.name = newName;
			JOptionPane.showMessageDialog(null, "<html><span style='color:green'>User details updated</span></html>");
		}
		else
			return;
	}
		

	public void changeMail(LoginRegister user) {
		String newMail = JOptionPane.showInputDialog("Enter new mailID");
		if (newMail != null) {
			user.email = newMail;
			JOptionPane.showMessageDialog(null, "<html><span style='color:green'>User details updated</span></html>");
		}
		else
			return;
	}

	public void changeDOB(LoginRegister user) {
		String newDOB = JOptionPane.showInputDialog("Enter new date of birth");
		if (newDOB != null) {
			user.dob = newDOB;
			JOptionPane.showMessageDialog(null, "<html><span style='color:green'>User details updated</span></html>");
		}
		else
			return;
	}

}

class Database {
	private ArrayList<String> users = new ArrayList<String>();
	private ArrayList<String> passwd = new ArrayList<String>();
	
	public void addUser(String user, String passwd) {
		users.add(user);
		this.passwd.add(passwd);
	}
	
	public int checkUser(String user) {
		for (int i = 0; i < this.users.size(); i++) {
			if (user.equals(this.users.get(i)) == true)
				return i;
		}
		return -1;
	}

	public boolean checkPasswd(int i, String passwd) {
		if (passwd.equals(this.passwd.get(i)) == true)
			return true;
		return false;
	}
	
	public int getNext() {
		return users.size();
	}
}