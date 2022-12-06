package com.example.cy_rate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cy_rate.Business.Business;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.Review.Review;
import com.example.cy_rate.Review.ReviewRepository;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class CyRateApplicationTests {

	// @Test
	// void contextLoads() {
	// }

	@Autowired
	private BusinessRepository busRepo;
	@Autowired
	private ReviewRepository revRepo;


	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
	}

	@Test
	public void createReviewTest()
	{
		// Review tester = new Review(5, "This is a great place to eat", "Favorite Location");
		Gson gson = new Gson();
		// Used for retrieving results from db, using it post string
		Business testBusiness = busRepo.findById(3);
		Response response = RestAssured.given().
				header("Content-Type", "application/json").
				header("charset","utf-8").
				body("{\"rateVal\": 5, \"reviewTxt\": \"This is a great place to eat\", \"reviewHeader\": \"Favorite Location\"}").
				when().
				post("/review/3/user/7/createReview");
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		// Get most recent entry
		List<Review> rows = revRepo.findByBusiness(testBusiness);
		Review createdReview = rows.get(rows.size() - 1);

		assertEquals("This is a great place to eat", createdReview.getReviewTxt());
		
		// // Parse response body into Review object for assertEquals
		// String responseString = response.getBody().asString();
		// gson.fromJson(responseString, Review.class);

	}
}
