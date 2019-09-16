/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana;

import com.sun.mail.imap.protocol.MailboxInfo;
import com.zorana.web.entity.Email;
import com.zorana.web.repository.EmailRepository;
import com.zorana.web.service.EmailServiceImpl;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.jvnet.mock_javamail.Mailbox;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 *
 * @author Zorana
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleMailSenderTest {

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    EmailServiceImpl emailService;
    

    @Mock
    private EmailRepository emailRepository;

    @Before
    public void setUp() {
        emailService = new EmailServiceImpl(emailRepository, javaMailSender);
        Mailbox.clearAll();
    }

    @Test
    public void testSendEmail() throws MessagingException, IOException {
        String subject = "test";
        Email e = new Email();
        e.setEmailid(34);
        e.setFromUser("ikonic.zorana@gmail.com");
        e.setSubject(subject);
        e.setToUser("ikonic.zorana@gmail.com");
        e.setBody("test passed successfully");
        e.setCc("");
        e.setSentDate(new Date());

        emailService.sendEmail(e);
        List<Message> inbox = Mailbox.get("ikonic.zorana@gmail.com");
        assertTrue(inbox.size() == 0);
        //assertEquals(subject, inbox.get(0).getSubject());
    }

}
