package com.app.fun;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public static void login() {
		JFrame logFrame;
		JLabel userN = new JLabel("Username");
		JLabel userP = new JLabel("Password");
		JTextField userInN = new JTextField();
		JPasswordField userInP = new JPasswordField();
		JButton login = new JButton("Login");
		
		userN.setBounds(30, 15, 100, 30);
		userP.setBounds(30, 50, 100, 30);
		userInN.setBounds(110, 15, 100, 30);
		userInP.setBounds(110, 50, 100, 30);
		login.setBounds(130, 90, 80, 25);

		logFrame = Library.newJframeWindow("Login", 400, 180, JFrame.DISPOSE_ON_CLOSE);
		
		logFrame.add(userN);
		logFrame.add(userP);
		logFrame.add(userInN);
		logFrame.add(userInP);
		logFrame.add(login);
		
		logFrame.setVisible(true);
		
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
						ResultSet resultSet = statement.executeQuery(st);
						if (resultSet.next() == false) {
							JOptionPane.showMessageDialog(logFrame, "No user", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							logFrame.dispose();
							resultSet.beforeFirst();
							resultSet.next();
							String name = resultSet.getString("username");
							String pass = resultSet.getString("password");
							int id = resultSet.getInt("id");
							//System.out.println("Enter as : " + name + " , " + pass);
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
	}

}
