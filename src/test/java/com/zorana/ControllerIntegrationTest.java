/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zorana.web.config.WebMvcConfig;
import com.zorana.web.entity.Email;
import java.util.Date;
import javax.mail.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Zorana
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebMvcConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
     @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    
     @Test
    public void testPreviewEmails() throws Exception {
        mockMvc.perform(get("/viewemails"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewemails"));
    }
    
    @Test
    public void testPreviewOneEmail() throws Exception {
        mockMvc.perform(post("/messageProcess")
                .content(asJsonString(new Email(null, "ikonic.z@gmail.com", "test@gmail.com", "ikonic.z@gmail.com","test","test",new Date(),null)))
                .contentType(MediaType.APPLICATION_JSON));
    }
    
     public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     
    @Test
    public void testSave() throws Exception {
        Email e = new Email(null, "ikonic.z@gmail.com", "test@gmail.com", "ikonic.z@gmail.com","test","test",new Date(),null);
                
        mockMvc.perform(post("/emailProcess")
                .content(asJsonString(e))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

    }
    
    @Test
    public void testDeleteEmail() throws Exception {
        mockMvc.perform(post("/emailProcess")
                .content(asJsonString(new Email(1)))
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/delete/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }


}
