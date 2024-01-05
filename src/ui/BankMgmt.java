package ui;

import java.util.ArrayList;
import javax.swing.JOptionPane;

class LoginRegister {
	
	private int i;
	protected String name, email;
	protected double balance;
	protected int pin = 0;
	private String userID, passwd;
	
	Database db = new Database();
	LoginRegister userInst;
	ArrayList<LoginRegister> inst = new ArrayList<LoginRegister>();
	UserOps uop = new UserOps();
	
	public void mainMenu() {
		
		String[] list = {"Login", "Register", "Exit"};
		while (i != 2) {
			i = JOptionPane.showOptionDialog(null, "Welcome!", 
					"Sign in", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, list, list[0]);
			
			if (i == 0)
				login();
			else if (i == 1) 
				register();
			else if (i == 2)
				JOptionPane.showMessageDialog(null, "Goodbye!");
		}
	}
	
	public void login() {
		if (db.getNext() == 0) {
			JOptionPane.showMessageDialog(null, "<html><span style='color:red'>Database empty!</span></html>");
			return;
		}
		else {
			userID = JOptionPane.showInputDialog("Enter your userID");
			passwd = JOptionPane.showInputDialog("Enter your password");
			if (userID == null || passwd == null) {
				JOptionPane.showMessageDialog(null, "<html><span style='color:red'>Cancelled</span></html>");
				return;
			}
			else {
				int instNo = db.checkUser(userID);
				if (instNo != -1) {
					if (db.checkPasswd(instNo, passwd) == true) {
						JOptionPane.showMessageDialog(null, "<html><span style='color:green'>Logged in successfully!</span></html>");
						uop.greet(inst.get(instNo));
					}
					else {
						JOptionPane.showMessageDialog(null, "<html><span style='color:red'>Wrong password!</span></html>");
						return;
					}
				} 
				else {
					JOptionPane.showMessageDialog(null, "<html><span style='color:red'>No such user exists</span></html>");
					return;
				}
			}
		}
	}
	
	public void register() {
		userInst = new LoginRegister();
		userInst.userID = JOptionPane.showInputDialog("Enter userID");
		userInst.passwd = JOptionPane.showInputDialog("Enter your password");
		
		if (userInst.userID != null && userInst.passwd != null) {
			int instNo = db.checkUser(userInst.userID);
			
			if (instNo == -1) {
				db.addUser(userInst.userID, userInst.passwd);
				JOptionPane.showMessageDialog(null, "<html><span style='color:green'>Registered successfully!!</span></html>");
				
				userInst.name = JOptionPane.showInputDialog("Enter name");
				userInst.email = JOptionPane.showInputDialog("Enter email");
				userInst.balance = Double.parseDouble(JOptionPane.showInputDialog("Enter balance"));
				
				int k = 1;
				while (k != userInst.pin) {
					userInst.pin = Integer.parseInt(JOptionPane.showInputDialog("Enter PIN"));
					k = Integer.parseInt(JOptionPane.showInputDialog("Re-enter PIN"));
					
					if (k != userInst.pin) {
						JOptionPane.showMessageDialog(null, "<html><span style='color:red'>PIN does not match!</span></html>");
					}
				}
				
				inst.add(userInst);
				JOptionPane.showMessageDialog(null, "<html><span style='color:green'>User details updated.  Please login again</span></html>");
			}
			else {
				JOptionPane.showMessageDialog(null, "<html><span style='color:red'>User already exists!</span></html>");
			}
			return;
		}
		else {
			JOptionPane.showMessageDialog(null, "<html><span style='color:red'>Cancelled</span></html>");
			return;
		}
	}
	
	@Override
	public String toString() {
		return userID;
	}
}

public class BankMgmt {
	public static void main(String[] args) {
		LoginRegister ui = new LoginRegister();
		ui.mainMenu();
	}
}