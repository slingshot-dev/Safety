package it.com.examples.SafetyAlerts.controllers;

import com.example.SafetyAlerts.SafetyAlertsApplication;
import com.example.SafetyAlerts.controllers.RestControllerPersonEmail;
import com.example.SafetyAlerts.dao.impl.GetCommunityEmailInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    @Autowired
    private GetCommunityEmailInfo getCommunityEmailInfo;
    @Autowired
    private RestControllerPersonEmail restControllerPersonEmail;


    @Test
    public void ReturnCommunityEmailMessage() throws Exception {
        this.mockMvc.perform(get("/communityEmail?city=Culver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("John")))
                .andExpect(jsonPath("$.emailUrls.[0].email").value("jaboyd@email.com"));
    }

    @Test
    public void ReturnCommunityEmailCompleteJson() throws Exception {
        this.mockMvc.perform(get("/communityEmail?city=Culver")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"emailUrls\":[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"email\":\"drk@email.com\"},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"email\":\"tenz@email.com\"},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jonanathan\",\"lastName\":\"Marrack\",\"email\":\"drk@email.com\"},{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"email\":\"tenz@email.com\"},{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Foster\",\"lastName\":\"Shepard\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"email\":\"tcoop@ymail.com\"},{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"email\":\"lily@email.com\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"email\":\"soph@email.com\"},{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\",\"email\":\"ward@email.com\"},{\"firstName\":\"Zach\",\"lastName\":\"Zemicks\",\"email\":\"zarc@email.com\"},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"email\":\"reg@email.com\"},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"email\":\"jpeter@email.com\"},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"email\":\"jpeter@email.com\"},{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"email\":\"aly@imail.com\"},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"email\":\"bstel@email.com\"},{\"firstName\":\"Shawna\",\"lastName\":\"Stelzer\",\"email\":\"ssanw@email.com\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"email\":\"bstel@email.com\"},{\"firstName\":\"Clive\",\"lastName\":\"Ferguson\",\"email\":\"clivfd@ymail.com\"},{\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"email\":\"gramps@email.com\"}]}"));
    }

}
