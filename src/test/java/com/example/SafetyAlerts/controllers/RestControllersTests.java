package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RestControllersTests {

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");
    ObjectFromData objectFromData = new ObjectFromData();

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RestControllerFirestationPPD restControllerFirestationPPD;

    @MockBean
    SafetyAlertsMapper safetyAlertsMapper;

    @BeforeEach
    public void init() {

        Firestation firestation = new Firestation();
        List<Firestation> firestationTestList = new ArrayList<>();
        firestation.setAddress("7 rue de talhouet");
        firestation.setStation("1");
        firestationTestList.add(firestation);
        Firestation firestation2 = new Firestation();
        firestation2.setAddress("15 rue de Bordeaux");
        firestation2.setStation("2");
        firestationTestList.add(firestation2);

        Person person = new Person();
        List<Person> personTestList = new ArrayList<>();
        person.setFirstName("cyrille");
        person.setLastName("guillet");
        person.setAddress("7 rue de talhouet");
        person.setZip(Long.valueOf("91130"));
        person.setCity("Ris-Orangis");
        person.setEmail("toto@outlook.fr");
        person.setPhone("0123456789");
        personTestList.add(person);
        Person person2 = new Person();
        person2.setFirstName("david");
        person2.setLastName("dupont");
        person2.setAddress("15 rue de Bordeaux");
        person2.setZip(Long.valueOf("91130"));
        person2.setCity("Ris-Orangis");
        person2.setEmail("daviddupont@outlook.fr");
        person2.setPhone("0123456666");
        personTestList.add(person2);

        MedicalRecord medicalRecord = new MedicalRecord();

        List<MedicalRecord> medicTestList = new ArrayList<>();
        medicalRecord.setFirstName("cyrille");
        medicalRecord.setLastName("guillet");
        medicalRecord.setBirthdate("03/28/1973");
        List<String> medics = new ArrayList<>();
        medics.add("Doliprane");
        medics.add("Diprosome");
        medicalRecord.setMedications(medics);
        List<String> allerg = new ArrayList<>();
        allerg.add("Urticaire");
        allerg.add("Migraine");
        medicalRecord.setAllergies(allerg);
        medicTestList.add(medicalRecord);
        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setFirstName("david");
        medicalRecord2.setLastName("dupont");
        medicalRecord2.setBirthdate("05/15/2004");
        List<String> medics2 = new ArrayList<>();
        medics2.add("Efferalgan");
        medics2.add("Levothirox");
        medicalRecord2.setMedications(medics2);
        List<String> allerg2 = new ArrayList<>();
        allerg2.add("Eczema");
        allerg2.add("Asthme");
        medicalRecord2.setAllergies(allerg2);
        medicTestList.add(medicalRecord2);

        objectFromData.setFirestations(firestationTestList);
        objectFromData.setPersons(personTestList);
        objectFromData.setMedicalrecords(medicTestList);
    }


    @Test
    public void GetFirestation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(get("/firestation/?stationNumber=1"))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("7 rue de talhouet", objectFromData.getFirestations().get(0).getAddress());
    }


    @Test
    public void PostFirestation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(post("/firestation/post/?address=10 Rue de Paris&station=3"))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("10 Rue de Paris", objectFromData.getFirestations().get(2).getAddress());
    }


    @Test
    public void PutFirestation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(put("/firestation/put/?address=7 rue de talhouet&station=10"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("10", objectFromData.getFirestations().get(0).getStation());
    }

    @Test
    public void DeleteFirestation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(delete("/firestation/delete/?address=7 Rue de Talhouet&station=1"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("15 rue de Bordeaux", objectFromData.getFirestations().get(0).getAddress());
        assertEquals(1, objectFromData.getFirestations().size());
    }

    @Test
    public void GetPerson() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(get("/personinfo?firstname=cyrille&lastname=guillet"))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("cyrille", objectFromData.getPersons().get(0).getFirstName());
    }

    @Test
    public void PostPerson() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(post("/person/post/?firstName=fabienne&lastName=guillet&address=7 rue de Talhouet&city=ris-orangis&zip=91130&phone=0123455555&email=fabienne@outlook.fr&birthdate=02/17/1971"))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("fabienne", objectFromData.getPersons().get(2).getFirstName());
    }

    @Test
    public void PutPerson() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(put("/person/put/?firstName=cyrille&lastName=guillet&address=7 rue de Talhouet3&city=ris-orangis3&zip=911303&phone=1233&email=slingshot@outlook.fr3"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("7 rue de Talhouet3", objectFromData.getPersons().get(0).getAddress());


    }

    @Test
    public void DeletePerson() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(delete("/person/delete/?firstName=cyrille&lastName=guillet"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("15 rue de Bordeaux", objectFromData.getPersons().get(0).getAddress());
        assertEquals(1, objectFromData.getPersons().size());
    }

    @Test
    public void PostMedic() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(post("/medicalRecord/post/?firstName=fabienne&lastName=guillet&birthdate=02/17/1971&medications=Doliprane : 4gr&allergies=Asthme"))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("Doliprane : 4gr", objectFromData.getMedicalrecords().get(2).getMedications().get(0));
    }

    @Test
    public void PutMedic() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(put("/medicalRecord/put?firstName=cyrille&lastName=guillet&birthdate=03/28/1973&medications=thradox:700mg&allergies=illisoxian 2Gr"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("thradox:700mg", objectFromData.getMedicalrecords().get(0).getMedications().get(0));


    }

    @Test
    public void DeleteMedic() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act
        MvcResult mvcResult = this.mockMvc.perform(delete("/medicalRecord/delete/?firstName=cyrille&lastName=guillet"))
                .andDo(print()).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Assert
        assertEquals(200, status);
        assertEquals("david", objectFromData.getMedicalrecords().get(0).getFirstName());
        assertEquals(2, objectFromData.getPersons().size());
    }

    @Test
    public void GetPersonFromName() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/personinfo?" + bundle.getString("param5")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("cyrille")))
                .andExpect(jsonPath("$.[0].lastName").value("guillet"));
    }

    @Test
    public void GetChildFromAddress() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/childAlert?address=" + bundle.getString("param6")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("david")))
                .andExpect(jsonPath("$.[0].enfantFoyer.[0].lastName").value("dupont"));
    }

    @Test
    public void GetFirestationFromAddress() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/fire?address=15 rue de Bordeaux"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("david")))
                .andExpect(jsonPath("$.[0].firestationNumber").value("2"));
    }

    @Test
    public void ReturnPersonFromStation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/firestation?stationNumber=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("cyrille")))
                .andExpect(jsonPath("$.persons.[0].address").value("7 rue de talhouet"));
    }

    @Test
    public void GetAddressFromStation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/flood?station=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("cyrille")))
                .andExpect(jsonPath("$.[0].firestationNumber").value("1"))
                .andExpect(jsonPath("$.[0].lastName").value("guillet"));
    }

    @Test
    public void GetEmailFromCity() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/communityEmail?city=Ris-Orangis"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("cyrille")))
                .andExpect(jsonPath("$.[0].email").value("toto@outlook.fr"));
    }

    @Test
    public void GetPhoneFromStation() throws Exception {
        // Arrange
        when(safetyAlertsMapper.read()).thenReturn(objectFromData);

        // Act & Assert
        this.mockMvc.perform(get("/phoneAlert?" + bundle.getString("param3")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("cyrille")))
                .andExpect(jsonPath("$.[0].phone").value("0123456789"));
    }
}