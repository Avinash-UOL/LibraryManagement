package test.fun;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;

import org.junit.Test;

import com.app.fun.AdminOperations;
import com.app.fun.GeneralOperations;
import com.app.ui.AdminPanel;
import com.app.ui.Library;

public class AdminOperationsTest {
	
	@Test 
    public void testAddUserWithEmptyData(){  
		validLogin();
		AdminPanel.buttons[0].doClick();
		assertEquals("Add a new user",AdminOperations.addUserFrame.getTitle());
		AdminOperations.addUserButton.doClick();
		assertEquals("Please don't leave any field blank", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testAddUserWithMisMatchPassword(){  
		validLogin();
		AdminPanel.buttons[0].doClick();
		assertEquals("Add a new user",AdminOperations.addUserFrame.getTitle());
		AdminOperations.userInput.setText("TesterUser");
		AdminOperations.userPassword.setText("123456");
		AdminOperations.userConfirmPassword.setText("123465");
		AdminOperations.addUserButton.doClick();
		assertEquals("Please Enter password correctly", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testAddUserWithExistingData(){  
		validLogin();
		AdminPanel.buttons[0].doClick();
		assertEquals("Add a new user",AdminOperations.addUserFrame.getTitle());
		AdminOperations.userInput.setText("admin");
		AdminOperations.userPassword.setText("Tester");
		AdminOperations.userConfirmPassword.setText("Tester");
		AdminOperations.addUserButton.doClick();
		assertEquals("User already exists", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testAddUserWithValidData(){  
		Random random = new Random(); 
		validLogin();
		AdminPanel.buttons[0].doClick();
		assertEquals("Add a new user",AdminOperations.addUserFrame.getTitle());
		AdminOperations.userInput.setText(String.valueOf(random.nextInt(10000000)));
		AdminOperations.userPassword.setText("Tester");
		AdminOperations.userConfirmPassword.setText("Tester");
		AdminOperations.addUserButton.doClick();
		assertEquals("User Added", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testViewUsers(){  
		validLogin();
		AdminPanel.buttons[5].doClick();
		assertEquals("Users",AdminOperations.dataFrame.getTitle());
    }
	
	@Test 
    public void testDeleteUsers(){  
		validLogin();
		AdminPanel.buttons[8].doClick();
		assertEquals("Delete Users",AdminOperations.dataFrame.getTitle());
		int rowData = AdminOperations.tableData.getRowCount();AdminOperations.tableData.setRowSelectionInterval(rowData - 1, rowData - 1);
		assertEquals("Delete Users",GeneralOperations.optionFrame.getTitle());
		GeneralOperations.yesButton.doClick();
		assertEquals("Success",GeneralOperations.alertFrame.getTitle());
		GeneralOperations.okButton.doClick();
		AdminOperations.tableData.setRowSelectionInterval(rowData - 2, rowData - 2);
		assertEquals("Delete Users",GeneralOperations.optionFrame.getTitle());
		GeneralOperations.noButton.doClick();
    }
	
	@Test 
    public void testEnrollStudentsWithEmptyData() throws Exception{  
		validLogin();
		AdminPanel.enrollStudent.doClick();
		assertEquals("Enroll Student",AdminOperations.enrollStudent.getTitle());
		AdminOperations.enrollStudentButton.doClick();
		assertEquals("Please don't leave any field blank", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testEnrollStudentsWithValidData() throws Exception{
		Random random = new Random(); 
		validLogin();
		AdminPanel.enrollStudent.doClick();
		assertEquals("Enroll Student",AdminOperations.enrollStudent.getTitle());
		AdminOperations.enrollStudentName.setText("Tester");
		AdminOperations.enrollStudentRoll.setText(String.valueOf(random.nextInt(10000000)));
		AdminOperations.enrollStudentButton.doClick();
		assertEquals("User Enrolled Successfully", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testEnrollStudentsWithExistingStudentID() throws Exception{
		validLogin();
		AdminPanel.enrollStudent.doClick();
		assertEquals("Enroll Student",AdminOperations.enrollStudent.getTitle());
		AdminOperations.enrollStudentName.setText("Tester");
		AdminOperations.enrollStudentRoll.setText(String.valueOf("123"));
		AdminOperations.enrollStudentButton.doClick();
		assertEquals("Student already registered", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testEnrollStudentsWithInvalidData() throws Exception{
		validLogin();
		AdminPanel.enrollStudent.doClick();
		assertEquals("Enroll Student",AdminOperations.enrollStudent.getTitle());
		AdminOperations.enrollStudentName.setText("Tester");
		AdminOperations.enrollStudentRoll.setText(String.valueOf("123STFKDJF"));
		AdminOperations.enrollStudentButton.doClick();
		assertEquals("Please enter numeric value for roll number", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testViewStudents() throws Exception{  
		validLogin();
		AdminPanel.viewStudent.doClick();
		assertEquals("View Enrolled Students",AdminOperations.dataFrame.getTitle());
    }
	
	@Test
	public void testAddBookWithEmptyData() throws Exception {
		validLogin();
		AdminPanel.buttons[1].doClick();
		assertEquals("Add Book",AdminOperations.addFrame.getTitle());
		AdminOperations.bookPriceInput.setText("50");
		AdminOperations.addBookButton.doClick();
		assertEquals("Please don't leave any field blank", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
	}
	
	@Test
	public void testAddBookWithInvalidPrice() throws Exception {
		validLogin();
		AdminPanel.buttons[1].doClick();
		assertEquals("Add Book",AdminOperations.addFrame.getTitle());
		AdminOperations.bookNameInput.setText("Tester Book");
		AdminOperations.bookAuthorInput.setText("Tester Author");
		AdminOperations.bookGenereInput.setText("Tester Genere");
		AdminOperations.bookPriceInput.setText("Tester");
		AdminOperations.addBookButton.doClick();
		assertEquals("Please enter numeric value for price", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
	}
	
	@Test
	public void testAddBookWithValidContents() throws Exception {
		validLogin();
		AdminPanel.buttons[1].doClick();
		assertEquals("Add Book",AdminOperations.addFrame.getTitle());
		AdminOperations.bookNameInput.setText("Tester Book");
		AdminOperations.bookAuthorInput.setText("Tester Author");
		AdminOperations.bookGenereInput.setText("Tester Genere");
		AdminOperations.bookPriceInput.setText("50");
		AdminOperations.addBookButton.doClick();
		assertEquals("Book added to database", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
	}
	
	@Test 
    public void testViewBook(){  
		validLogin();
		AdminPanel.buttons[4].doClick();
		assertEquals("Book Shelf",AdminOperations.dataFrame.getTitle());
    }
	
	@Test 
    public void testDeleteBook(){  
		validLogin();
		AdminPanel.buttons[9].doClick();
		assertEquals("Delete Books",AdminOperations.dataFrame.getTitle());
		int rowData = AdminOperations.tableData.getRowCount();
		AdminOperations.tableData.setRowSelectionInterval(rowData - 1, rowData - 1);
		assertEquals("Delete Books",GeneralOperations.optionFrame.getTitle());
		GeneralOperations.yesButton.doClick();
		assertEquals("Success",GeneralOperations.alertFrame.getTitle());
		GeneralOperations.okButton.doClick();
		AdminOperations.tableData.setRowSelectionInterval(rowData - 2, rowData - 2);
		assertEquals("Delete Books",GeneralOperations.optionFrame.getTitle());
		GeneralOperations.noButton.doClick();
    }
	
	
	
	public void validLogin() {
		Library.main(null);
		Library.login();
		Library.userInN.setText("admin");
		Library.userInP.setText("admin");
		Library.login.doClick();
	}	

}
