package util;

  
import java.util.Date;  
import java.util.Properties;  
  
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class SendEmailUtil {  
    public static final String HOST = "smtp.163.com";  
    public static final String PROTOCOL = "smtp";     
    public static final int PORT = 25;  
    public static final String FROM = "a690667607@163.com";//发件人的email  
    public static final String PWD = "19961225";//发件人密码  
    /** 
     * 获取Session 
     * @return 
     */  
    private static Session getSession() {  
        Properties props = new Properties();  
        props.put("mail.smtp.host", HOST);//设置服务器地址  
        props.put("mail.store.protocol" , PROTOCOL);//设置协议  
        props.put("mail.smtp.port", PORT);//设置端口  
        props.put("mail.smtp.auth" , "true");
        props.put("username", FROM);
        props.put("password", PWD);
        Authenticator authenticator = new Authenticator() {  
  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
            	/**D19961225:用户设置的授权码 */
                return new PasswordAuthentication(FROM, "D19961225");  
            }  
        };  
        Session session = Session.getDefaultInstance(props , authenticator);  
          
        return session;  
    }  
      
    public static void send(String toEmail , String content) {  
        Session session = getSession();  
        try {  
            System.out.println("--send--"+content);  
            // Instantiate a message  
            Message msg = new MimeMessage(session);  
   
            //Set message attributes  
            msg.setFrom(new InternetAddress(FROM));  
            InternetAddress[] address = {new InternetAddress(toEmail)};  
            msg.setRecipients(Message.RecipientType.TO, address);  
            msg.setSubject("博客账号激活邮件");  
            msg.setSentDate(new Date());  
            msg.setContent(content , "text/html;charset=utf-8");  
   
            //Send the message  
            Transport.send(msg);  
        }  
        catch (MessagingException mex) {  
            mex.printStackTrace();  
        }  
    }  
}  