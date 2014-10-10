package com.example.sendmaildemo;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.Log;


public class Gmail extends javax.mail.Authenticator
{
	
	String fromEmail;
	String fromPassword;
	String toEmail;
	String emailSubject;
	String emailBody;

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	public Gmail() 
	{
		
	}

	public Gmail(String toEmail, String emailSubject, String emailBody) 
	{
		this.toEmail = toEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		
		fromEmail="felightmail@gmail.com";
		fromPassword="felightrocks";
		
		

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port","465");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.setProperty("mail.host", "smtp.gmail.com");   
		emailProperties.put("mail.smtp.socketFactory.port", "465");
		emailProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		
		
	}
	
	
	public void sendEmail() throws AddressException,MessagingException,UnsupportedEncodingException 

	{
		
		 	//mailSession = Session.getDefaultInstance(emailProperties,this);
				mailSession = Session.getDefaultInstance(emailProperties,null);
				
				emailMessage = new MimeMessage(mailSession);
				  
				emailMessage.setFrom(new InternetAddress("felightmail@gmail.com"));
				
				//emailMessage.setSender(new InternetAddress("bhojaraju.pg@gmail.com"));
				
				Log.i("create","after setfrom");
			/*	for (String toEmail : toEmailList) 
				{
					Log.i("GMail","toEmail: "+toEmail);
					emailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
							
				}*/
				
			
				emailMessage.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(toEmail));
			
				emailMessage.setSubject(emailSubject);
				
				emailMessage.setContent(emailBody, "text/html");// for a html email
				//emailMessage.setText(emailBody);// for a text email
				
			
		
				try{
						Transport transport = mailSession.getTransport("smtp");
						
						Log.i("GMail", "before connecting");
						transport.connect("smtp.gmail.com", fromEmail, fromPassword);
						
						
						Log.i("GMail","before transport send");
						
						transport.sendMessage(emailMessage,emailMessage.getAllRecipients());
						
						transport.close();
					}
					catch(Exception e)
					{
						Log.i("catch", e.getLocalizedMessage()+"");
					}
			
	}
	
}
