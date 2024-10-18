package ru.vsu.cs.sakovea.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.vsu.cs.sakovea.config.MailConfig;


@Component
@RequiredArgsConstructor
public class EmailSenderService {
    
    private final MailConfig mailConfig;

    public void sendConfirmationEmail(String email, String token) {
        String link = "http://localhost:8080/api/auth/confirm?token=" + token;

        MimeMessage message = mailConfig.getMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        System.out.println(email);

        try {
            helper.setFrom("sakov.eu@yandex.ru");
            helper.setTo(email);
            helper.setSubject("Confirm your email");
            helper.setText("Click the link to confirm your email: " + link, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }

        mailConfig.getMailSender().send(message);
    }
}
