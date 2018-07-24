package com.weng.Gradeproj;


//HtmlUnit imports
import java.util.logging.Level;
import org.apache.commons.logging.LogFactory;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.Scanner;
import javax.swing.JOptionPane;
public class App 
{
	public static void  logon() throws Exception 
	{
		 //Turning off annoying JavaScript warnings. Thats what we do, right? Ignore warnings?
		 LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	     java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	     java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

	   
	    //making sure this baby runs
       System.out.println( "Hello World!" );
     
       final WebClient webClient = new WebClient();
       final Scanner scan = new Scanner(System.in);
       //start try block
	    try
	    {
	    	//instantiate page
	    	HtmlPage login = (HtmlPage) webClient.getPage("https://homeaccess.stjohns.k12.fl.us/HomeAccess/Account/LogOn?ReturnUrl=%2fhomeaccess");
	
	    	//find form
	    	HtmlForm form = (HtmlForm) login.getElementsByTagName("form").get(0);
	    	
	    	//set user name and password fields
	    	String user = JOptionPane.showInputDialog("Enter your Username: ");
	    	form.getInputByName("LogOnDetails.UserName").type(user); 

	    	String pass = JOptionPane.showInputDialog("Enter your Password: ");
	    	form.getInputByName("LogOnDetails.Password").type(pass); 
	    	
	    	//find button
	    	HtmlButton button = (HtmlButton) login.getElementsByTagName("button").get(0);
	    	
	    	//click login button
	    	HtmlPage page2 =  button.click();
	    	
	    	//output resulting page
	    	System.out.println(page2.asText()); 
	    	JOptionPane.showMessageDialog(null, page2.asText() , "Grades", 0 );
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    } finally 
	    {
			webClient.close();
			scan.close();
	    }
	}
	
	public static void main( String[] args ) throws Exception
    	{ 
			logon();
	    }
    }
