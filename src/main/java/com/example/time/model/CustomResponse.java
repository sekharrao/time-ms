package com.example.time.model;

import lombok.Data;

@Data
public class CustomResponse {

	private final String time;
	public CustomResponse(String time){
		this.time = time;
	}
}
