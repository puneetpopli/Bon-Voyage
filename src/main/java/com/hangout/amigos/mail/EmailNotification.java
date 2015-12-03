package com.hangout.amigos.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotification {

	static final String host = "smtp.gmail.com";
	static final String username = "hangoutamigos";
	static final String password = "Hangoutamigos280"; 

	public static void sendEmailonSignUp(String userEmailAddress, String userName)  throws UnsupportedEncodingException 
	{
		String subject = "Hello from HangoutAmigos!";

		String msgBody = "Hello "+ userName + ",\n\nYour account has been successfully created." +
				"\n\n Thank you for signing up for Hangout Amigos!" + "\n\n We hope you will "
				+ "enjoy planning your hangouts, trips and weekends with us.\n\n" + 
				"- Team Mavericks";

		emailGenerator(userEmailAddress, userName, subject, msgBody);	
	}
	
	public static void emailGenerator(String userEmailAddress, String userName, String subject, String msgBody) throws UnsupportedEncodingException
	{
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);

		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("hangoutamigos@gmail.com", "Hangout Amigos"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(userEmailAddress, "Hello " + userName ));
			msg.setSubject(subject);	
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
		}
	}
}
