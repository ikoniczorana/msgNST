/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.config;

import com.viber.bot.ViberSignatureValidator;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;
import javax.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Zorana
 */
public class BotConfiguration {
    
    @Value("${application.viber-bot.auth-token}")
    private String authToken;

    @Value("${application.viber-bot.name}")
    private String name;

    @Nullable
    @Value("${application.viber-bot.avatar:@null}")
    private String avatar;

    
    @Bean
    ViberBot viberBot() {
        return new ViberBot(new BotProfile(name, avatar), authToken);
    }

    @Bean
    ViberSignatureValidator signatureValidator() {
        return new ViberSignatureValidator(authToken);
    }
}
