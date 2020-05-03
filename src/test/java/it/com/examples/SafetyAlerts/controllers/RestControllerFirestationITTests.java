package it.com.examples.SafetyAlerts.controllers;


import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.jupiter.api.AfterAll;
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
public class RestControllerFirestationITTests {

    @Autowired
    MockMvc mockMvc;

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

    @BeforeEach
    public void copy() throws IOException {
        Path sourcepath = Paths.get("src/main/resources/data_orig.json");
        Path destinationepath = Paths.get("src/main/resources/data.json");
        Files.copy(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterAll
    public static void copyfinal() throws IOException {
        Path sourcepath = Paths.get("src/main/resources/data_orig.json");
        Path destinationepath = Paths.get("src/main/resources/data.json");
        Files.copy(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void ReturnPersonFromStation() throws Exception {
        this.mockMvc.perform(get("/firestation?stationNumber=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Peter")))
                .andExpect(jsonPath("$.persons.[0].address").value("644 Gershwin Cir"));
    }

    @Test
    public void ReturnPersonFromStationCompleteJson() throws Exception {
        this.mockMvc.perform(get("/firestation?stationNumber=1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(bundle.getString("firestation")));
    }

}
