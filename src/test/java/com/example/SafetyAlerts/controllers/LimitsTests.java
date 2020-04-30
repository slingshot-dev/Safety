package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.impl.PersonDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class LimitsTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RestControllerPersonEmail restControllerPersonEmail;

    @MockBean
    PersonDAO personDAO;


    @Test
    public void contextLoads() {
        assertThat(restControllerPersonEmail).isNotNull();
        }

    @Test
    public void BadUrlRequest() throws Exception {
        // act & Assert
        this.mockMvc.perform(get("/communityEmail/test"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().is4xxClientError());
    }
}




