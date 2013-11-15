package com.litt.saap.common.listener;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件接收监听器.
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
 * @since 2013-11-11
 * @version 1.0
 */
public class MailReceiveListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MailReceiveListener.class);
		
	private String host;
	
	private int port = 110;	
	
	/** 是否需要用户认证. */
	private boolean isNeedAuth = true;
	
	/** 是否需要SSL. */
	private boolean isNeedSSL = true;
	
	private String username;
	
	private String password;
	
	/** 附件保存路径. */
	private String attachementStorePath;
	
	
    // 与邮件服务器连接后得到的邮箱
    private Store store;
    // 收件箱
    private Folder folder;
    // 收件箱中的邮件消息
    private Message[] messages;
    // 当前正在处理的邮件消息
    private Message currentMessage;
    
    public Properties initProps()
    {
    	// 准备连接服务器的会话信息 
        Properties props = new Properties(); 
        props.setProperty("mail.store.protocol", "pop3");       // 协议 
        props.setProperty("mail.pop3.port", ""+port);             // 端口 
        props.setProperty("mail.pop3.host", host);    // pop3服务器 
        return props;
    }
    
    /**
     * 接收所有邮件
     */
    public void receiveAll()
    {
    	
    }
    
    private boolean connect() throws MessagingException {
    	Properties props = this.initProps();
    	Session session = Session.getInstance(props); 
        //创建store,建立连接
        this.store = session.getStore("pop3");        

        logger.debug("try to connect pop3 server:{}...", new Object[]{host});
        if(isNeedAuth)
        	this.store.connect(username, password);
        else	
        	this.store.connect();
        
        return true;
    }
   
    private void openInBoxFolder()throws MessagingException {
        this.folder = store.getFolder("INBOX");
        // 只读
        folder.open(Folder.READ_ONLY);
        // 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数 
        logger.debug("Mail stat, Total:{}, News:{}, Unread:{}, Deleted:{} ", new Object[]{folder.getMessageCount(), folder.getNewMessageCount(), folder.getUnreadMessageCount(), folder.getDeletedMessageCount()});         
    }
   
    private void closeConnectionQuietly() {
    	logger.debug("try to close mail connection...");
        if (this.folder!=null && this.folder.isOpen()) {
        	try {
				this.folder.close(true);
			} catch (MessagingException e) {
				logger.error("Close mail folder error.", e);
			}
        }
        if(this.store!=null && this.store.isConnected())
        {
        	try {
				this.store.close();
			} catch (MessagingException e) {
				logger.error("Close mail store error.", e);
			}
        }
        logger.debug("Close mail connection success.");
    }
	
	public void onReceive()
	{
		
	}
	
	

}
