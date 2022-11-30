package com.app.fun;

import java.awt.Color;
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
	
	public static JFrame alertFrame;
	public static JFrame infoFrame;
	public static JFrame optionFrame;
	public static JButton okButton;
	public static JButton yesButton;
	public static JButton noButton;
	public static JLabel message;
	
	public static void alert(String alertType, String messageText) {
		okButton = new JButton("OK");
		okButton.setBounds(20, 80, 80, 25);
		
		message = new JLabel(messageText);
		message.setForeground(Color.black);
		message.setFont(new Font("Arial", Font.BOLD, 20));
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
			}
		});
	}

}
