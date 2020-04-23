package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = SafetyAlertsApplication.class)
@AutoConfigureMockMvc
public class RestControllerPersonEmailITTests {


    @Autowired
    MockMvc mockMvc;

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

    @BeforeEach
    public void copy() throws IOException {
        Path sourcepath = Paths.get("src/main/resources/data_orig.json");
        Path destinationepath = Paths.get("src/main/resources/data.json");
        Files.copy(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    public void ReturnCommunityEmailMessage() throws Exception {
        this.mockMvc.perform(get("/communityEmail?city=Culver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("John")))
                .andExpect(jsonPath("$.[0].email").value("jaboyd@email.com"));
    }

    @Test
    public void ReturnCommunityEmailCompleteJson() throws Exception {
        this.mockMvc.perform(get("/communityEmail?city=Culver")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(bundle.getString("pall")));
    }

}
