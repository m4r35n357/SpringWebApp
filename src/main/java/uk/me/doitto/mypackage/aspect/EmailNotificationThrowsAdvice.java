package uk.me.doitto.mypackage.aspect;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import uk.me.doitto.mypackage.globals.Constants;
import uk.me.doitto.mypackage.service.InetService;

public final class EmailNotificationThrowsAdvice implements ThrowsAdvice {

	private MailSender mailSender;
	public void setMailSender (MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	private SimpleMailMessage messageTemplate;
	public void setMessageTemplate (SimpleMailMessage messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
	
	public void afterThrowing (Exception e) throws Throwable {
		StringBuilder builder = new StringBuilder("Exception from " + InetService.getHostName() + ": ");
		builder.append(e.getMessage().toString());
		builder.append(Constants.lineSeparator);
		for (StackTraceElement element : e.getStackTrace()) {
			builder.append(element.toString());
			builder.append(Constants.lineSeparator);
		}
		SimpleMailMessage message = new SimpleMailMessage(messageTemplate);
		message.setText(builder.toString());
		mailSender.send(message);
	}
}
