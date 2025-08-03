package chursov;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
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
                return new PasswordAuthentication(
                        CONFIG.getProperty("email.user"),
                        CONFIG.getProperty("email.password")
                );
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(CONFIG.getProperty("email.user")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(MimeUtility.encodeText(EMAIL_SUBJECT, "UTF-8", null));

        Multipart multipart = new MimeMultipart();

        // HTML body
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html; charset=utf-8");
        multipart.addBodyPart(htmlPart);

        // Attachment: Russian certificate
        MimeBodyPart ruAttachment = new MimeBodyPart();
        ruAttachment.attachFile(new File(certPathRu));
        ruAttachment.setFileName("Certificate_RU.png"); // читаемое имя
        multipart.addBodyPart(ruAttachment);

        // Attachment: English certificate
        MimeBodyPart enAttachment = new MimeBodyPart();
        enAttachment.attachFile(new File(certPathEn));
        enAttachment.setFileName("Certificate_EN.png");
        multipart.addBodyPart(enAttachment);

        // Set content and send
        message.setContent(multipart);
        Transport.send(message);

        System.out.printf("Email sent to %s%n", to);
    }

}
