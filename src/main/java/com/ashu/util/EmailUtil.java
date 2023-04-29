package com.ashu.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailSender;

	public boolean mailSend(String subject, String body, String to) {

		boolean isSent = false;
		try {

			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			mailSender.send(msg);

			isSent = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;

	}

}
