package com.bcc.municipalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MunicipalapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MunicipalapiApplication.class, args);
    }

}