package longND.fpt.home.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMail {
	private final static String MAIL = "longndhe140970@fpt.edu.vn";

	@Autowired
	private JavaMailSender emailSender;

	public void sentEmail(String toEmail, String subject, String text) {

		// Mail info
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(subject);
			message.setFrom(MAIL);
			message.setTo(toEmail);
			message.setText(text);

			emailSender.send(message);
			System.out.println("Message sent successfully...");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
