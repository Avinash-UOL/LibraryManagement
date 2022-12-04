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
	
	public static java.util.Date date = new java.util.Date();
	
	public static JOptionPane optionPane;
	
	public static JTable tableData;
	public static JFrame dataFrame;
	
	//Enroll Student
	public static JFrame enrollStudent;
	public static JButton enrollStudentButton;
	public static JTextField enrollStudentRoll, enrollStudentName;
	
	//Add Book
	public static JFrame addFrame;
	public static JButton addBookButton;
	public static JTextField bookNameInput, bookAuthorInput, bookGenereInput, bookPriceInput;
	
	//Add User
	public static JFrame addUserFrame;
	public static JTextField userInput;
	public static JPasswordField userPassword, userConfirmPassword;
	public static JButton addUserButton;
	
	//Issue Book
	public static JFrame issueBookFrame;
	public static JTextField issueIDInput, issueRollInput, issueNameInput, issueDateInput;
	public static JButton issueButton;

	public static void makeATableBoii(String[][] data, String[] cols, String title) {
		
		tableData = new JTable(data, cols);
		JScrollPane scrollPane = new JScrollPane();
		dataFrame = new JFrame();
		scrollPane = new JScrollPane(tableData);
		tableData.setFont(new Font("Arial", Font.BOLD, 16));
		tableData.setRowHeight(30);
		scrollPane.setBounds(0, 0, 800, 700);
		dataFrame.setTitle(title);
		dataFrame.setSize(800, 700);
		dataFrame.setLocationRelativeTo(null);
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.add(scrollPane);
		dataFrame.setVisible(true);
		
		tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			
			@Override
	        public void valueChanged(ListSelectionEvent event) {
	            String id = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
	            if(!id.equalsIgnoreCase(GeneralOperations.processedID)) {
	            	if(title.equalsIgnoreCase("Delete Books")){
			            GeneralOperations.confirmation("Delete Books","Are you sure you want to delete ?",id);
			        }else if(title.equalsIgnoreCase("Delete Users")) {
			            GeneralOperations.confirmation("Delete Users","Are you sure you want to delete ?",id);
			        }else if(title.equalsIgnoreCase("Return Book")) {
			            GeneralOperations.confirmation("Return Book","Are you sure you want to return this book ? ",id);
			        }
	            }
	        }
	    });
		
	}

	public static void addBook() {
		addFrame = Library.newJframeWindow("Add Book", 600, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel name, auth, gen, price;
		addBookButton = new JButton("Add Book");
		addFrame.add(addBookButton);
		addBookButton.setBounds(20, 300, 120, 30);

		name = new JLabel("Enter Book's Name");
		auth = new JLabel("Enter Book's Author");
		gen = new JLabel("Enter Book's Genre");
		price = new JLabel("Enter Book's Price (£)");

		JLabel[] lables = { name, auth, gen, price };
		//String[] lableINPUT = new String[lables.length];
		//JTextField[] inputs = { nameIN, authIN, genIN, priceIN };
		bookNameInput = new JTextField();
		bookAuthorInput = new JTextField();
		bookGenereInput = new JTextField();
		bookPriceInput = new JTextField();
		
		addFrame.add(bookNameInput);
		addFrame.add(bookAuthorInput);
		addFrame.add(bookGenereInput);
		addFrame.add(bookPriceInput);
		//for (int i = 0; i < inputs.length; i++) {
		//	inputs[i] = new JTextField();
		//	addFrame.add(inputs[i]);
		//}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {
			lables[i].setBounds(20, 40 + yoff, 180, 20);
			addFrame.add(lables[i]);
			//inputs[i].setBounds(150, 40 + yoff, 400, 40);
			//inputs[i].setFont(new Font("Arial",Font.PLAIN,20));
			yoff += 60;
		}
		
		bookNameInput.setBounds(200,40,380,40);
		bookNameInput.setFont(new Font("Arial",Font.PLAIN,20));
		bookAuthorInput.setBounds(200,100,380,40);
		bookAuthorInput.setFont(new Font("Arial",Font.PLAIN,20));
		bookGenereInput.setBounds(200,160,380,40);
		bookGenereInput.setFont(new Font("Arial",Font.PLAIN,20));
		bookPriceInput.setBounds(200,220,380,40);
		bookPriceInput.setFont(new Font("Arial",Font.PLAIN,20));
		
		addFrame.setVisible(true);

		addBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//lableINPUT[0] = inputs[0].getText();
				//lableINPUT[1] = inputs[1].getText();
				//lableINPUT[2] = inputs[2].getText();
				//lableINPUT[3] = inputs[3].getText();
				boolean allow = true;
				for (int i = 0; i < 4; i++) {
					if (bookNameInput.getText().isEmpty() || bookAuthorInput.getText().isEmpty() || bookGenereInput.getText().isEmpty() || bookPriceInput.getText().isEmpty()) {
						allow = false;
						//JOptionPane.showMessageDialog(null, "Please don't leave any field blank", "Error", JOptionPane.ERROR_MESSAGE);
						GeneralOperations.alert("Error", "Please don't leave any field blank");
						break;
					}
				}
				
				try {
					if(allow) {
						Float.parseFloat(bookPriceInput.getText());
					}
				}catch(NumberFormatException numExep) {
					allow = false;
					//JOptionPane.showMessageDialog(null, "Please enter numeric value for price", "Error", JOptionPane.ERROR_MESSAGE);
					GeneralOperations.alert("Error", "Please enter numeric value for price");
				}
				
				if (allow) {
					Connection connection = MySQLDriver.connect("root", "");
					//System.out.println("name = " + bookNameInput);
					MySQLDriver.insertToTable(connection,
							"insert into bookData(name,author,genre,price,issued) values('" + bookNameInput.getText() + "','"
									+ bookAuthorInput.getText() + "','" + bookGenereInput.getText() + "','" + bookPriceInput.getText() + "',0)");
					//JOptionPane.showMessageDialog(null, "Book added to database", "Success", JOptionPane.INFORMATION_MESSAGE);
					GeneralOperations.alert("Success", "Book added to database");
					addFrame.setVisible(false);
					try {
						connection.close();
						System.out.println("Connection closed");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public static void deleteBooks() {

		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from bookData");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5), metaData.getColumnName(6) };

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
				data[i][0] = String.valueOf(set.getInt("book_id"));
				data[i][1] = set.getString("name");
				data[i][2] = set.getString("author");
				data[i][3] = set.getString("genre");
				data[i][4] = String.valueOf(set.getFloat("price"));
				data[i][5] = String.valueOf(set.getInt("issued"));
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Delete Books");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void logout() {
		int n = JOptionPane.showConfirmDialog(null, "Do you want to logout");
		if (n == JOptionPane.YES_OPTION) {
			//Library.frame.dispose();
			//Library.frame.setVisible(false);
			try {
				Library.mainMenu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void showBooks() {

		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from bookData");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5), metaData.getColumnName(6) };

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
				data[i][0] = String.valueOf(set.getInt("book_id"));
				data[i][1] = set.getString("name");
				data[i][2] = set.getString("author");
				data[i][3] = set.getString("genre");
				data[i][4] = String.valueOf(set.getFloat("price"));
				data[i][5] = String.valueOf(set.getInt("issued"));
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Book Shelf");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void viewUsers() {

		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from users");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2) };
			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[2];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = set.getString("username");
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Users");
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
	
	public static void deleteUsers() {

		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from users");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2) };
			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[2];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = set.getString("username");
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Delete Users");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void viewIssuedBooks(String title) {
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from issuedBookData");
			ResultSetMetaData metaData = set.getMetaData();

			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5), metaData.getColumnName(6) };

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
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = String.valueOf(set.getInt("book_id"));
				data[i][2] = set.getString("book_name");
				data[i][3] = set.getString("issuer_roll_no");
				data[i][4] = set.getString("issuer_name");
				data[i][5] = String.valueOf(set.getDate("issue_date"));
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, title);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void issueBook() {
		issueBookFrame = Library.newJframeWindow("Issue Book", 600, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel id, Uroll, Uname, iDate;
		//JTextField idIN = null, UrollIN = null, UnameIN = null, idateIN = null;
		//JButton issue = new JButton("Issue Book");
		issueButton = new JButton("Issue Book");
		issueBookFrame.add(issueButton);
		issueButton.setBounds(20, 300, 120, 30);

		id = new JLabel("Book Id");
		Uroll = new JLabel("Student Roll No");
		Uname = new JLabel("Issuer Name");
		iDate = new JLabel("Date of issue");

		JLabel[] lables = { id, Uroll, Uname, iDate };
		//String[] lableINPUT = new String[lables.length];
		//JTextField[] inputs = { idIN, UrollIN, idateIN };

		//for (int i = 0; i < inputs.length; i++) {
		//	inputs[i] = new JTextField();
		//	issueBookFrame.add(inputs[i]);
		//}
		issueIDInput = new JTextField();
		issueRollInput = new JTextField();
		issueNameInput = new JTextField();
		issueDateInput = new JTextField();
		issueBookFrame.add(issueIDInput);
		issueBookFrame.add(issueRollInput);
		issueBookFrame.add(issueNameInput);
		issueBookFrame.add(issueDateInput);

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {
			lables[i].setBounds(20, 40 + yoff, 120, 20);
			issueBookFrame.add(lables[i]);
			//inputs[i].setBounds(150, 40 + yoff, 320, 40);
			//inputs[i].setFont(new Font("Arial",Font.PLAIN,20));
			yoff += 60;
		}
		
		issueIDInput.setBounds(150,40,320,40);
		issueIDInput.setFont(new Font("Arial",Font.PLAIN,20));
		issueRollInput.setBounds(150,100,320,40);
		issueRollInput.setFont(new Font("Arial",Font.PLAIN,20));
		issueNameInput.setBounds(150,160,320,40);
		issueNameInput.setFont(new Font("Arial",Font.PLAIN,20));
		issueDateInput.setBounds(150,220,320,40);
		issueDateInput.setFont(new Font("Arial",Font.PLAIN,20));
		
		issueDateInput.setText(new java.sql.Date(date.getTime()).toString());
		issueDateInput.setEditable(false); 
		
		issueBookFrame.setVisible(true);

		issueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Connection connection = MySQLDriver.connect("root", "");
				//for (int i = 0; i < inputs.length; i++)
				//	lableINPUT[i] = inputs[i].getText();

				try {
					if(issueIDInput.getText().isEmpty() || issueRollInput.getText().isEmpty()) {
						//JOptionPane.showMessageDialog(null, "Please don't leave any field blank", "Error",JOptionPane.ERROR_MESSAGE);
						GeneralOperations.alert("Error", "Please don't leave any field blank");
					}else {
						Statement statement = connection.createStatement();	
						ResultSet set1 = statement.executeQuery("Select * from students where roll_number='" + issueRollInput.getText() + "'");
						String studName = "";
						if(set1.next() == false) {
							//JOptionPane.showMessageDialog(null, "Student record not found. Please enroll this student", "Error",JOptionPane.ERROR_MESSAGE);
							GeneralOperations.alert("Error", "Student record not found. Please enroll this student");
						}else {
							studName = set1.getString("student_name");
							ResultSet set2 = statement.executeQuery("Select * from bookData where book_id='" + issueIDInput.getText() +"'");

							if (set2.next() == false) {
								//JOptionPane.showMessageDialog(null, "No book found as per given data", "Error", JOptionPane.ERROR_MESSAGE);
								GeneralOperations.alert("Error", "No book found as per given data");
							} else {
								String Bname = set2.getString("Name");
								int issue = set2.getInt("Issued");
								if(issue != 0) {
									//JOptionPane.showMessageDialog(null, "Book not available. Already issued.", "Error",JOptionPane.ERROR_MESSAGE);
									GeneralOperations.alert("Error", "Book not available. Already issued.");
								}else {
									MySQLDriver.insertToTable(connection,
											"insert into issuedBookData(book_id,book_name,issuer_roll_no,issuer_name,issue_date) values('"
													+ issueIDInput.getText() + "','" + Bname + "','" + issueRollInput.getText() + "','" + studName
													+ "','" + issueDateInput.getText() + "')");
									//JOptionPane.showMessageDialog(null, "Book Issued To user");
									GeneralOperations.alert("Success", "Book issued to user");
									issue = 1;
									MySQLDriver.insertToTable(connection, "update bookData set issued ='" + issue + "' where book_id='" + issueIDInput.getText() + "'");
									//System.out.println("insert into bookData(issued) values(" + issue + ") where book_id=" + lableINPUT[0]);
									issueBookFrame.setVisible(false);
								}
							}
						}
						connection.close();
						System.out.println("Connection closed");
						statement.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void enrollStudent() {
		enrollStudent = Library.newJframeWindow("Enroll Student", 600, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel Uroll, Uname;
		enrollStudentButton = new JButton("Enroll Student");
		enrollStudent.add(enrollStudentButton);
		enrollStudentButton.setBounds(20, 300, 120, 30);

		Uroll = new JLabel("Student Roll No : ");
		Uname = new JLabel("Student Name : ");

		JLabel[] lables = { Uroll, Uname };
		enrollStudentRoll = new JTextField();
		enrollStudentName = new JTextField();
		enrollStudent.add(enrollStudentRoll);
		enrollStudent.add(enrollStudentName);

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {
			lables[i].setBounds(20, 40 + yoff, 120, 20);
			enrollStudent.add(lables[i]);
			yoff += 60;
		}
		
		enrollStudentRoll.setBounds(150, 40, 320, 40);
		enrollStudentRoll.setFont(new Font("Arial",Font.PLAIN,20));
		enrollStudentName.setBounds(150, 100, 320, 40);
		enrollStudentName.setFont(new Font("Arial",Font.PLAIN,20));
		
		enrollStudent.setVisible(true);

		enrollStudentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//lableINPUT[0] = inputs[0].getText();
				//lableINPUT[1] = inputs[1].getText();
				boolean allow = true;
				for (int i = 0; i < 2; i++) {
					if (enrollStudentRoll.getText().isEmpty() || enrollStudentName.getText().isEmpty()) {
						allow = false;
						//JOptionPane.showMessageDialog(null, "Please don't leave any field blank", "Error", JOptionPane.ERROR_MESSAGE);
						GeneralOperations.alert("Error","Please don't leave any field blank");
						break;
					}
				}
				
				try {
					if(allow) {
						Integer.parseInt(enrollStudentRoll.getText());
					}
				}catch(NumberFormatException numExep) {
					allow = false;
					//JOptionPane.showMessageDialog(null, "Please enter numeric value for price", "Error", JOptionPane.ERROR_MESSAGE);
					GeneralOperations.alert("Error", "Please enter numeric value for roll number");
				}
				
				if (allow) {
					Connection connection = MySQLDriver.connect("root", "");
					try {
						Statement statement = connection.createStatement();	
						ResultSet set1 = statement.executeQuery("Select * from students where roll_number=" + enrollStudentRoll.getText());
						String studName = "";
						if(set1.next() == true) {
							//JOptionPane.showMessageDialog(null, "Student already registered", "Error", JOptionPane.ERROR_MESSAGE);	
							GeneralOperations.alert("Error","Student already registered");
						}else {
							MySQLDriver.insertToTable(connection, "insert into students(roll_number,student_name) values('" + enrollStudentRoll.getText() + "','" + enrollStudentName.getText() + "')");
							//JOptionPane.showMessageDialog(null, "User Enrolled Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
							GeneralOperations.alert("Success", "User Enrolled Successfully");
							enrollStudent.setVisible(false);
						}
						connection.close();
						//System.out.println("Connection closed");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
	}

	public static void returnBook(String bookId, JFrame dataFrame) {
		JFrame returnFrame;
		returnFrame = Library.newJframeWindow("Return Book", 600, 500, JFrame.DISPOSE_ON_CLOSE);
		JLabel id, book_name, Uroll, issuer_name, issue_Date, rDate;
		JTextField idIN = null, bookName = null, rdateIN = null, UrollIN = null, issuerName = null, issueDate = null;
		JButton returnBut = new JButton("Confirm Return");
		returnFrame.add(returnBut);
		returnBut.setBounds(20, 400, 120, 30);

		id = new JLabel("Book Id");
		book_name = new JLabel("Book Name");
		Uroll = new JLabel("Student Roll No");
		issuer_name = new JLabel("Issuer Name");
		issue_Date = new JLabel("Date of issue");
		rDate = new JLabel("Date of return");

		JLabel[] lables = { id, book_name, Uroll, issuer_name, issue_Date, rDate };
		String[] lableINPUT = new String[lables.length];
		JTextField[] inputs = { idIN, bookName, UrollIN, issuerName, issueDate, rdateIN };

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();
			returnFrame.add(inputs[i]);
		}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {
			lables[i].setBounds(20, 40 + yoff, 120, 20);
			returnFrame.add(lables[i]);
			inputs[i].setBounds(150, 40 + yoff, 320, 40);
			inputs[i].setFont(new Font("Arial",Font.PLAIN,20));
			yoff += 60;
		}
		
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement();
			ResultSet set1 = statement.executeQuery("Select * from issuedBookData where id=" + bookId);
			int bkId = 0;
			
			if(set1.next()) {
				bkId = set1.getInt("book_id");
			}
			
			ResultSet set2 = statement.executeQuery("Select * from issuedBookData where book_id=" + bkId);
			
			if(set2.next()) {
				String bkName = set2.getString("book_name");
				String issRollNo = set2.getString("issuer_roll_no");
				String issName = set2.getString("issuer_name");
				String issDate = set2.getDate("issue_date").toString();
				String retDate = new java.sql.Date(date.getTime()).toString();
				
				inputs[0].setText(String.valueOf(bkId));
				inputs[0].setEditable(false);
				inputs[1].setText(bkName);
				inputs[1].setEditable(false);
				inputs[2].setText(issRollNo);
				inputs[2].setEditable(false);
				inputs[3].setText(issName);
				inputs[3].setEditable(false);
				inputs[4].setText(issDate);
				inputs[4].setEditable(false);
				inputs[5].setText(retDate);
				inputs[5].setEditable(false);
			}
			
			//connection.close();
			//System.out.println("Connection closed");
			statement.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		returnFrame.setVisible(true);

		returnBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = connection.createStatement();
					MySQLDriver.insertToTable(connection,
							"insert into returnedBookData(book_id,issuer_roll_no,issuer_name,issue_date,return_date) values('"
									+ inputs[0].getText() + "','" + inputs[2].getText() + "','" + inputs[3].getText() + "','" + inputs[4].getText()
									+ "','" + inputs[5].getText() + "')");
					statement.executeUpdate("delete from issuedBookData where book_id=" + inputs[0].getText());
					int issue = 0;
					MySQLDriver.insertToTable(connection,
							"update bookData set issued =" + issue + " where book_id=" + inputs[0].getText());
					JOptionPane.showMessageDialog(null, "Book returned");
					connection.close();
					System.out.println("Connection closed");
					statement.close();
					returnFrame.setVisible(false);
					dataFrame.setVisible(false);
					viewIssuedBooks("Return Book");
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});

	}

	public static void viewReturnedBooks() {
		Connection connection = MySQLDriver.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from returnedBookData");
			ResultSetMetaData metaData = set.getMetaData();

			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5) };

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
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = String.valueOf(set.getInt("book_id"));
				data[i][2] = set.getString("issuer_roll_no");
				data[i][3] = String.valueOf(set.getDate("issue_date"));
				data[i][4] = String.valueOf(set.getDate("Return_date"));
				i++;
			}
			connection.close();

			makeATableBoii(data, cols, "Returned Books");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void addUser() {
		JLabel userN = new JLabel("Username");
		JLabel userP = new JLabel("Password");
		JLabel userConfP = new JLabel("Confirm Password");
		
		userInput = new JTextField();
		userPassword = new JPasswordField();
		userConfirmPassword = new JPasswordField();
		
		addUserButton = new JButton("Add user");

		addUserFrame = Library.newJframeWindow("Add a new user", 600, 310, JFrame.DISPOSE_ON_CLOSE);

		userN.setBounds(30, 15, 100, 30);
		userP.setBounds(30, 60, 100, 30);
		userConfP.setBounds(30, 105, 150, 30);
		userInput.setBounds(150, 15, 320, 40);
		userInput.setFont(new Font("Arial",Font.PLAIN,20));
		userPassword.setBounds(150, 60, 320, 40);
		userPassword.setFont(new Font("Arial",Font.PLAIN,20));
		userConfirmPassword.setBounds(150, 105, 320, 40);
		userConfirmPassword.setFont(new Font("Arial",Font.PLAIN,20));
		addUserButton.setBounds(130, 170, 120, 25);

		addUserFrame.add(userN);
		addUserFrame.add(userP);
		addUserFrame.add(userConfP);
		addUserFrame.add(userInput);
		addUserFrame.add(userPassword);
		addUserFrame.add(userConfirmPassword);
		addUserFrame.add(addUserButton);	
		addUserFrame.setVisible(true);

		addUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//String uname = userInN.getText();
				//String upass = String.valueOf(userInP.getPassword());
				//String cupass = String.valueOf(userConfInP.getPassword());
				if (userInput.getText().isEmpty() || String.copyValueOf(userPassword.getPassword()).isEmpty() || String.copyValueOf(userConfirmPassword.getPassword()).isEmpty()){
					//JOptionPane.showMessageDialog(null, "Please Provide username");
					GeneralOperations.alert("Error", "Please don't leave any field blank");
				} else if (!String.copyValueOf(userPassword.getPassword()).equals(String.copyValueOf(userConfirmPassword.getPassword()))) {
					//JOptionPane.showMessageDialog(null, "Please Enter password correctly");
					GeneralOperations.alert("Success","Please Enter password correctly");
				} else {
					Connection connection = MySQLDriver.connect("root", "");
					try {
						Statement statement = connection.createStatement();	
						ResultSet set1 = statement.executeQuery("Select * from users where username='" + userInput.getText() +"'");
						String studName = "";
						if(set1.next() == true) {
							//JOptionPane.showMessageDialog(null, "Student already registered", "Error", JOptionPane.ERROR_MESSAGE);	
							GeneralOperations.alert("Error","User already exists");
						}else {
							String query = "insert into users(username,password) values('" + userInput.getText() + "' , '" + String.copyValueOf(userConfirmPassword.getPassword()) + "')";
							//JOptionPane.showMessageDialog(null,"User Added");
							GeneralOperations.alert("Success","User Added");
							MySQLDriver.insertToTable(connection, query);
							addUserFrame.setVisible(false);
							addUserFrame.dispose();
						}
						connection.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}

		});

	}

}
