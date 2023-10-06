import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailTLS {
    public static void sendEmail(emailObject email) {
        String recipientEmail = email.getRecipientEmail();
        String subject = email.getEmailSubject();
        String body = email.getEmailContent();
        final String username = "baqirhabeeb1040@gmail.com";
        final String password = "wveombktgxvwkxzl";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("baqirhabeeb1040@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}