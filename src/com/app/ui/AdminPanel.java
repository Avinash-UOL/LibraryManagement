package com.app.ui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.app.fun.AdminOperations;
import com.app.fun.GeneralOperations;

public class AdminPanel {
	public static String[] names = { "Add User", "Add Book", "Issue Book", "Return Book", "View Books", "View Users", "View Issued Books",  
			 "View Returned Books", "Delete User", "Delete Books", "Logout" };
	public static JButton viewBooks = null, viewUsers = null, viewIssBooks = null, issueBook = null, addUser = null,
			addBook = null, returnBook = null, viewRetBooks = null, deleteUser = null, deleteBooks = null, logout = null;
	public static JButton[] buttons = { addUser, addBook, issueBook, returnBook, viewBooks, viewUsers, viewIssBooks, 
			viewRetBooks, deleteUser, deleteBooks, logout };
	public static JButton enrollStudent;
	public static JButton viewStudent;
	public static JFrame adminFrame;

	public static void ShowAdminMenu(String name, int id) throws IOException {

		JLabel label = new JLabel("Select an option");

		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(450, 70, 250, 30);

		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		
		adminFrame = new JFrame("Admin Functions");
		
		JLabel background = new JLabel(new ImageIcon(Library.bgImage));
		
		adminFrame.setLayout(null);
		adminFrame.setSize(1200, 600);
		background.setBounds(0, 0, adminFrame.getWidth(), adminFrame.getHeight());
		//adminFrame.setLocationRelativeTo(null);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.add(label);
		
		//Library.frame.add(label);
		//Library.frame.setTitle("Admin Functions");
		//Library.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//JButton ResetInc = new JButton();
		int xoff = 0, yoff = 0;

		for (int i = 0; i < buttons.length; i++) {
			if (i == buttons.length - 1) {
				buttons[i] = new JButton();
				Image dimg = ImageIO.read(Library.class.getResourceAsStream("/logout.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
				buttons[i].setIcon(new ImageIcon(dimg));
				// ResetInc.setIcon(new ImageIcon(
				// "C:\\Users\\Dell\\eclipse-workspace\\LibManagement\\src\\resources\\refresh.png"));
				//LibMain.frame.add(ResetInc);
				// ResetInc.setBounds(700, 60, 50, 54);

				//Library.frame.add(buttons[i]);
				buttons[i].setBounds(1120, 10, 50, 50);
				adminFrame.add(buttons[i]);
				
				break;
			}
			buttons[i] = new JButton(names[i]);
			//Library.frame.add(buttons[i]);
			//adminFrame.add(buttons[i]);
			buttons[i].setBounds(130 + xoff, 190 + yoff, 180, 45);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
			if (xoff >= 630) {
				xoff = 0;
				yoff = 70;
			} else {
				xoff += 190;
			}
			adminFrame.add(buttons[i]);
		}
		
		enrollStudent = new JButton("Enroll Student");
		enrollStudent.setBounds(400, 400, 180, 45);
		enrollStudent.setFont(new Font("Arial", Font.BOLD, 20));
		adminFrame.add(enrollStudent);
		
		enrollStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperations.enrollStudent();
			}
		});
		
		viewStudent = new JButton("View Students");
		viewStudent.setBounds(620, 400, 180, 45);
		viewStudent.setFont(new Font("Arial", Font.BOLD, 20));
		adminFrame.add(viewStudent);
		
		viewStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperations.showStudents();
			}
		});
		
		for (int i = 0; i < buttons.length; i++) {
			final int j = i;
			buttons[j].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (j == 0) {
						AdminOperations.addUser();
					} else if (j == 1) {
						AdminOperations.addBook();
					} else if (j == 2) {
						AdminOperations.issueBook();
					} else if (j == 3) {
						AdminOperations.viewIssuedBooks("Return Book");
					} else if (j == 4) {
						AdminOperations.showBooks();
					} else if (j == 5) {
						AdminOperations.viewUsers();
					} else if (j == 6) {
						AdminOperations.viewIssuedBooks("Issued Book");
					} else if (j == 7) {
						AdminOperations.viewReturnedBooks();
					} else if (j == 8) {
						AdminOperations.deleteUsers();
					} else if (j == 9) {
						AdminOperations.deleteBooks();
					} else if (j == 10) {
						//AdminOperations.logout();
						int n = JOptionPane.showConfirmDialog(null, "Do you want to logout");
						if (n == JOptionPane.YES_OPTION) {
							adminFrame.dispose();
							adminFrame.setVisible(false);
							try {
								Library.mainMenu();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}

				}
			});
		}
		
		adminFrame.add(background);
		adminFrame.setVisible(true);
	}
}
