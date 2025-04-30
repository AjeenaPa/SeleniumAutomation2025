package emailDemo;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessageRemovedException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void main(String[] args) {
		final String senderEmail = "AjeenaTestuse@gmail.com";
		final String appPasswordString = "efonixynsglswqdr";
		final String receiverEmailString = "AjeenaTestuse@gmail.com";
		
		//SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");  
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		//create a session with authentication
		Session session = Session.getInstance(prop,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPasswordString);
			}
		});
		
		session.setDebug(true);
		
		try {
			//create email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmailString));
			message.setSubject("Test email from Selenium Automation");
			message.setText("Hello\n This is a test email fro Java regards QA");
			
			
			//Email body
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \n\n This is a test email fro Java regards QA");
			
			//Email attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String path = System.getProperty("user.dir")+"/reports/ExtentReport.html";
			attachmentPart.attachFile(new File(path));
			
			//Combine email body and attachment parts
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
			
			//send email
			Transport.send(message);
			System.out.println("Email sent successfully");
		} catch (Exception  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
