package com.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Library {
	public static JFrame frame;
	private static BufferedImage bgImage;

	public static void main(String[] args) {
		try {
			mainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void mainMenu() throws Exception {
		String[] options = { "Login", "Add new user", "Exit" };

		newFrame("Welcome to the Library", 800, 700);
		frame.setLayout(null);

		bgImage = ImageIO.read(Library.class.getResourceAsStream("/back1.png"));
		//JLabel background = new JLabel(new ImageIcon(resoucePath));
		JLabel background = new JLabel(new ImageIcon(bgImage));
		JLabel labelT = new JLabel("Welcome to the Library");
		labelT.setHorizontalAlignment(JLabel.CENTER);
		labelT.setBounds(160, 20, 450, 40);
		labelT.setFont(new Font("Arial", Font.BOLD, 40));
		labelT.setForeground(Color.black);
		labelT.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(labelT);

		//background.setBounds(0, 0, 800, 700);
		frame.setSize(799, 699);
		frame.setSize(800, 700);
		background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		login();
		frame.add(background);
		frame.setVisible(true);

		/*int selection = JOptionPane.showOptionDialog(frame, "Welcome to Library Management System", "Select an option",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (selection == 0) {
			GeneralOperations.login();
		} else if (selection == 1) {
			GeneralOperations.addUser();
		} else if (selection == 2) {
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);

		}*/

	}

	public static void newFrame(String title, int w, int h) {
		frame = new JFrame(title);
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		//frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static JFrame newJframeWindow(String title, int w, int h, int closeop) {
		JFrame iframe = new JFrame(title);
		iframe.setSize(w, h);
		iframe.setDefaultCloseOperation(closeop);
		iframe.setLayout(null);
		//iframe.setVisible(true);
		iframe.setLocationRelativeTo(null);
		return iframe;
	}
	
	public static void login() {
		JLabel userN = new JLabel("Username");
		JLabel userP = new JLabel("Password");
		JTextField userInN = new JTextField();
		JPasswordField userInP = new JPasswordField();
		JButton login = new JButton("Login");
		JButton exit = new JButton("Exit");
		
		userN.setBounds(300, 350, 100, 30);
		userP.setBounds(300, 400, 100, 30);
		userInN.setBounds(380, 350, 150, 30);
		userInP.setBounds(380, 400, 150, 30);
		login.setBounds(320, 450, 80, 25);
		exit.setBounds(400, 450, 80, 25);
		frame.add(userN);
		frame.add(userP);
		frame.add(userInN);
		frame.add(userInP);
		frame.add(login);
		frame.add(exit);
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String uname = userInN.getText();
				String upass = String.valueOf(userInP.getPassword());
				if (uname == "") {
					JOptionPane.showMessageDialog(null, "Please Enter username");
				} else if (upass == "") {
					JOptionPane.showMessageDialog(null, "Please Enter password");
				} else {
					// Connection to be created
				}

			}
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});
		
	}

}