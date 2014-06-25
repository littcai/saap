package com.litt.saap.common.service;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-11-12
 * @version 1.0
 */
public interface IEmailService {
	
	/**
	 * Send simple.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param content the content
	 */
	public void sendSimple(String to, String subject,String content);

	/**
	 * 发送简单邮件.
	 *
	 * @param from 发件者
	 * @param to 收件者
	 * @param subject 主题
	 * @param content 内容
	 */
	public void sendSimple(String from, String to, String subject,
			String content);

	/**
	 * 发送简单邮件.
	 *
	 * @param message the message
	 */
	public void sendSimple(SimpleMailMessage message);
	
	/**
	 * 发送HTML邮件.
	 *
	 * @param to 收件者
	 * @param subject 主题
	 * @param text 文本内容
	 * @param html 内容
	 * @throws MessagingException the messaging exception
	 */
	public void sendMime(String to, String subject, String text,
			String html) throws MessagingException;

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
	public void sendMime(String from, String to, String subject, String text,
			String html) throws MessagingException;
	
	public void sendMime(String to, String subject, String text, String html, Map<String, File> attachements, Map<String, File> images) throws MessagingException;

	/**
	 * 发送HTML邮件.
	 *
	 * @param from 发件者
	 * @param to 收件者
	 * @param subject 主题
	 * @param text 文本内容
	 * @param html 内容
	 * @param attachements 附件
	 * @param images 内嵌图片
	 * @throws MessagingException the messaging exception
	 */
	public void sendMime(String from, String to, String subject, String text,
			String html, Map<String, File> attachements,
			Map<String, File> images) throws MessagingException;

	/**
	 * 发送HTML邮件.
	 *
	 * @param mailMessage the mail message
	 */
	public void sendMime(MimeMessage message);
	
	public String getFrom();

}