package com.example.time.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.time.model.CustomResponse;

@RestController
@CrossOrigin
public class TimeController {

	
	private CustomResponse customResponse = new CustomResponse();
	
	@GetMapping("/time")
    public CustomResponse greeting() {
		 return customResponse;
    }
	
	@Scheduled(fixedRate = 5000)
	private void setTime() {
		LocalTime time = LocalTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		 customResponse.setTime(time.format(formatter));	
	}

}