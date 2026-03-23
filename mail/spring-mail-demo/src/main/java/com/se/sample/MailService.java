package com.se.sample;

import com.se.sample.model.User;
import com.se.sample.model.UserRegisteredEvent;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {


    private final JavaMailSender mailSender;

    @Value("${value.from.file}")
    private String welcomeMessage;


    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostConstruct
    public void init() {
        String mailTo = "Yevhen.Skyba@ligazakon.ua";
        try {
            sendHtml(mailTo, "test subject", "<h2>Письмо-тест работы smtp-клиента</h2>");

        } catch (MessagingException e) {
            int aaa = 0;
            e.printStackTrace();
        }
    }

    public void sendPlainText(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendHtml(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // To get the Charset object
        Charset utf8Charset = StandardCharsets.UTF_8;
        // To get the string name
        String utf8Name = StandardCharsets.UTF_8.name();

        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true means this is HTML
        mailSender.send(message);
    }
}
