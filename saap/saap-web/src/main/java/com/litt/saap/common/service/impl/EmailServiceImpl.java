package com.litt.saap.common.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.litt.saap.common.service.IEmailService;

/**
 * 电子邮件服务.
 * 
 * <pre><b>描述：</b>
 *    通过系统邮箱发送邮件
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-11-11
 * @version 1.0
 */
public class EmailServiceImpl implements IEmailService {
	
	@Resource
	private JavaMailSenderImpl mailSender;
	
	private String from;
	
	/**
	 * Send simple.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param content the content
	 */
	public void sendSimple(String to, String subject,String content)
	{
		this.sendSimple(from, to, subject, content);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.IEmailService#sendSimple(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendSimple(String from, String to, String subject,String content)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		this.sendSimple(message);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.IEmailService#sendSimple(org.springframework.mail.SimpleMailMessage)
	 */
	public void sendSimple(SimpleMailMessage message)
	{
		mailSender.send(message);
	}
	
	/**
	 * 发送HTML邮件.
	 *
	 * @param from 发件者
	 * @param to 收件者
	 * @param subject 主题
	 * @param text 文本内容
	 * @param html 内容
	 * @throws MessagingException the messaging exception
	 */
	public void sendMime(String to, String subject, String text,
			String html) throws MessagingException
	{
		this.sendMime(from, to, subject, text, html);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.IEmailService#sendMime(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendMime(String from, String to, String subject, String text, String html) throws MessagingException
	{
		this.sendMime(from, to, subject, text, html, null, null);
	}
	
	public void sendMime(String to, String subject, String text, String html, Map<String, File> attachements, Map<String, File> images) throws MessagingException
	{
		this.sendMime(from, to, subject, text, html, attachements, images);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.IEmailService#sendMime(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map, java.util.Map)
	 */
	public void sendMime(String from, String to, String subject, String text, String html, Map<String, File> attachements, Map<String, File> images) throws MessagingException
	{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		messageHelper.setFrom(from);
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(html, true);
		if(attachements!=null && !attachements.isEmpty())
		{
			Iterator<Entry<String, File>> iter = images.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, File> entry = iter.next();			
				messageHelper.addAttachment(entry.getKey(), entry.getValue());
			}
		}
		if(images!=null && !images.isEmpty())
		{
			Iterator<Entry<String, File>> iter = images.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, File> entry = iter.next();
			    messageHelper.addInline(entry.getKey(), entry.getValue());
			}
		}
		this.sendMime(message);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.IEmailService#sendMime(javax.mail.internet.MimeMessage)
	 */
	public void sendMime(MimeMessage message)
	{
		mailSender.send(message);
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	public static void main(String[] args){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.qq.com");
		mailSender.setPort(25);
		mailSender.setUsername("service@webestlife.com");
		mailSender.setPassword("Service810802");
		//mailSender.setProtocol("smtps");
		mailSender.setDefaultEncoding("UTF-8");
		Properties javaMailProperties = new Properties();
		//javaMailProperties.put("mail.smtps.auth", true);
		//javaMailProperties.put("mail.transport.protocol", "smtps");
		
		mailSender.setJavaMailProperties(javaMailProperties);
		
		EmailServiceImpl impl = new EmailServiceImpl();
		impl.mailSender = mailSender;
		impl.from = "service@webestlife.com";
		impl.sendSimple("ycai@webestlife.com", "中文测试", "中文测试");
	}

}
