package com.se.sample;

import com.se.sample.dto.EmailDTO;
import com.se.sample.exception.BusinessException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Value("${spring.mail.username:}")
    private String EMAIL_FROM;

    @Value("${spring.mail.properties.mail.smtp.from:}")
    private String EMAIL_FROM_SMTP;


    @Value("${mail.useDns:false}")
    private boolean useDns;

    // fallback host/port when MX lookup fails
    @Value("${mail.dns.fallback-host:mail-00.ligazakon.net}")
    private String dnsFallbackHost;

    @Value("${mail.dns.fallback-port:25}")
    private int dnsFallbackPort;

    // optional envelope-from for MAIL FROM; if empty, use spring.mail.username
    @Value("${mail.dns.envelope-from:}")
    private String dnsEnvelopeFrom;

    private final DnsLookupService dnsLookupService;

    private final JavaMailSender mailSender;

    public EmailService(DnsLookupService dnsLookupService, JavaMailSender mailSender) {
        this.dnsLookupService = dnsLookupService;
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailDTO emailDTO) {
        if (useDns) {
            sendEmailDirectly(emailDTO);
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getBody());
        if (EMAIL_FROM != null && !EMAIL_FROM.isBlank()) {
            message.setFrom(EMAIL_FROM);
        }
        mailSender.send(message);
    }

    public void sendHtmlEmail(EmailDTO emailDTO) {
        if (useDns) {
            // build a simple MIME and send via direct MX
            MimeMessage mime = new MimeMessage(Session.getInstance(new Properties()));
            try {
                String from = (dnsEnvelopeFrom != null && !dnsEnvelopeFrom.isBlank()) ? dnsEnvelopeFrom : EMAIL_FROM;
                if (from != null && !from.isBlank()) {
                    mime.setFrom(new InternetAddress(from));
                }
                mime.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getTo()));
                mime.setSubject(emailDTO.getSubject());
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
                mime.setContent(htmlContent, "text/html; charset=utf-8");
                sendRawMimeDirect(mime, emailDTO.getTo());
            } catch (Exception e) {
                throw new BusinessException("Unable to send email via DNS/MX", e);
            }
            return;
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            if (EMAIL_FROM != null && !EMAIL_FROM.isBlank()) {
                helper.setFrom(EMAIL_FROM);
            }
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
            throw new BusinessException("Unable to send email", e);
        }
    }

    public void sendEmailDirectly(EmailDTO emailDTO) {
        String to = emailDTO.getTo();
        String mxHost = null;
        int mxPort = dnsFallbackPort;
//        try {
//            mxHost = dnsLookupService.getMxRecord(to);
//        } catch (Exception e) {
//            // fallback
//            mxHost = dnsFallbackHost;
//        }

        mxHost= dnsFallbackHost;
        Properties props = new Properties();
        props.put("mail.smtp.host", mxHost);
        props.put("mail.smtp.port", String.valueOf(mxPort));
        props.put("mail.smtp.auth", "false");

        Session session = Session.getInstance(props);
        try {
            MimeMessage message = new MimeMessage(session);
            String from = (dnsEnvelopeFrom != null && !dnsEnvelopeFrom.isBlank()) ? dnsEnvelopeFrom : EMAIL_FROM_SMTP;
            if (from != null && !from.isBlank()) {
                message.setFrom(new InternetAddress(from));
            }
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(emailDTO.getSubject());
            message.setText(emailDTO.getBody());
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Direct email delivery failed via host: " + mxHost, e);
        }
    }

    private void sendRawMimeDirect(MimeMessage message, String to) {
        String mxHost;
        try {
            mxHost = dnsLookupService.getMxRecord(to);
        } catch (Exception e) {
            mxHost = dnsFallbackHost;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", mxHost);
        props.put("mail.smtp.port", String.valueOf(dnsFallbackPort));
        props.put("mail.smtp.auth", "false");

        Session session = Session.getInstance(props);
        try {
            MimeMessage outbound = new MimeMessage(session);
            outbound.setContent(message.getContent(), message.getContentType());
            outbound.setSubject(message.getSubject());
            if (message.getFrom() != null && message.getFrom().length > 0) {
                outbound.setFrom(message.getFrom()[0]);
            }
            outbound.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(outbound);
        } catch (Exception e) {
            throw new RuntimeException("Direct MIME delivery failed via host: " + mxHost, e);
        }
    }
}
