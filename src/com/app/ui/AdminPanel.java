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

	public static void ShowAdminMenu(String name, int id) throws IOException {

		JLabel label = new JLabel("Select an option");

		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(450, 70, 250, 30);

		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JFrame adminFrame = new JFrame("Admin Functions");
		
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
		
		JButton enrollStudent = new JButton("Enroll Student");
		enrollStudent.setBounds(400, 400, 180, 45);
		enrollStudent.setFont(new Font("Arial", Font.BOLD, 20));
		adminFrame.add(enrollStudent);
		
		enrollStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperations.enrollStudent();
			}
		});
		
		JButton viewStudent = new JButton("View Students");
		viewStudent.setBounds(620, 400, 180, 45);
		viewStudent.setFont(new Font("Arial", Font.BOLD, 20));
		adminFrame.add(viewStudent);
		
		viewStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperations.showStudents();
			}
		});
		
		adminFrame.add(background);
		adminFrame.setVisible(true);
	}
}
