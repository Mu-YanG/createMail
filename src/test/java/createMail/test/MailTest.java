package createMail.test;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;

public class MailTest {
	
	@Test
	public void mailTest() throws MessagingException{
		//创建propertie
		Properties properties = new Properties();
		//邮件的服务器地址
		properties.setProperty("mail.host", "smtp.sina.com");
		//发送邮件的协议
		properties.setProperty("mail.transport.protocol", "smtp");
		//邮件的认证
		properties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		//创建message对象使用session配置
		MimeMessage message = new MimeMessage(session);
		//发件人
		message.setFrom("lvyangtest@sina.com");
		//收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(""));
		//标题
	/*	message.setSubject("你好，帅哥");
		//文件内容      //	message.setText("你好本次你好帅：12312312");
		message.setContent("<h1 style='color:red;'>你好帅</h1>", "text/html;charset=utf-8");*/
		MimeMultipart mimeMultipart = new MimeMultipart();  
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent("<h1 style='color:red;'>你好帅</h1>", "text/html;charset=utf-8");
		bodyPart.setDataHandler(new DataHandler(new FileDataSource(new File("d"))));
		bodyPart.setFileName("123.xml");
		mimeMultipart.addBodyPart(bodyPart);
		message.setContent(mimeMultipart);      ;
		//创建transprot对象
		Transport transport = session.getTransport();
		//链接服务器
		transport.connect("smtp.sina.com","lvyangtest@sina.com","lvyangtest");
		//发送信息
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		
		
		
		
		
	}
	
}
