package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SafetyAlertsApplication.class)
@AutoConfigureMockMvc
public class RestControllerFloodITTest {

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
    public void ReturnFoyerFromStation() throws Exception {
        this.mockMvc.perform(get("/flood?station=4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Lily")))
                .andExpect(jsonPath("$.[0].firestationNumber").value("4"))
                .andExpect(jsonPath("$.[0].lastName").value("Cooper"));
    }

    @Test
    public void ReturnFoyerCompleteJson() throws Exception {
        this.mockMvc.perform(get("/flood?station=4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(bundle.getString("flood")));
    }



}
