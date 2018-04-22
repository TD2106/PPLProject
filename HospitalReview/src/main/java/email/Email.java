package email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import aes.AES;

public class Email {
    private static String USER_NAME = "";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = ""; // GMail password

    public static void sendConfirmationMail(String userEmail, String recipient) {
        String subject = "Confirmation for Hospital ";
        String[] to = {recipient};
        String confirmationCode = AES.encrypt(userEmail, "hospitalreview");
        String body = "Thank you for registering an account at our website.\nPlease click the link below" +
                " confirm your registration:\n" +
                "" + confirmationCode;
        sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
