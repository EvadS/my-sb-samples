package com.se.sample;



import com.se.sample.dto.EmailDTO;
import com.se.sample.exception.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getBody());
        message.setFrom(EMAIL_FROM);
        mailSender.send(message);
    }

    public void sendHtmlEmail(EmailDTO emailDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_FROM);
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());

            String htmlContent = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                        <title>Spring Mail Sender</title>
                    </head>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6; padding: 20px;">
                        <h2>Spring Mail Sender</h2>
                        <p>%s</p>
                    </body>
                    </html>
                    """.formatted(emailDTO.getBody());

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BusinessException("Unable to send email");
        }
    }
}
