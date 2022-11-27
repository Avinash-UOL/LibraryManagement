package com.app.fun;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.app.driver.MySQLDriver;
import com.app.ui.Library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminOperations {

	public static void makeATableBoii(String[][] data, String[] cols, String title) {
		
		//JTable bookData = new JTable();
		final JTable bookData = new JTable(data, cols);
		JScrollPane scrollPane = new JScrollPane();
		JFrame dataFrame = new JFrame();
		
		//bookData = new JTable(data, cols);
		scrollPane = new JScrollPane(bookData);
		bookData.setFont(new Font("Arial", Font.BOLD, 16));
		bookData.setRowHeight(30);
		scrollPane.setBounds(0, 0, 800, 700);
		//Library.newFrame(title, 800, 700);
		//Library.frame.add(scrollPane);
		dataFrame.setTitle(title);
		dataFrame.setSize(800, 700);
		dataFrame.setLocationRelativeTo(null);
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.add(scrollPane);
		dataFrame.setVisible(true);	
	}
	
	public static void showStudents() {

		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from students");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2) };

			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[6];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("roll_number"));
				data[i][1] = set.getString("student_name");
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "View Enrolled Students");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void enrollStudent() {
		JFrame enrollStudent;
		enrollStudent = Library.newJframeWindow("Enroll Student", 600, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel Uroll, Uname;
		JTextField UrollIN = null, UnameIN = null;
		JButton issue = new JButton("Enroll Student");
		enrollStudent.add(issue);
		issue.setBounds(20, 300, 120, 30);

		Uroll = new JLabel("Student Roll No : ");
		Uname = new JLabel("Student Name : ");

		JLabel[] lables = { Uroll, Uname };
		String[] lableINPUT = new String[lables.length];
		JTextField[] inputs = { UrollIN, UnameIN };

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();
			enrollStudent.add(inputs[i]);
		}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {

			lables[i].setBounds(20, 40 + yoff, 120, 20);
			enrollStudent.add(lables[i]);
			inputs[i].setBounds(150, 40 + yoff, 320, 40);
			inputs[i].setFont(new Font("Arial",Font.PLAIN,20));
			yoff += 60;
		}
		
		enrollStudent.setVisible(true);

		issue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lableINPUT[0] = inputs[0].getText();
				lableINPUT[1] = inputs[1].getText();
				boolean allow = true;
				for (int i = 0; i < 2; i++) {
					if (lableINPUT[i].isEmpty()) {
						allow = false;
						JOptionPane.showMessageDialog(null, "Please don't leave any field blank", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				
				if (allow) {
					Connection connection = MySQLDriver.connect("root", "");

					MySQLDriver.insertToTable(connection,
							"insert into students(roll_number,student_name) values('" + lableINPUT[0] + "','"
									+ lableINPUT[1] + "')");
					JOptionPane.showMessageDialog(null, "User Enrolled Successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					enrollStudent.setVisible(false);
					try {
						connection.close();
						//System.out.println("Connection closed");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
	}

}
