package com.se.sample.EmailController;


import com.se.sample.EmailService;
import com.se.sample.dto.Base;
import com.se.sample.dto.EmailDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/text-mail")
    public Base<Void> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);

        return new Base<>(null, "Successfully sent email to " + emailDTO.getTo());
    }

    @PostMapping("/html-mail")
    public Base<Void> sendHtmlEmail(@RequestBody @Valid EmailDTO emailDTO) {
        emailService.sendHtmlEmail(emailDTO);

        return new Base<>(null, "Successfully sent email to " + emailDTO.getTo());
    }
}