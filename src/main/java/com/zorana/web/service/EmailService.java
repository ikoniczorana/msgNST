/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.zorana.web.entity.Email;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Zorana
 */
public interface EmailService {

    public List<Email> getAllEmails();

    public boolean saveEmail(Email email);

    public void sendEmail(Email email);
    
    public void sendEmailWithAttachment(Email email, MultipartFile file) throws Exception;
    
    public boolean deleteEmailById(int id);

}
