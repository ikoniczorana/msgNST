/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.zorana.web.controller.UserController;
import com.zorana.web.entity.Email;
import com.zorana.web.repository.EmailRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Zorana
 */
@Service
public class EmailServiceImpl implements EmailService {

    private EmailRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailServiceImpl() {
    }

    @Autowired
    public EmailServiceImpl(EmailRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<Email> getAllEmails() {
        List<Email> list = new ArrayList<Email>();
        repository.findAll().forEach(e -> list.add(e));

        System.out.println(list.get(0));
        return list;

    }

    @Override
    public boolean saveEmail(Email email) {

        try {
            System.out.println(email.getSubject() + email.getFromUser() + email.getToUser() + email.getSubject());
            repository.save(email);
            System.out.println("Saved");
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        String[] tempArray;

        //msg.setTo(email.getToUser());
        if (!email.getCc().isEmpty()) {
            msg.setCc(email.getCc());
        }
        msg.setSubject(email.getSubject());
        msg.setText(email.getBody());

        if (email.getToUser().contains(";")) {
            tempArray = email.getToUser().split(";");
            msg.setTo(tempArray);
        } else if (email.getToUser().contains(",")) {
            tempArray = email.getToUser().split(",");
            msg.setTo(tempArray);
        } else {
            msg.setTo(email.getToUser());
        }
        javaMailSender.send(msg);

    }

    @Override
    public void sendEmailWithAttachment(Email email, MultipartFile file) throws Exception {
        String[] tempArray;
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        Multipart multipart = new MimeMultipart();
        MimeBodyPart attachPart = new MimeBodyPart();
        DataSource ds;
        try {
            ds = new ByteArrayDataSource(file.getBytes(), file.getContentType());
            attachPart.setDataHandler(new DataHandler(ds));
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        attachPart.setFileName(file.getOriginalFilename());
        attachPart.setDisposition(Part.ATTACHMENT);

        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);

        //helper.setTo(email.getToUser());
        helper.setText(email.getBody());
        helper.setSubject(email.getSubject());
        helper.setFrom("ikonic.zorana@gmail.com");
        if (!email.getCc().isEmpty()) {
            helper.setCc(email.getCc());
        }

        if (email.getToUser().contains(";")) {
            tempArray = email.getToUser().split(";");
            helper.setTo(tempArray);
        } else if (email.getToUser().contains(",")) {
            tempArray = email.getToUser().split(",");
            helper.setTo(tempArray);
        } else {
            helper.setTo(email.getToUser());
        }
        javaMailSender.send(msg);

    }

}
