/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zorana
 */
@Component
@ConfigurationProperties("twilio")
public class TwilioProps {

    private String accountSid;
    private String authToken;
    private String fromNumber;
    private String whatsappNumber;
    private String twimlBinUrl;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getTwimlBinUrl() {
        return twimlBinUrl;
    }

    public void setTwimlBinUrl(String twimlBinUrl) {
        this.twimlBinUrl = twimlBinUrl;
    }
}
