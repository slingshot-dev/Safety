package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SafetyAlertsApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RestControllerFirestationPPDITTests {

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
    public void PostFirestation() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/firestation/post/?address=10 rue de Paris&station=6"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void PutFirestation() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(put("/firestation/put/?address=10 rue de Paris&station=6"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


}
