package com.co4gsl.martianrobots;

import com.co4gsl.martianrobots.controllers.RobotController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MartianRobotsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MartianRobotsIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private RobotController controller;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

	@Test
	public void givenResource_whenLandingPage_thenExpectedResponse() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.GET, entity, String.class);
		assertEquals("Welcome to martian-robots Application!!", response.getBody());
	}

	@Test
	public void givenResourceCommandData_whenRobotsLands_thenExpectedResponse() throws Exception {
		StringBuilder expectedResponse = new StringBuilder();
		expectedResponse.append("Sample Input\n5 3\n1 1 E\nRFRFRFRF\n3 2 N\nFRRFLLFFRRFLL\n0 3 W\nLLFFFLFLFL\n0 3 W\nLLFFFLLFL\n");
		expectedResponse.append("\nOutput\n1 1 E\n3 3 N LOST\n3 3 N LOST\n2 3 S\n");

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/land-robots"),
				HttpMethod.GET, entity, String.class);
		assertEquals(expectedResponse.toString(), response.getBody());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
