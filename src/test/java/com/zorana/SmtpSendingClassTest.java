/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana;

import com.github.sleroy.fakesmtp.core.ServerConfiguration;
import com.github.sleroy.junit.mail.server.test.FakeSmtpRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Zorana
 */
public class SmtpSendingClassTest {

    @Rule
    public FakeSmtpRule smtpServer = new FakeSmtpRule(ServerConfiguration.create().port(2525).charset("UTF-8"));

    @Test
    public void testCase1() {
        Assert.assertTrue(smtpServer.isRunning());
        Assert.assertTrue(smtpServer.mailBox().isEmpty());
    }
}
