/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.zorana.web.config.ConfigUtility;
import com.zorana.web.constants.MessageConsts;
import com.zorana.web.entity.Sms;
import com.zorana.web.repository.MessageRepository;
import com.zorana.web.responses.MessageSenderResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Zorana
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ConfigUtility configUtil;
    private MessageClient messageClient;

    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        super();
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageSenderResponse sendSMS(Sms sms) {
        MessageSenderResponse messageSenderResponse = null;

        try {

            String appKey = configUtil.getProperty("appKey");
            String appSecret = configUtil.getProperty("appSecret");

            URL url = new URL("https://messagingapi.sinch.com/v1/sms/" + sms.getTonumber());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String userCredentials = "application\\" + appKey + ":" + appSecret;
            byte[] encoded = Base64.encodeBase64(userCredentials.getBytes());
            String basicAuth = "Basic " + new String(encoded);
            connection.setRequestProperty("Authorization", basicAuth);

            String postData = "{\"Message\":\"" + sms.getMessage() + "\"}";
            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes());

            StringBuilder response = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();
            os.close();
            messageSenderResponse = buildSuccessResponse();
            System.out.println(response.toString());

        } catch (IOException e) {
            messageSenderResponse = buildResponseFromException(e);
            e.printStackTrace();
        }
        return messageSenderResponse;
    }

    private MessageSenderResponse buildResponseFromException(ApiException apiException) {
        MessageSenderResponse messageSenderResponse = null;

        if (apiException != null && apiException.getStatusCode() != null) {
            messageSenderResponse = new MessageSenderResponse(
                    false,
                    apiException.getMessage(),
                    apiException.getStatusCode());
        } else {
            messageSenderResponse = buildGeneralErrorResponse();
        }
        return messageSenderResponse;
    }

    private MessageSenderResponse buildResponseFromException(Exception exception) {
        MessageSenderResponse messageSenderResponse = null;

        if (exception != null) {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;

                if (apiException != null && apiException.getStatusCode() != null) {
                    messageSenderResponse = new MessageSenderResponse(
                            false,
                            apiException.getMessage(),
                            apiException.getStatusCode());
                }
            }
        }

        return messageSenderResponse == null ? buildGeneralErrorResponse() : messageSenderResponse;
    }

    private MessageSenderResponse buildGeneralErrorResponse() {
        return new MessageSenderResponse(false,
                MessageConsts.GENERAL_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private MessageSenderResponse buildSuccessResponse() {
        return new MessageSenderResponse(true,
                null,
                HttpStatus.CREATED.value());
    }

    @Override
    public boolean saveSMS(Sms sms) {
        try {
            messageRepository.save(sms);
            System.out.println("Saved");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
