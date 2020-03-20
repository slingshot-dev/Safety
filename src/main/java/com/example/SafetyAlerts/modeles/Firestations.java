package com.example.SafetyAlerts.modeles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class Firestations {

    private static final Logger logger = LogManager.getLogger("Firestations");

    public void firestations() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("ata.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray fireList = (JSONArray) jsonObject.get("firestations");

            System.out.println(fireList);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("Error test", e);
        }

    }
}