package test.fun;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.junit.Test;

import com.app.fun.GeneralOperations;
import com.app.ui.AdminPanel;
import com.app.ui.Library;

public class LibraryTest {

	@Test 
    public void testLoginWithBlankUsername() throws Exception{ 
		Library.main(null);
		Library.login();
		Library.userInN.setText("");
		Library.userInP.setText("");
		Library.login.doClick();
		assertEquals("Invalid Login Credentials", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testLoginWithBlankPassword() throws Exception{ 
		Library.main(null);
		Library.login();
		Library.userInN.setText("");
		Library.userInP.setText("");
		Library.login.doClick();
		assertEquals("Invalid Login Credentials", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testLoginWithInvalidCreds() throws Exception{ 
		Library.main(null);
		Library.login();
		Library.userInN.setText("admin");
		Library.userInP.setText("admind");
		Library.login.doClick();
		assertEquals("Invalid Login Credentials", GeneralOperations.message.getText());
		GeneralOperations.okButton.doClick();
    }
	
	@Test 
    public void testLoginWithValidCreds() throws Exception{ 
		Library.main(null);
		Library.login();
		Library.userInN.setText("admin");
		Library.userInP.setText("admin");
		Library.login.doClick();
		assertEquals("Admin Functions", AdminPanel.adminFrame.getTitle());
		//Library.exit.doClick();
    }
	
}
