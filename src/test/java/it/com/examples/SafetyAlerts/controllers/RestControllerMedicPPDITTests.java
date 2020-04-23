package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.File;
import java.io.FileOutputStream;
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
public class RestControllerMedicPPDITTests {

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
    public void PostMedic() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/medicalRecord/post/?firstName=cyrille&lastName=guillet&birthdate=03/28/1973&medications=doliprane : 4gr&allergies=diprosome : 1"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void PutMedic() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(put("/medicalRecord/put?firstName=Reginold&lastName=Walker&birthdate=08/30/1979&medications=thradox:700mg 2&allergies=illisoxian 2"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void DeleteMedic() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/medicalRecord/delete/?firstName=Reginold&lastName=Walker"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}

