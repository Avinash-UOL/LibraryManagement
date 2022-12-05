package com.app.fun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.app.driver.MySQLDriver;
import com.app.ui.AdminPanel;
import com.app.ui.Library;

public class GeneralOperations {
	
	public static JFrame alertFrame;
	public static JFrame optionFrame;
	public static JButton okButton;
	public static JButton yesButton;
	public static JButton noButton;
	public static JLabel message;
	
	public static String processedID = "";
	
	
	public static void alert(String alertType, String messageText) {
		okButton = new JButton("OK");
		okButton.setBounds(20, 80, 80, 25);
		
		message = new JLabel(messageText);
		message.setForeground(Color.black);
		message.setFont(new Font("Arial", Font.BOLD, 15));
		message.setBounds(20, 10, 300, 80);
		
		if(alertType.equalsIgnoreCase("Error")) {
			alertFrame = Library.newJframeWindow("Error", 350, 150, JFrame.DISPOSE_ON_CLOSE);
			alertFrame.add(message);
			alertFrame.add(okButton);
		} else if(alertType.equalsIgnoreCase("Success")) {
			alertFrame = Library.newJframeWindow("Success", 350, 150, JFrame.DISPOSE_ON_CLOSE);
			alertFrame.add(message);
			alertFrame.add(okButton);
		}
		
		alertFrame.setVisible(true);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alertFrame.setVisible(false);
				alertFrame.dispose();
				if(messageText.equalsIgnoreCase("Book Deleted")) {
					AdminOperations.dataFrame.setVisible(false);
					AdminOperations.deleteBooks();
				}else if(messageText.equalsIgnoreCase("User Deleted")) {
					AdminOperations.dataFrame.setVisible(false);
					AdminOperations.deleteUsers();
				}
			}
		});
	}
	
	public static void confirmation(String title, String messageText, String id) {
		processedID = id;
		yesButton = new JButton("Yes");
		yesButton.setBounds(20, 80, 80, 25);
		
		noButton = new JButton("No");
		noButton.setBounds(100, 80, 80, 25);
		
		message = new JLabel(messageText);
		message.setForeground(Color.black);
		message.setFont(new Font("Arial", Font.BOLD, 15));
		message.setBounds(20, 10, 300, 80);
		
		optionFrame = Library.newJframeWindow(title, 350, 150, JFrame.DISPOSE_ON_CLOSE);
		optionFrame.add(message);
		optionFrame.add(yesButton);
		optionFrame.add(noButton);
		
		optionFrame.setVisible(true);
		
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperations.dataFrame.setVisible(false);
				AdminOperations.dataFrame.dispose();
				optionFrame.setVisible(false);
				optionFrame.dispose();
				Connection connection = MySQLDriver.connect("root", "");	
				try {
    				Statement statement = connection.createStatement();
    				if(title.equalsIgnoreCase("Delete Books")) {
    					if(statement.executeUpdate("Delete from bookData where book_id=" + id) == 1) {
    						alert("Success", "Book Deleted");
    					}else {
    						alert("Error", "Failed to Delete Book");
    					}
    				}else if(title.equalsIgnoreCase("Delete Users")) {
    					if(statement.executeUpdate("Delete from users where id=" + id) == 1) {
    						alert("Success", "User Deleted");
    					}else {
    						alert("Error", "Failed to Delete User");
    					}
    				}else if(title.equalsIgnoreCase("Return Book")) {
    					AdminOperations.returnBook(id,AdminOperations.dataFrame);
    				}else if(title.equalsIgnoreCase("Logout")) {
    					Library.mainMenu();
    				}
					
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
			}
		});
		
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Clicked No Button");
				AdminOperations.dataFrame.setVisible(false);
				AdminOperations.dataFrame.dispose();
				optionFrame.setVisible(false);
				optionFrame.dispose();
				if(title.equalsIgnoreCase("Delete Books")) {
					processedID = "";
					AdminOperations.deleteBooks();
				} else if(title.equalsIgnoreCase("Delete Users")) {
					processedID = "";
					AdminOperations.deleteUsers();
				} else if(title.equalsIgnoreCase("Return Book")) {
					processedID = "";
					AdminOperations.returnBook(id, AdminOperations.dataFrame);
				} else if(title.equalsIgnoreCase("Logout")) {
					//Do Nothing
				}
				
			}
		});
		
	}
	
	public static String getIssuedBook() {
		String issuedBookID = "";
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement();	
			ResultSet set1 = statement.executeQuery("Select * from bookData where issued='1' LIMIT 1");
			if(set1.next() == false) {
				System.out.println("No Record Found");
			} else {
				issuedBookID = String.valueOf(set1.getInt("book_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return issuedBookID;
	}
	
	public static String getAvailableBook() {
		String availableBookID = "";
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement();	
			ResultSet set1 = statement.executeQuery("Select * from bookData where issued='0' LIMIT 1");
			if(set1.next() == false) {
				System.out.println("No Record Found");
			} else {
				availableBookID = String.valueOf(set1.getInt("book_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return availableBookID;
	}
	
	public static String getValidStudentRollNumber() {
		String availableStudent = "";
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement();	
			ResultSet set1 = statement.executeQuery("Select * from students LIMIT 1");
			if(set1.next() == false) {
				System.out.println("No Record Found");
			} else {
				availableStudent = String.valueOf(set1.getInt("roll_number"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return availableStudent;
	}
	

}
