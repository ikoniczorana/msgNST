/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.entity;

import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Zorana
 */
public class TwilioClient {

    private TwilioRestClient twilioRestClient;
    private PhoneNumber fromPhoneNumber;
    private PhoneNumber whatsappPhoneNumber;
    private String twiMLBinUrl;

    public TwilioClient(TwilioRestClient twilioRestClient, PhoneNumber fromPhoneNumber) {
        this.twilioRestClient = twilioRestClient;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public TwilioClient(TwilioRestClient twilioRestClient, PhoneNumber fromPhoneNumber, PhoneNumber whatsappPhoneNumber, String twiMLBinUrl) {
        this.twilioRestClient = twilioRestClient;
        this.fromPhoneNumber = fromPhoneNumber;
        this.whatsappPhoneNumber = whatsappPhoneNumber;
        this.twiMLBinUrl = twiMLBinUrl;
    }
    
    

    public TwilioRestClient getTwilioRestClient() {
        return twilioRestClient;
    }

    public void setTwilioRestClient(TwilioRestClient twilioRestClient) {
        this.twilioRestClient = twilioRestClient;
    }

    public PhoneNumber getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(PhoneNumber fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public PhoneNumber getWhatsappPhoneNumber() {
        return whatsappPhoneNumber;
    }

    public void setWhatsappPhoneNumber(PhoneNumber whatsappPhoneNumber) {
        this.whatsappPhoneNumber = whatsappPhoneNumber;
    }

    public String getTwiMLBinUrl() {
        return twiMLBinUrl;
    }
}
