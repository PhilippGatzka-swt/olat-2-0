package ch.bbw.olat.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class OlatMailService {

    @Autowired
    private JavaMailSender mailSender;


    public void send(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("olat.2.mailer@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);

    }
}
