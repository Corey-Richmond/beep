/*
 * @author Anusha Sura
 */

package client;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
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

import facebook.FacebookClient.Domain;

import qps.QPSInterface;

import Authentication.AuthenticationInterface;
import LoadBalancer.Balancer;
 
public class Form extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l=new JLabel();
	JFrame frame = new JFrame();
	ButtonGroup bg1 = new ButtonGroup( );
	
	ArrayList<String[]> result;
	ArrayList<String> resultnew;
	ArrayList<String> resultActor;
	ArrayList<String> resultArtist;
	ArrayList<String> resultAthlete;
	String resultSport="";
	String resultSportAthlete="";
	String resultTeamAthlete="";
	
	ArrayList<String> resultteam;
	private JRadioButton Actor = new JRadioButton("Actor");
	private JRadioButton Movies = new JRadioButton("Movies");
	private JRadioButton Artist = new JRadioButton("Artist");;
	private JRadioButton Athlete = new JRadioButton("Athlete");;
	private JRadioButton Team = new JRadioButton("Team");;
	private JTextField txt=new JTextField(25);
	private JButton but=new JButton();
    
     private JTextArea textArea = new JTextArea(25, 25);
     private JScrollPane scrollPane = new JScrollPane(textArea);
     private Registry registry; 
     private Balancer lb;
     
     public Form(){
    	 try {
			registry = LocateRegistry.getRegistry(2001);
			lb = (Balancer) registry.lookup("LoadBalancer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
    public void start()
    {
    	 
        l.setText(" Enter your choice :");
    
       // but.setText("Submit");
        textArea.setEditable(false);
       // final String newline = "\n";
       //textArea.append(text + newline);
    	
        add(l);
      
        add(txt);
        
      
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
        	            			textArea.setText(null);        	                    
        	                   	QPSInterface server = lb.getQPS();
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	                // changes done by anusha
        	                // result=server.getCitiesByActor(txt.getText());
        	                
        	                   	resultActor=server.getCitiesByActor1(txt.getText());
        	                String text;
							if(resultActor.size()==0)
							{
        	                	text="No result found\nWe are listening in\non social networks for more data\nplease try again later.";
        	                	textArea.setText(null);
							}
        	                else
        	                {
        	                	// changes done by anusha
            	                //	text=result.toString();
    							//	text=resultnew.toString();
        	                	for(String result: resultActor)
								{   textArea.append(result + "\n");
								}
        	                }
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
        	            			textArea.setText(null);
        	                    
        	                   	QPSInterface server = lb.getQPS();
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	              // changes done by me                  
        	              //  result=server.getCitiesByMovie(txt.getText());
        	                   	resultnew=server.getCitiesByMovie1(txt.getText());
        	                String text;
							//if(result.size()==0)
								if(resultnew.size()==0)
        	                	{
	        	                	text="No result found\nWe are listening in\non social networks for more data\nplease try again later.";
									textArea.setText(text);
									server.addNewEntry(Domain.MOVIE, txt.getText());
        	                	}
        	                else
        	                {
        	                 // changes done by anusha
        	                //	text=result.toString();
							//	text=resultnew.toString();
								for(String result: resultnew)
								{   textArea.append(result + "\n");
								}
        	                }
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
        	            			textArea.setText(null);	
        	                    
        	                   	QPSInterface server = lb.getQPS();
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                 // changes done by anusha               
        	               // result=server.getCitiesByArtist(txt.getText());
        	                 resultArtist=server.getCitiesByArtist1(txt.getText());
        	                String text;
							if(resultArtist.size()==0)
							{
        	                	text="No result found\nWe are listening in\non social networks for more data\nplease try again later.";
								textArea.setText(text);
								server.addNewEntry(Domain.ARTIST, txt.getText());
							}
        	                else
        	                {
        	                // changes done by anusha
        	                //	text=result.toString();
        	                //    textArea.setText(text);
        	                	for(String result: resultArtist)
								{   textArea.append(result + "\n");
								}
        	                }
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
        	            			textArea.setText(null);
        	            		        	                    
        	                   	QPSInterface server = lb.getQPS();
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                                
        	               // result=server.getCitiesByAthlete(txt.getText());
        	                // changes done by anusha
        	                   	resultSportAthlete= server.getSportByAthlete1(txt.getText());
        	                   	resultTeamAthlete=server.getTeamtByAthlete1(txt.getText());
        	                   	resultAthlete= server.getCitiesByAthlete1(txt.getText());
        	                String text;
							if(result.size()==0){
        	                	text="No result found\nWe are listening in\non social networks for more data\nplease try again later.";
								server.addNewEntry(Domain.ATHLETE, txt.getText());
							}
        	                else
        	                {
        	                textArea.setText(resultSportAthlete);
							textArea.append("\n");
							textArea.setText(resultTeamAthlete);
							textArea.append("\n");
							for(String result: resultAthlete)
							{   textArea.append(result + "\n");
							}
        	                }
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
        	            			textArea.setText(null);      	            			
        	                    
        	                   	QPSInterface server = lb.getQPS();
        	                   	           
        	                   	//server.setParameters(new ParaWrapper());
        	                    // changes done by anusha
        	                //result=server.getCitiesByTeam(txt.getText());
        	                   	
        	                resultSport=server.getSportByTeam1(txt.getText());
        	                resultteam=server.getCitiesByTeam1(txt.getText());
        	                String text;
							if(resultteam.size()==0)
							{
        	                	text="No result found\nWe are listening in\non social networks for more data\nplease try again later.";
								textArea.setText(text);
								server.addNewEntry(Domain.TEAM, txt.getText());
							}
        	                else
        	                {
        	                textArea.setText(resultSport);
							textArea.append("\n");
							for(String result: resultteam)
							{   textArea.append(result + "\n");
							}
        	                }
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
                //Athlete.addActionListener(AthleteActionListener);
                Team.addActionListener(TeamActionListener);
        	this.add(Actor);
        	this.add(Movies);
        	this.add(Artist);
        	//this.add(Athlete);
        	this.add(Team);
        	
        	 this.setLayout(new GridLayout(1,1));
      
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