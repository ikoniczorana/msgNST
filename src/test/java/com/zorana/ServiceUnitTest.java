/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana;

import com.zorana.web.entity.Email;
import com.zorana.web.entity.User;
import com.zorana.web.service.EmailService;
import com.zorana.web.service.EmailServiceImpl;
import com.zorana.web.service.MessageServiceImpl;
import com.zorana.web.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Zorana
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceUnitTest {

    @Mock
    EmailServiceImpl emailService;

    @Mock
    MessageServiceImpl messageService;

    @Mock
    UserServiceImpl userService;

    @Test
    public void getAllEmails() {
        List<Email> emails = new ArrayList<Email>();
        Email e1 = new Email(1);
        Email e2 = new Email(2);
        Email e3 = new Email(3);

        emails.add(e1);
        emails.add(e2);
        emails.add(e3);

        Mockito.when(emailService.getAllEmails()).thenReturn(emails);

        List<Email> emailList = emailService.getAllEmails();
        Assert.assertEquals(3, emailList.size());
        Mockito.verify(emailService, Mockito.times(1)).getAllEmails();

        System.out.println("List of emails is successfully returned!");

    }

    @Test
    public void saveUserTest() {
        User u = new User(3);
        userService.saveUser(u);
        Mockito.verify(userService, Mockito.times(1)).saveUser(u);

        System.out.println("User is successfully saved!");
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<User>();
        User e1 = new User(1);
        User e2 = new User(2);
        User e3 = new User(3);

        users.add(e1);
        users.add(e2);
        users.add(e3);

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        List<User> userList = userService.getAllUsers();
        Assert.assertEquals(3, userList.size());
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();

        System.out.println("List of users is successfully returned!");
    }
    
    @Test
    public void saveEmailTest() {
        Email e = new Email();
        e.setEmailid(13);
        e.setFromUser("ikonic.zorana@gmail.com");
        e.setSentDate(new Date());
        e.setUserid(new User(1));
        emailService.saveEmail(e);
        Mockito.verify(emailService, Mockito.times(1)).saveEmail(e);

        System.out.println("Email is successfully saved!");
    }
    
    
}
