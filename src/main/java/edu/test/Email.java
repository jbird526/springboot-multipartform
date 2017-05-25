package edu.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Email {

	protected final Logger LOG = LoggerFactory.getLogger( this.getClass() );
	
	@Autowired 
	private JavaMailSender javaMailSender;
    
	@Autowired   
    Email(JavaMailSender javaMailSender) {   
            this.javaMailSender = javaMailSender;   
    }
    //mailTo, mailReplyTo, mailFrom, mailSubject, mailText
    public void sendMail(String mailTo, String mailReplyTo, String mailFrom, String mailSubject, String mailText){
    	  try {    		
		        SimpleMailMessage mailMessage = new SimpleMailMessage();		
		        mailMessage.setTo(mailTo);		
		        mailMessage.setReplyTo(mailReplyTo);		
		        mailMessage.setFrom(mailFrom);		
		        mailMessage.setSubject(mailSubject);		
		        mailMessage.setText(mailText);		
		        javaMailSender.send(mailMessage);
    	  } catch (MailException exception) {
              exception.printStackTrace();
          }
    }
    
}
