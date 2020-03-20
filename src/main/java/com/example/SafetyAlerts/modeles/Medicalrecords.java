package com.example.SafetyAlerts.modeles;


        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;

        import java.io.FileReader;

public class Medicalrecords {

    public void medicalrecords() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray medicList = (JSONArray) jsonObject.get("medicalrecords");

            System.out.println(medicList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
