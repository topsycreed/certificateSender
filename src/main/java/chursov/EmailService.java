package chursov;

import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class EmailService {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String EMAIL_SUBJECT = "Сертификаты об окончании курса по автоматизации Java";

    private static final Properties CONFIG = Util.loadProperties("src/main/resources/config.properties");

    public static void send(String to, String fullName, String certPathRu, String certPathEn) throws Exception {
        String html = Util.prepareEmailBody("src/main/resources/email/email_template.md", fullName);

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONFIG.getProperty("email.user"), CONFIG.getProperty("email.password"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(CONFIG.getProperty("email.user")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(EMAIL_SUBJECT);

        Multipart multipart = new MimeMultipart();

        // HTML content
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(html, "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);

        // Attachment
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File(certPathRu));
        attachment.attachFile(new File(certPathEn));
        multipart.addBodyPart(attachment);

        message.setContent(multipart);
        Transport.send(message);

        System.out.printf("✅ Email sent to %s%n", to);
    }
}
