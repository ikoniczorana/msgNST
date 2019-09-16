/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.responses;

/**
 *
 * @author Zorana
 */
public class MessageSenderResponse {

    private boolean success;
    private String message;
    private int status;

    public MessageSenderResponse(boolean success, String message, int status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MessageSenderResponse{"
                + "success=" + success
                + ", message='" + message + '\''
                + ", status=" + status
                + '}';
    }
}
