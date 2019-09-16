/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.zorana.web.entity.Sms;
import com.zorana.web.responses.MessageSenderResponse;

/**
 *
 * @author Zorana
 */
public interface MessageService {

    MessageSenderResponse sendSMS(Sms sms);

    boolean saveSMS(Sms sms);
}
