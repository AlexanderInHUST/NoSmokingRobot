package util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * Created by tangyifeng on 2017/8/30.
 * Email: yifengtang_hust@outlook.com
 */
public class MailSender {

    private static final String ACCOUNT = "976390503@qq.com";
    private static final String PASSWORD = "ieszzhjvxqlebdjh";
    private static final int PORT = 465;
    private static final String SMTP_HOST = "smtp.qq.com";

    public boolean sendMail(String subject, String content, String toWhom, File file) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(false);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ACCOUNT));
            message.addRecipients(Message.RecipientType.TO, toWhom);
            message.setSubject(subject);
            message.addHeader("charset", "UTF-8");

            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            contentPart.setHeader("Content-Type", "text/html; charset=GBK");
            multipart.addBodyPart(contentPart);

            if (file.exists()) {
                MimeBodyPart fileBody = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                fileBody.setDataHandler(new DataHandler(source));
                sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                fileBody.setFileName("=?GBK?B?"
                        + enc.encode(file.getName().getBytes()) + "?=");
                multipart.addBodyPart(fileBody);
            }

            message.setContent(multipart);
            message.setSentDate(new Date());
            message.saveChanges();
            Transport transport = session.getTransport("smtp");

            transport.connect(SMTP_HOST, PORT, ACCOUNT, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (AddressException e1) {
            e1.printStackTrace();
        } catch (MessagingException e2) {
            e2.printStackTrace();
        }
        return false;
    }
}
