package com.capstone.inventory_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	static {
		mysqlContainer.start();
	}

	@Test
	void shouldReadInventory() {
		var res = RestAssured.given()
				.when().get("/api/inventory?skuCode=iphone&quantity=10")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response()
				.as(Boolean.class);
		assertTrue(res);
		 var negativeResponse = RestAssured.given()
				 .when().get("/api/inventory?skuCode=iphone&quantity=101")
				 .then()
				 .log().all()
				 .statusCode(200)
				 .extract().response()
				 .as(Boolean.class);
		 assertFalse(negativeResponse);

	}

}
