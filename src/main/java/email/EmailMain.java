package email;

import javax.mail.MessagingException;

public class EmailMain {
    public static void send(String recipient, String content) throws MessagingException {
        final String email = "vashi-bioritmi@mail.ru";
        final String password = "password1984";

        final EmailSender sender = new EmailSender(email, password);

        final String subject = "Биоритмы на неделю";
        sender.send(recipient, subject, content);
    }
}
