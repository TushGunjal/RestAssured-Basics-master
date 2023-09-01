package requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonTest {

	protected final String BASE_URL = "https://swapi.dev/api";
	protected final String FILMS = "/films";
	protected final String PEOPLE = "/people";
	protected final String STARSHIPS = "/starships";
	protected final String SPECIES = "/species";
	protected final String PLANETS = "/planets";
	protected final String VEHICLES = "/vehicles";
	protected final Integer SC_OK = HttpStatus.SC_OK;


	public static RequestSpecBuilder reqBuilder;
	public static RequestSpecification reqSpec;

	@BeforeAll
	public static void beforeAll() {

		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addFilter(new AllureRestAssured());
		reqBuilder.setContentType(ContentType.JSON);

		reqSpec = reqBuilder.build();
	}
	

}
