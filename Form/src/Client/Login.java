/*
 * @author Anusha Sura
 */

package Client;
import javax.swing.*;

import Authentication.AuthenticationInterface;

import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Login extends JFrame {

	
	JTextField usernameTxt;  
    JPasswordField passwordTxt;  
    JInternalFrame loginFrame;  
    JInternalFrame RegisterFrame;
    JTextField username1Txt;  
    JPasswordField password1Txt;
    JTextField fullnameTxt;  
    JTextField emailTxt;  
    static Login ex1;
      
    public static void main(String[] args){  
    	 ex1 = new Login();  
        ex1.go();  
                  
      
    }  
    public void go(){  
      
          
        JFrame mainFrame = new JFrame("Main");  
        mainFrame.setSize(400,300);  
        loginFrame = new JInternalFrame("Login");  
        loginFrame.setSize(400,200);  
        RegisterFrame = new JInternalFrame("Register");
        RegisterFrame.setSize(300,300);
      //  RegisterFrame.setAlignmentX(-100);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);  
        JDesktopPane mainPanel = new JDesktopPane();  
        JPanel loginPanel = new JPanel();  
        JPanel registerPanel = new JPanel();
        
        JTextArea textArea = new JTextArea(25,25);  
        usernameTxt = new JTextField(25);     
        passwordTxt = new JPasswordField(25);  
        JLabel usernameLbl = new JLabel("Username: ");  
        JLabel passwordLbl = new JLabel("Password: ");  
        JButton loginButton = new JButton("Login"); 
        JButton NewUser = new JButton("New User");  
        
        fullnameTxt = new JTextField(25);
        username1Txt = new JTextField(25);     
        password1Txt = new JPasswordField(25);  
        emailTxt = new JTextField(25);     
        JLabel fullnamelb1= new JLabel("Full Name");
        JLabel emaillb2= new JLabel("Email");
        JLabel usernamelb3= new JLabel("User Name");
        JLabel passwordlb4= new JLabel("Password");
        JButton Submit = new JButton("Submit");
       
        
        loginButton.addActionListener(new loginButtonListener());  
        NewUser.addActionListener(new AddButtonListener());  
        Submit.addActionListener(new SubmitButtonListener());
        
        mainPanel.add(textArea);  
        mainPanel.add(loginFrame);  
        mainPanel.add(RegisterFrame);  
        loginPanel.add(usernameLbl);  
        loginPanel.add(usernameTxt);  
        loginPanel.add(passwordLbl);  
        loginPanel.add(passwordTxt);  
        loginPanel.add(loginButton);  
        loginPanel.add(NewUser);  
        registerPanel.add(fullnamelb1);
        registerPanel.add(fullnameTxt);
        registerPanel.add(emaillb2);
        registerPanel.add(emailTxt);
        registerPanel.add(usernamelb3);
        registerPanel.add(username1Txt);
        registerPanel.add(passwordlb4);
        registerPanel.add(password1Txt);
        registerPanel.add(Submit);
        
          
        loginFrame.getContentPane().add(BorderLayout.CENTER,loginPanel);  
        mainFrame.getContentPane().add(BorderLayout.CENTER,mainPanel);  
        RegisterFrame.getContentPane().add(BorderLayout.CENTER,registerPanel);
        
        loginFrame.setVisible(true);                      
        mainFrame.setVisible(true);       
        RegisterFrame.setVisible(false);                 
  
    }  
    
    public class AddButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev){  
         
          loginFrame.setVisible(false);  
          RegisterFrame.setVisible(true);             
              
        }  
          
    }  
    
    public class SubmitButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev){  
         
        	 loginFrame.setVisible(true);  
             RegisterFrame.setVisible(false);   
             String puname1 = username1Txt.getText();
             String pfname = fullnameTxt.getText();
             String pemail = emailTxt.getText();
         	 char[] paswd = password1Txt.getPassword();
         	 String paswd1= new String(paswd);
         	 try{
         	Registry registry = LocateRegistry.getRegistry("localhost");
            
         	AuthenticationInterface server = (AuthenticationInterface)registry.lookup("Authentication");
         	           
         	//server.setParameters(new ParaWrapper());
         	server.registerUser(pfname, pemail, puname1, paswd1);
         	            
         	            
      

         	        } catch (Exception e) {
         	            System.err.println("Register user exception");
         	            e.printStackTrace();
         	        }
       
        }  
          
    }  

  
    public class loginButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev){  
            //if username and password is good hide child window  
        	String puname = usernameTxt.getText();
        	char[] paswd = passwordTxt.getPassword();
        	boolean check=false;
        	String ppaswd= new String(paswd);
       	 try{
          	Registry registry = LocateRegistry.getRegistry("localhost",1234);
             
          	AuthenticationInterface server = (AuthenticationInterface)registry.lookup("Authentication");
          	           
          	//server.setParameters(new ParaWrapper());
                   check= server.authenticate(puname, ppaswd);
          	            
       

          	        } catch (Exception e) {
          	            System.err.println("Login Exception");
          	            e.printStackTrace();
          	        }
        	if(check) {
        	Form form =new Form();
        	ex1.setVisible(false);
        	
        	form.start();
        	
        	dispose();
        	} else {

        	JOptionPane.showMessageDialog(null,"Wrong Password / Username" );
        	usernameTxt.setText("");
        	passwordTxt.setText("");
        	usernameTxt.requestFocus();
        	}
         //   loginFrame.setVisible(false);  
                  
              
        }  
          
    }  

}