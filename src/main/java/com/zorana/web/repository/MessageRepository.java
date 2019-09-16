/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.repository;

import com.zorana.web.entity.Email;
import com.zorana.web.entity.Sms;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Zorana
 */
public interface MessageRepository extends CrudRepository<Sms, Integer>{
    
}
