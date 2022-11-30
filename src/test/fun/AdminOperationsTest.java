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
    public void testEnrollStudentsWithInvalidData() throws Exception{  
		validLogin();
		AdminPanel.enrollStudent.doClick();
		assertEquals("Enroll Student",AdminOperations.enrollStudent.getTitle());
		AdminOperations.enrollStudentButton.doClick();
		assertEquals("Please don't leave any field blank", GeneralOperations.message.getText());
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
    }
	
	@Test 
    public void testViewStudents() throws Exception{  
		validLogin();
		AdminPanel.viewStudent.doClick();
    }
	
	
	
	public void validLogin() {
		Library.main(null);
		Library.login();
		Library.userInN.setText("admin");
		Library.userInP.setText("admin");
		Library.login.doClick();
	}	

}
