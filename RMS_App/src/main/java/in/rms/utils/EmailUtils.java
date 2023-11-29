package in.rms.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleMessage(String to, String subject,String text,List<String> list){
		try {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		 
		 MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		 
		 mimeMessageHelper.setTo(to);
		 mimeMessageHelper.setSubject(subject);
		 mimeMessageHelper.setText(text);
		 if(null!=list && list.size()>0) {
			 mimeMessageHelper.setCc(getCcArray(list));
			 javaMailSender.send(mimeMessage);
		 }
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	private String[] getCcArray(List<String> ccList) {
		
		String[] cc = new String[ccList.size()];
		for(int i =0;i<ccList.size();i++) {
			
			cc[i]=ccList.get(i);
		}
		return cc;
	}
	
	
	public void forgotMail(String to, String subject,String password) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";


		message.setContent(htmlMsg,"text/html");
		javaMailSender.send(message);
	}
}
