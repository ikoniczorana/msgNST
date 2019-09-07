/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.controller;

import com.zorana.web.entity.Email;
import com.zorana.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.zorana.web.entity.User;
import com.zorana.web.service.EmailService;
import java.io.File;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zorana
 */
@Controller
public class UserController {

    @Autowired
    JavaMailSender javaMailSender;

    private UserService userService;
    private EmailService emailService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    User korisnik = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ModelAndView email(HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("email");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam("name") String name, @RequestParam("pass") String pass, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(name) && user.getPassword().equalsIgnoreCase(pass)) {

                korisnik = user;
            }
        }
        if (korisnik == null) {
            mv.setViewName("login");

            mv.addObject("message", "Username or Password is wrong!!");
        } else {
            mv.setViewName("email");
            mv.addObject("username", korisnik.getUsername());
            mv.addObject("emailaddress", korisnik.getEmailaddress());

            System.out.println(korisnik.getEmailaddress());
        }

        return mv;
    }

    @RequestMapping(value = "/emailProcess", method = RequestMethod.POST)
    public ModelAndView emailProcess(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("cc") String cc, @RequestParam("subject") String subject, @RequestParam("body") String body, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        Email email = new Email();
        email.setFromUser(korisnik.getEmailaddress());
        email.setToUser(to);
        email.setSubject(subject);
        email.setBody(body);
        email.setCc(cc);
        email.setUserid(korisnik);
        emailService.saveEmail(email);

        System.out.println("Korisnik id" + korisnik.getEmailaddress());

        System.out.println("ime fajla" + file.getName());
        if (!file.isEmpty()) {

            emailService.sendEmailWithAttachment(email, file);
//            try {
//                MimeMessage msg = javaMailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//                Multipart multipart = new MimeMultipart();
//                MimeBodyPart attachPart = new MimeBodyPart();
//                DataSource ds;
//                try {
//                    ds = new ByteArrayDataSource(file.getBytes(), file.getContentType());
//                    attachPart.setDataHandler(new DataHandler(ds));
//                } catch (IOException ex) {
//                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                attachPart.setFileName(file.getOriginalFilename());
//                attachPart.setDisposition(Part.ATTACHMENT);
//
//                multipart.addBodyPart(attachPart);
//                msg.setContent(multipart);
//                helper.setTo(to);
//                helper.setText(body);
//                helper.setSubject(subject);
//                helper.setFrom("ikonic.zorana@gmail.com");
//                if (!cc.isEmpty()) {
//                    helper.setCc(cc);
//                }
//                System.out.println("ove je");
//                javaMailSender.send(msg);
//            } catch (MessagingException ex) {
//                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
//            SimpleMailMessage simplemailmsg = new SimpleMailMessage();
//            simplemailmsg.setSubject(subject);
//            simplemailmsg.setText(body);
//
//            simplemailmsg.setTo(to);
//            if (!cc.isEmpty()) {
//                simplemailmsg.setCc(cc);
//            }
//            javaMailSender.send(simplemailmsg);
//
//            System.out.println("obicna por");
            emailService.sendEmail(email);
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("email");

        return mav;
    }

   
    @RequestMapping(value = "/viewemails", method = RequestMethod.GET)
    public ModelAndView viewEmails(HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView();
        List<Email> emailList = emailService.getAllEmails();
        for (Email email : emailList) {
            System.out.println(email.getSubject());
        }
        mav.addObject("emailList", emailList);
        mav.setViewName("viewemails");
        return mav;
    }

}
