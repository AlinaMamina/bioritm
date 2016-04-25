package email;

import com.sun.mail.smtp.SMTPTransport;
import org.jetbrains.annotations.NotNull;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    private final String email;
    private final String password;

    public EmailSender(@NotNull final String email, @NotNull final String password) {
        this.email = email;
        this.password = password;
    }

    // will be better create class Letter
    public void send(@NotNull final String recipient, @NotNull final String subject, @NotNull final String content) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.mail.ru");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // create a new message
        final MimeMessage msg = new MimeMessage(session);

        // set the FROM and TO fields
        msg.setFrom(new InternetAddress(email));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));

        msg.setSubject(subject);
        msg.setText(content, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.mail.ru", email, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }
}
