package com.DigitalVisionProject.service.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailSenderService implements EmailSenderServiceImp {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }

    @Override
    @Async
    public void send(String subject,String to, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("digitalvision117@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e){
            LOGGER.error("failed to send"+ subject +"to:" + to, e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
