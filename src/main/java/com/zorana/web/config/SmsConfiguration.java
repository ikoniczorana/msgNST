/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.config;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;
import com.zorana.web.entity.TwilioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zorana.web.properties.TwilioProps;

/**
 *
 * @author Zorana
 */
@Configuration
public class SmsConfiguration {
    
    @Bean
    @Autowired
    public TwilioClient createTwilioRestClient(TwilioProps twilioProps){
        TwilioRestClient twilioRestClient =  new TwilioRestClient.Builder(twilioProps.getAccountSid(),
                twilioProps.getAuthToken()).build();

        PhoneNumber fromPhoneNumber = new PhoneNumber(twilioProps.getFromNumber());
        //PhoneNumber whatsappPhoneNumber = new PhoneNumber(TwilioConsts.WHATSAPP_PREFIX + twilioProps.getWhatsappNumber());

        TwilioClient twilioClient = new TwilioClient(twilioRestClient, fromPhoneNumber);
        return twilioClient;
    }
    
   
}
