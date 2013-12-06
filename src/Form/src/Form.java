import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

 








import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import qps.QPSInterface;

import Authentication.AuthenticationInterface;
 
public class Form extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l;
	JFrame frame = new JFrame();
     
	private JRadioButton Movies;
	private JRadioButton Artist;
	private JRadioButton Sports;
    public void start()
    {
        l=new JLabel();
        l.setText(" Enter your choice :");
    
        JTextField txt=new JTextField(25);
        JButton but=new JButton();
        but.setText("Submit");
        JTextArea textArea = new JTextArea(25, 25);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
       // final String newline = "\n";
       //textArea.append(text + newline);
    	ButtonGroup bg1 = new ButtonGroup( );
    	
        add(l);
      
        add(txt);
        add(but);
            
        but.addActionListener(
                new ActionListener(){
                    public void actionPerformed(
                            ActionEvent e){
                                            l.setText("Button clicked");
                                          }
                                    }
                            );
        

        

           

        	JRadioButton Movies = new JRadioButton("Movies");
        	JRadioButton Artist = new JRadioButton("Artist");
        	JRadioButton Sports = new JRadioButton("Sports");
      
        	bg1.add(Movies);
        	bg1.add(Artist);
        	bg1.add(Sports);
        	
        
        	
        	this.add(Movies);
        	this.add(Artist);
        	this.add(Sports);
        	
        	 this.setLayout(new GridLayout(1,1));
        	String text="";
        	if(Movies.isSelected())
        	{  	 try{
               	Registry registry = LocateRegistry.getRegistry("localhost");
                
               	QPSInterface server = (QPSInterface)registry.lookup("QPS");
               	           
               	//server.setParameters(new ParaWrapper());
                            
            server.getMovie(txt.getText());
            
               	        } catch (Exception e) {
               	            System.err.println("Exception");
               	            e.printStackTrace();
               	        }
            
        	}
        	if(Artist.isSelected())
        	{   	 try{
               	Registry registry = LocateRegistry.getRegistry("localhost");
                
               	QPSInterface server = (QPSInterface)registry.lookup("QPS");
               	           
               	//server.setParameters(new ParaWrapper());
                            
            server.getArtist(txt.getText());
            
               	        } catch (Exception e) {
               	            System.err.println("Exception");
               	            e.printStackTrace();
               	        }
        	}
        	if(Sports.isSelected())
        	{   	 try{
               	Registry registry = LocateRegistry.getRegistry("localhost");
                
               	QPSInterface server = (QPSInterface)registry.lookup("QPS");
               	           
               	//server.setParameters(new ParaWrapper());
                            
            server.getTeam(txt.getText());
            
               	        } catch (Exception e) {
               	            System.err.println("Exception");
               	            e.printStackTrace();
               	        }
        	}
        	
        	
        	add(textArea);
        	
        	   setLayout(new FlowLayout());
               setSize(300,300);
               setVisible(true);
               setDefaultCloseOperation(EXIT_ON_CLOSE);
}
        	
        
        
    
     
    public static void main(String args[])
    {
        new Form().start();
        
        
    }
}