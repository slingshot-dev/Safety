package com.example.SafetyAlerts.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
public class RestControllerPersonsInfo {

/*    @Autowired
    private GetPersonInfo getPersonInfo;*/

    @GetMapping("/personinfo")
    @ResponseBody
    public String getPersonInfo(@RequestParam(defaultValue = "test") String firstname, @RequestParam(defaultValue = "test2") String lastname ) {
    return firstname;
    }

}

