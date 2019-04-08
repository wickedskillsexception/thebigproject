package com.siit;

import com.siit.thebigproject.exceptions.ValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThebigprojectApplication {

    public static void main(String[] args) throws ValidationException {

        SpringApplication.run(ThebigprojectApplication.class, args);

    }
}


