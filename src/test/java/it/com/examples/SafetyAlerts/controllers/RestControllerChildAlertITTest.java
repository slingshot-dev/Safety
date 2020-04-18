package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ResourceBundle;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SafetyAlertsApplication.class)
@AutoConfigureMockMvc
public class RestControllerChildAlertITTest {

    @Autowired
    MockMvc mockMvc;

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

    @Test
    public void ReturnFirestationMessage() throws Exception {
        this.mockMvc.perform(get("/childAlert?address=" + bundle.getString("param1")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Kendrik")))
                .andExpect(jsonPath("$.[0].lastName").value("Stelzer"))
                .andExpect(jsonPath("$.[0].personParFoyers.[0].firstName").value("Brian"));
    }

    @Test
    public void ReturnFirestationCompleteJson() throws Exception {
        this.mockMvc.perform(get("/childAlert?address=" + bundle.getString("param1")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(bundle.getString("child")));
    }



}
