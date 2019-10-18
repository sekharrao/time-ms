package com.example.time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = TimeApplication.class
		)
@AutoConfigureMockMvc
public class TimeApplicationTestsIT {
	
	private static final String AUTH_SECRET = "S)N'/vXP/U?7@.\"]";
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void callingTimeEndpoint_withinOneSec_shouldHaveSameTime() throws Exception {
		
		String time_response1 = getTimeResponse();
		
		Thread.sleep(1000);
		
		String time_response2 = getTimeResponse();
		
		assertThat(time_response1.contentEquals(time_response2));
				
	}
	
	@Test
	public void callingTimeEndpoint_after5Sec_shouldHaveDifferentTime() throws Exception {
		
		String time_response1 = getTimeResponse();
		
		Thread.sleep(6000);
		
		String time_response2 = getTimeResponse();
		
		assertThat(!time_response1.contentEquals(time_response2));
				
	}

	private String getTimeResponse() throws UnsupportedEncodingException, Exception {
		return mvc.perform(get("/time")
				.header("Authorization", AUTH_SECRET))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse().getContentAsString();
	}

}
