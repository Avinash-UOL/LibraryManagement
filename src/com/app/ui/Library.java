package com.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.app.driver.MySQLDriver;
import com.app.fun.AdminOperations;
import com.app.fun.GeneralOperations;

public class Library {
	public static JFrame frame;
	public static BufferedImage bgImage;
	public static ResultSet resultSet;
	public static JLabel userN;
	public static JLabel userP;
	public static JTextField userInN;
	public static JPasswordField userInP;
	public static JButton login;
	public static JButton exit;
	public static JOptionPane pane;
	

	public static void main(String[] args) {
		//AdminOperations.refresh();
		try {
			mainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void mainMenu() throws Exception {

		newFrame("Welcome to the Library", 800, 700);
		frame.setLayout(null);

		bgImage = ImageIO.read(Library.class.getResourceAsStream("/background.jpg"));
		//JLabel background = new JLabel(new ImageIcon(resoucePath));
		JLabel background = new JLabel(new ImageIcon(bgImage));
		JLabel labelT = new JLabel("Welcome to the Library");
		labelT.setHorizontalAlignment(JLabel.CENTER);
		labelT.setBounds(380, 20, 450, 40);
		labelT.setFont(new Font("Arial", Font.BOLD, 40));
		labelT.setForeground(Color.white);
		//labelT.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(labelT);

		//background.setBounds(0, 0, 800, 700);
		//frame.setSize(799, 699);
		frame.setSize(1200, 600);
		background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		login();
		frame.add(background);
		frame.setBackground(Color.black);
		frame.setVisible(true);

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
		userN = new JLabel("Username");
		userN.setForeground(Color.white);
		userN.setFont(new Font("Arial", Font.BOLD, 20));
		userP = new JLabel("Password");
		userP.setForeground(Color.white);
		userP.setFont(new Font("Arial", Font.BOLD, 20));
		userInN = new JTextField();
		userInP = new JPasswordField();
		login = new JButton("Login");
		exit = new JButton("Exit");
		
		userN.setBounds(460, 130, 120, 70);
		userP.setBounds(460, 180, 120, 70);
		userInN.setBounds(580, 150, 150, 35);
		userInP.setBounds(580, 200, 150, 35);
		login.setBounds(500, 250, 80, 25);
		exit.setBounds(600, 250, 80, 25);
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

					Connection connection = MySQLDriver.connect("root", "");
					String st = ("select * from users where username='" + uname + "' and password='" + upass + "'");
					Statement statement;
					try {
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						resultSet = statement.executeQuery(st);
						if (resultSet.next() == false) {
							//pane.showMessageDialog(frame, "Invalid Login Credentials", "Error", JOptionPane.ERROR_MESSAGE);
							GeneralOperations.alert("Error","Invalid Login Credentials");
						} else {
							frame.dispose();
							frame.setVisible(false);
							resultSet.beforeFirst();
							resultSet.next();
							String name = resultSet.getString("username");
							String pass = resultSet.getString("password");
							int id = resultSet.getInt("id");
							System.out.println("Enter as : " + name + " , " + pass);
							AdminPanel.ShowAdminMenu(name, id);
							statement.close();
							connection.close();
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

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