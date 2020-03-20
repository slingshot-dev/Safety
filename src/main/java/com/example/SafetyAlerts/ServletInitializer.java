package com.example.SafetyAlerts;

import org.springframework.boot.builder.SpringApplicationBuilder;


public class ServletInitializer extends SafetyAlertsApplication {


        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SafetyAlertsApplication.class);

        }
    }

