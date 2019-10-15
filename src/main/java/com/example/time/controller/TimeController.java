package com.example.time.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Schedules;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.time.model.CustomResponse;

@RestController
@CrossOrigin
public class TimeController {

	@GetMapping("/time")
	@Schedules()
    public CustomResponse greeting() {
         LocalTime time = LocalTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		 return new CustomResponse(time.format(formatter));
    }

}