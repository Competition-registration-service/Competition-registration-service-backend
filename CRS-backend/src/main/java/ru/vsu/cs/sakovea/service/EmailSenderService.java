package ru.vsu.cs.sakovea.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendConfirmationEmail(String email, String token) {
        String link = "http://localhost:8080/api/auth/confirm?token=" + token;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setSubject("Confirm your email");
            helper.setText("Click the link to confirm your email: " + link, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }

        mailSender.send(message);
    }
}
