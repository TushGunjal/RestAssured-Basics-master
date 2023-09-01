package requests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetPlanetsTest extends CommonTest {

	@Test
	public void GetPlanets(){

		Response response = given()
				.spec(reqSpec)
				.when()
				.get(BASE_URL + PLANETS + "/14"+"?format=wookiee")
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();

        //System.out.println("Wookiee Format Planet Response \n"+response.prettyPrint());
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());

	}





}
