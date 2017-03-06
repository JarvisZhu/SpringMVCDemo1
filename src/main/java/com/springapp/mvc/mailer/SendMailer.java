package com.springapp.mvc.mailer;

import java.util.HashMap;
import java.util.Map;

import javacommon.base.BaseMailer;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.rapid.framework.concurrent.async.AsyncToken;
import com.rapid.framework.concurrent.async.IResponder;

@Component
public class SendMailer extends BaseMailer{
	/**
	 * 使用freemarker模板创建邮件消息
	 */
	public SimpleMailMessage createConfirmBill(String username) {
		SimpleMailMessage msg = newSimpleMsgFromTemplate("测试邮件subject");
		try {
			
			msg.setTo("syic@tom.com");
			final Map model = new HashMap();
			model.put("username", username);
			String text = processTemplate("notify.flt", model);
			msg.setText(text);
			
		} catch (Exception ex) {
            System.out.println("Failure:" + ex);
        }
		return msg;
	}
	
	/**
	 * 发送邮件
	 */
	public AsyncToken sendConfirmMail(final String email, final Map model, final String title) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	        
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	String mailfrom = "524261314@163.com";//发件人地址
            	String contentType = "text/html;charset=UTF-8";//邮件类型
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                InternetAddress from = new InternetAddress(mailfrom, "智慧商圈平台告警邮件");
                mimeMessage.setFrom(from);
                
                String text = processTemplate("notify.flt", model);
                mimeMessage.setContent(text, contentType);
                mimeMessage.addHeader("X-Mailer", "Java Mailer");
                mimeMessage.setSubject(title);
            }
        };

		//转换为html邮件并发送,另有一个参数可以指定发件人名称
		//AsyncToken token = getAsyncJavaMailSender().send(SimpleMailMessageUtils.toHtmlMsg(msg,"小明")); 
        AsyncToken token = getAsyncJavaMailSender().send(preparator);
		//处理邮件发送结果
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				System.out.println("[ERROR] email send fail, cause:" + fault);
			}

			public void onResult(Object result) {
				System.out.println("[INFO] email send success");
			}
		});
		
		//返回token可以用于外部继续监听
		return token;
	}
}
