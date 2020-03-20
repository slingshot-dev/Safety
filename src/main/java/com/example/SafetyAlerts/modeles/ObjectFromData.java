package com.example.SafetyAlerts.modeles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ObjectFromData {

    public void ObjectsFromData(){
    }


    public void ObjectsFromDataPersons() {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray personsList = (JSONArray) jsonObject.get("persons");

            System.out.println(personsList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void ObjectsFromDataFirestations(String[] firestations){

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("firestations");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ObjectsFromDataMedicalrecords(String[] medicalrecords){

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("medicalrecords");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

