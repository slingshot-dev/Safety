package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.services.GetCommunityEmailInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerPersonEmailTests {

    RestControllerPersonEmail restControllerPersonEmail = new RestControllerPersonEmail();

    @Autowired
    MockMvc mockMvc;



    @Test
        public void contextLoads() {
        assertThat(restControllerPersonEmail).isNotNull();
        }

    @Test
    public void ReturnCommunityEmailMessage() throws Exception {
        this.mockMvc.perform(get("/communityEmail?city=Culver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void BadUrlRequest() throws Exception {
        this.mockMvc.perform(get("/communityEmail/test"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void PostFirestation() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/firestation/post/?address=10 rue de Paris&station=7"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}




