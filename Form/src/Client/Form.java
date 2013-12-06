/*
 * @author Anusha Sura
 */

package Client;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

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
	JLabel l=new JLabel();
	JFrame frame = new JFrame();
	ButtonGroup bg1 = new ButtonGroup( );
	
	ArrayList<String> result;
	private JRadioButton Actor = new JRadioButton("Actor");
	private JRadioButton Movies = new JRadioButton("Movies");
	private JRadioButton Artist = new JRadioButton("Artist");;
	private JRadioButton Athlete = new JRadioButton("Athlete");;
	private JRadioButton Team = new JRadioButton("Team");;
	private JTextField txt=new JTextField(25);
	private JButton but=new JButton();
    
     private JTextArea textArea = new JTextArea(25, 25);
     private JScrollPane scrollPane = new JScrollPane(textArea); 
    
    public void start()
    {
    	 
        l.setText(" Enter your choice :");
    
       // but.setText("Submit");
        textArea.setEditable(false);
       // final String newline = "\n";
       //textArea.append(text + newline);
    	
        add(l);
      
        add(txt);
        
         //    JRadioButton Actor = new JRadioButton("Actor");
        //	JRadioButton Movies = new JRadioButton("Movies");
        //	JRadioButton Artist = new JRadioButton("Artist");
        //    JRadioButton Athlete = new JRadioButton("Athlete");
       // 	JRadioButton Team = new JRadioButton("Team");
      
        	bg1.add(Actor);
        	bg1.add(Movies);
        	bg1.add(Artist);
        	bg1.add(Athlete);
        	bg1.add(Team);
        	ActionListener ActorActionListener = new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        if(e.getSource() instanceof JRadioButton){
        	            JRadioButton radioButton = (JRadioButton) e.getSource();
        	            if(radioButton.isSelected()){
        	            	if(Actor.isSelected())
        	            	{   	
        	            		try{
        	                   	Registry registry = LocateRegistry.getRegistry("localhost",2001);
        	                    
        	                   	QPSInterface server = (QPSInterface)registry.lookup("QPS");
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                result=server.getCitiesByActor(txt.getText());
        	                String text;
							if(result.size()==0)
        	                	text="No result found";
        	                else
        	                	text=result.toString();
        	                textArea.setText(text);
        	                
        	                System.out.println("asdasf");
        	                   	        } catch (Exception e1) {
        	                   	            System.err.println("Exception");
        	                   	            e1.printStackTrace();
        	                   	        }
        	            	}
        	            }
        	        }
        	    }
        	};
        	ActionListener MoviesActionListener = new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        if(e.getSource() instanceof JRadioButton){
        	            JRadioButton radioButton = (JRadioButton) e.getSource();
        	            if(radioButton.isSelected()){
        	            	if(Movies.isSelected())
        	            	{   	
        	            		try{
        	                   	Registry registry = LocateRegistry.getRegistry("localhost",2001);
        	                    
        	                   	QPSInterface server = (QPSInterface)registry.lookup("QPS");
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                result=server.getCitiesByMovie(txt.getText());
        	                String text;
							if(result.size()==0)
        	                	text="No result found";
        	                else
        	                	text=result.toString();
        	                textArea.setText(text);
        	                
        	                System.out.println("asdasf");
        	                   	        } catch (Exception e1) {
        	                   	            System.err.println("Exception");
        	                   	            e1.printStackTrace();
        	                   	        }
        	            	}
        	            }
        	        }
        	    }
        	};
        	
        	ActionListener ArtistActionListener = new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        if(e.getSource() instanceof JRadioButton){
        	            JRadioButton radioButton = (JRadioButton) e.getSource();
        	            if(radioButton.isSelected()){
        	            	if(Artist.isSelected())
        	            	{   	
        	            		try{
        	                   	Registry registry = LocateRegistry.getRegistry("localhost",2001);
        	                    
        	                   	QPSInterface server = (QPSInterface)registry.lookup("QPS");
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                result=server.getCitiesByArtist(txt.getText());
        	                String text;
							if(result.size()==0)
        	                	text="No result found";
        	                else
        	                	text=result.toString();
        	                textArea.setText(text);
        	                
        	                System.out.println("asdasf");
        	                   	        } catch (Exception e1) {
        	                   	            System.err.println("Exception");
        	                   	            e1.printStackTrace();
        	                   	        }
        	            	}
        	            }
        	        }
        	    }
        	};
        	ActionListener AthleteActionListener = new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        if(e.getSource() instanceof JRadioButton){
        	            JRadioButton radioButton = (JRadioButton) e.getSource();
        	            if(radioButton.isSelected()){
        	            	if(Athlete.isSelected())
        	            	{   	
        	            		try{
        	                   	Registry registry = LocateRegistry.getRegistry("localhost",2001);
        	                    
        	                   	QPSInterface server = (QPSInterface)registry.lookup("QPS");
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                result=server.getCitiesByAthlete(txt.getText());
        	                String text;
							if(result.size()==0)
        	                	text="No result found";
        	                else
        	                	text=result.toString();
        	                textArea.setText(text);
        	                
        	                System.out.println("asdasf");
        	                   	        } catch (Exception e1) {
        	                   	            System.err.println("Exception");
        	                   	            e1.printStackTrace();
        	                   	        }
        	            	}
        	            }
        	        }
        	    }
        	};
        	ActionListener TeamActionListener = new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        if(e.getSource() instanceof JRadioButton){
        	            JRadioButton radioButton = (JRadioButton) e.getSource();
        	            if(radioButton.isSelected()){
        	            	if(Team.isSelected())
        	            	{   	
        	            		try{
        	                   	Registry registry = LocateRegistry.getRegistry("localhost",2001);
        	                    
        	                   	QPSInterface server = (QPSInterface)registry.lookup("QPS");
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                result=server.getCitiesByTeam(txt.getText());
        	                String text;
							if(result.size()==0)
        	                	text="No result found";
        	                else
        	                	text=result.toString();
        	                textArea.setText(text);
        	                
        	                System.out.println("asdasf");
        	                   	        } catch (Exception e1) {
        	                   	            System.err.println("Exception");
        	                   	            e1.printStackTrace();
        	                   	        }
        	            	}
        	            }
        	        }
        	    }
        	};
        		Actor.addActionListener(ActorActionListener);
                Movies.addActionListener(MoviesActionListener);
                Artist.addActionListener(ArtistActionListener);
                Athlete.addActionListener(AthleteActionListener);
                Team.addActionListener(TeamActionListener);
        	this.add(Actor);
        	this.add(Movies);
        	this.add(Artist);
        	this.add(Athlete);
        	this.add(Team);
        	
        	 this.setLayout(new GridLayout(1,1));
      
        	add(textArea);
        	
        	   setLayout(new FlowLayout());
               setSize(300,300);
               setVisible(true);
               setDefaultCloseOperation(EXIT_ON_CLOSE);
}
        	
        
        
    public void action()
    {
     	String text="";
    	l.setText("Clicked!!");
    	if(bg1.getSelection()==Actor)
    	{   	
    		try{
           	Registry registry = LocateRegistry.getRegistry("localhost",2001);
            
           	QPSInterface server = (QPSInterface)registry.lookup("QPS");
           	           
           	//server.setParameters(new ParaWrapper());
                        
        result=server.getCitiesByActor(txt.getText());
        if(result.size()==0)
        	text="No result found";
        else
        	text=result.toString();
        textArea.setText(text);
           	        } catch (Exception e) {
           	            System.err.println("Exception");
           	            e.printStackTrace();
           	        }
    	}
    	
    	else if(bg1.getSelection()==Movies)
    	{  	 try{
           	Registry registry = LocateRegistry.getRegistry("localhost",2001);
            
           	QPSInterface server = (QPSInterface)registry.lookup("QPS");
           	           
           	//server.setParameters(new ParaWrapper());
                        
        result=server.getCitiesByMovie(txt.getText());
        if(result.size()==0)
        	text="No result found";
        else
        	text=result.toString();
        textArea.setText(text);        
           	        } catch (Exception e) {
           	            System.err.println("Exception");
           	            e.printStackTrace();
           	        }
        
    	}
    	else if(bg1.getSelection()==Artist)
    	{   	 try{
           	Registry registry = LocateRegistry.getRegistry("localhost",2001);
            
           	QPSInterface server = (QPSInterface)registry.lookup("QPS");
           	           
           	//server.setParameters(new ParaWrapper());
                        
        result=server.getCitiesByArtist(txt.getText());
        if(result.size()==0)
        	text="No result found";
        else
        	text=result.toString();
        textArea.setText(text);
           	        } catch (Exception e) {
           	            System.err.println("Exception");
           	            e.printStackTrace();
           	        }
    	}
    	
    	else if(Athlete.isSelected())
    	{   	 try{
           	Registry registry = LocateRegistry.getRegistry("localhost",2001);
            
           	QPSInterface server = (QPSInterface)registry.lookup("QPS");
           	           
           	//server.setParameters(new ParaWrapper());
                        
        result=server.getCitiesByAthlete(txt.getText());
        if(result.size()==0)
        	text="No result found";
        else
        	text=result.toString();
        textArea.setText(text); 
           	        } catch (Exception e) {
           	            System.err.println("Exception");
           	            e.printStackTrace();
           	        }
    	}
    	
    	else if(Team.isSelected())
    	{   	 try{
           	Registry registry = LocateRegistry.getRegistry("localhost",2001);
            
           	QPSInterface server = (QPSInterface)registry.lookup("QPS");
           	           
           	//server.setParameters(new ParaWrapper());
                        
        result=server.getCitiesByTeam(txt.getText());
        if(result.size()==0)
        	text="No result found";
        else
        	text=result.toString();
        textArea.setText(text);
           	        } catch (Exception e) {
           	            System.err.println("Exception");
           	            e.printStackTrace();
           	        }
    	}
    	
    	
    }
     
    public static void main(String args[])
    {
        new Form().start();
        
        
    }
}