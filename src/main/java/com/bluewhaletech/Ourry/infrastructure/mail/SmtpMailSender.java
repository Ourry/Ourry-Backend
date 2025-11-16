package com.bluewhaletech.Ourry.infrastructure.mail;

import com.bluewhaletech.Ourry.presentation.member.dto.EmailDTO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class SmtpMailSender {
    @Value("${mail.smtp.address}")
    private String address;
    @Value("${mail.smtp.personal}")
    private String personal;

    private final JavaMailSender mailSender;

    @Autowired
    public SmtpMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendMail(EmailDTO dto) throws MessagingException, UnsupportedEncodingException {
        /* 이메일 발송 */
        MimeMessage message = createMessage(dto.getEmail(), dto.getTitle(), dto.getText());
        mailSender.send(message);
        return "SUCCESS";
    }

    private MimeMessage createMessage(String recipient, String title, String text) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(address, personal));
        message.setRecipients(Message.RecipientType.TO, recipient);
        message.setSubject(title);
        message.setText(text, "UTF-8", "html");
        return message;
    }
}