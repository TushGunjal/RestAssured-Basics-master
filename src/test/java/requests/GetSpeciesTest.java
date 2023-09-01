package requests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetSpeciesTest extends CommonTest {

	@Test
	public void GetStarShips(){

		Response response = given()
				.spec(reqSpec)
				.when()
				.get(BASE_URL + SPECIES + "/3")
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();

        System.out.println("STARSHIPS -9 Response\n"+response.prettyPrint());
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());

	}

	@Test
	public void VerifyStarships9() throws ParseException, java.text.ParseException {

		Response response = given()
				.spec(reqSpec)
				.when()
				.get(BASE_URL + SPECIES + "/3")
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();

		assertThat("Wookie").isEqualTo(json.getString("name"));
		assertThat("mammal").isEqualTo(json.getString("classification"));
		assertThat("Kashyyyk").isEqualTo(readPlanet(json.getString("homeworld")));
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());

	}

	public String readPlanet(String url) {
		Response response = given()
				.spec(reqSpec)
				.when()
				.get(url)
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();
		return(json.getString("name"));
	}

}
