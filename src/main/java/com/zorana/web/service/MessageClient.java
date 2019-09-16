/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import com.zorana.web.entity.TwilioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zorana
 */
@Component
public class MessageClient {

    private TwilioClient twilioClient;

    @Autowired
    public MessageClient(TwilioClient twilioClient) {
        this.twilioClient = twilioClient;
    }

    public Message sendSMS(final String to, final String body) {
        final String METHOD = "sendSMS ";
        Message smsMessage = null;

        PhoneNumber toPhoneNumber = new PhoneNumber(to);
        smsMessage = sendMessage(toPhoneNumber, twilioClient.getFromPhoneNumber(), body);
        System.out.println("salje poruku");
        return smsMessage;
    }

    private Message sendMessage(final PhoneNumber to, final PhoneNumber from, final String body) {
        return new MessageCreator(to, from, body)
                .create(this.twilioClient.getTwilioRestClient());
    }
}
