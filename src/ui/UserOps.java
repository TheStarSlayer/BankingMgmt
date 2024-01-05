package ui;

import javax.swing.JOptionPane;
import java.util.ArrayList;

class UserOps {
	
	public void greet(LoginRegister user) {
		
		String[] option = {"Show info", "Change name", "Change mail",
				"Withdraw", "Deposit", "Logout"};
		
		int choice = 0;
		while (choice != 5) {
			
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
					withdraw(user);
					break;
				case 4:
					deposit(user);
					break;
				case 5:
					return;
			}
		}
	}

	public void userInfo(LoginRegister user) {
		JOptionPane.showMessageDialog(null, "Name: "+ user.name + "\nE-mail: "
				+user.email+"\nBalance: "+ user.balance);
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

	public void withdraw(LoginRegister user) {
		if (pinCheck(user) == true) {
			double amt = Double.parseDouble(JOptionPane.showInputDialog("Balance: " + user.balance
					+ "\nEnter withdraw amount"));
			
			if (user.balance - amt < 0) {
				JOptionPane.showMessageDialog(null, "<html><span style='color:red'>Balance not sufficient</span></html>");
				return;
			}
			else {
				user.balance -= amt;
				JOptionPane.showMessageDialog(null, "Current balance: "+ user.balance);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "<html><span style='color:red'>PIN not correct!!</span></html>");
		}
	}
	
	public void deposit(LoginRegister user) {
		if (pinCheck(user) == true) {
			double amt = Double.parseDouble(JOptionPane.showInputDialog("Balance: " + user.balance
					+ "\nEnter deposit amount"));
			
			user.balance += amt;
			JOptionPane.showMessageDialog(null, "Current balance: "+ user.balance);
		}
		else {
			JOptionPane.showMessageDialog(null, "<html><span style='color:red'>PIN not correct!!</span></html>");
		}
	}
	
	public boolean pinCheck(LoginRegister user) {
		int pin = Integer.parseInt(JOptionPane.showInputDialog("Enter PIN"));
		if (pin == user.pin)
			return true;
		return false;
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