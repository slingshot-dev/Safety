package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.Person;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RestControllerPersonEmailTests {
    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

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
/*    @Disabled*/
    public void ReturnCommunityEmailMessage() throws Exception {
        Person persontest = new Person();
        List<Person> personTestList = new ArrayList<>();
        persontest.setFirstName("cyrille");
        persontest.setLastName("guillet");
        persontest.setAddress("7 rue de talhouet");
        persontest.setZip(Long.valueOf("91130"));
        persontest.setCity("Ris-Orangis");
        persontest.setEmail("toto@outlook.fr");
        persontest.setPhone("0123456789");
        personTestList.add(persontest);

        when(personDAO.getAll()).thenReturn(personTestList);
        this.mockMvc.perform(get("/communityEmail?city=Ris-Orangis"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("toto@outlook.fr")));
    }

    @Test
    public void BadUrlRequest() throws Exception {
          this.mockMvc.perform(get("/communityEmail/test"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().is4xxClientError());
    }
}




