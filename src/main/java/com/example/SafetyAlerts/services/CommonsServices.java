package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonsServices {

    private final IGetAll<Person> personDAO;
    private final IGetAll<Firestation> firestationDAO;
    private final IGetAll<MedicalRecord> medicDA0;
    private String age;


    public CommonsServices(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
        this.medicDA0 = medicDA0;
    }


    public String getFireAdress(String address) {

        List<Firestation> resultFire = firestationDAO.getAll();
        List<Firestation> resultStationNumber = resultFire.stream().filter(fire1 -> (fire1.getAddress().contentEquals(address))).collect(Collectors.toList());

        return resultStationNumber.get(0).getStation();
    }

    public List<Person> getPersonAdress(String param){

        List<Person> resultPersons = personDAO.getAll();
        return resultPersons.stream().filter(person1 -> (person1.getAddress().contentEquals(param))).collect(Collectors.toList());
    }

    public List<MedicalRecord> resultPersonsMedic(String firstname, String lastname){

        List<MedicalRecord> resultMedic = medicDA0.getAll();
        return resultMedic.stream().filter(medic1 -> (medic1.getFirstName().contentEquals(firstname) && medic1.getLastName().contentEquals(lastname))).collect(Collectors.toList());

    }

    public List<MedicalRecord> getBirthdate(String firstName, String lasName) {
        List<MedicalRecord> resultMedic = medicDA0.getAll();
        return resultMedic.stream().filter(medic -> (medic.getFirstName().contentEquals(firstName) && medic.getLastName().contentEquals(lasName))).collect(Collectors.toList());
    }

    public ArrayList<ChildAlertUrl> removeDoublon(ArrayList<ChildAlertUrl> List){
        while (List.size()>1){
            List.remove(1);
        }
        return List;
    }

    public List<Person> getPersonAll(){

        return personDAO.getAll();
    }

    public List<Firestation> getFirestationAll(){

        return firestationDAO.getAll();
    }

    public List<MedicalRecord> getMedicAll(){

        return medicDA0.getAll();
    }

    public void firestationSave(Firestation addFirestation) {

        firestationDAO.save(addFirestation);
    }

    public void firestationUpdate(Firestation updateFirestation, int index) {

        firestationDAO.update(updateFirestation, index);
    }

    public void firestationRemove(List<Firestation> removeFirestation) {

        firestationDAO.delete(removeFirestation);
    }

    public void medicSave(MedicalRecord addMedic){

        medicDA0.save(addMedic);
    }

    public void medicUpdate(MedicalRecord updateMedic,  int index){

        medicDA0.update(updateMedic, index);
    }

    public void medicRemove(List<MedicalRecord> removeMedic){

        medicDA0.delete(removeMedic);
    }

    public void personSave(Person addPerson){

        personDAO.save(addPerson);
    }

    public void personUpdate(Person putPerson, int index){

        personDAO.update(putPerson, index);
    }

    public void personRemove(List<Person> removePerson){

        personDAO.delete(removePerson);

    }

    public PersonInfos getPersonFullInfos(String firstname, String lastname){

        PersonInfos personInfos = new PersonInfos();
        List<MedicalRecord> filteredListMedic = getMedicAll().stream().filter(medic1 -> medic1.getFirstName().contentEquals(firstname) && medic1.getLastName().contentEquals(lastname)).collect(Collectors.toList());
        List<Person> filteredListPerson = getPersonAll().stream().filter(person1 -> person1.getFirstName().contentEquals(firstname) && person1.getLastName().contentEquals(lastname)).collect(Collectors.toList());

        personInfos.setFirstName(filteredListPerson.get(0).getFirstName());
        personInfos.setLastName(filteredListPerson.get(0).getLastName());
        personInfos.setAddress(filteredListPerson.get(0).getAddress());
        personInfos.setCity(filteredListPerson.get(0).getCity());
        personInfos.setEmail(filteredListPerson.get(0).getEmail());
        personInfos.setPhone(filteredListPerson.get(0).getPhone());
        personInfos.setMedics(filteredListMedic.get(0).getMedications());
        personInfos.setAllergies(filteredListMedic.get(0).getAllergies());
        personInfos.setBirthdate(filteredListMedic.get(0).getBirthdate());

        age = GetAge.getAge(filteredListMedic.get(0).getBirthdate());
        personInfos.setAge(age);


        return personInfos;
    }

}
