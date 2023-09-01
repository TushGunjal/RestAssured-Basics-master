package requests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetPeopleTest extends CommonTest {

	@Test
	public void GetPeople(){

		Response response = given()
				.spec(reqSpec)
				.when()
				.get(BASE_URL + PEOPLE + "/3")
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();

        System.out.println("People Call -3 Response \n"+response.prettyPrint());
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());

	}

	@Test
	public void VerifyPeople3() throws ParseException, java.text.ParseException {
		String film = "";
		Response response = given()
				.spec(reqSpec)
				.when()
				.get(BASE_URL + PEOPLE + "/3")
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();

		assertThat("R2-D2").isEqualTo(json.getString("name"));
		assertThat("white, blue").isEqualTo(json.getString("skin_color"));
		int s = json.getInt("films.size()");
		System.out.println("List of String: " + s);
		for(int i = 0; i < s; i++) {
			film = film+readfilms(json.getString("films["+i+"]"))+","	;
			}
		film = film.substring(0, film.length()-1);
		assertThat("A New Hope,The Empire Strikes Back,Return of the Jedi,The Phantom Menace,Attack of the Clones,Revenge of the Sith").isEqualTo(film);
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());

	}


	public String readfilms(String url) {
		Response response = given()
				.spec(reqSpec)
				.when()
				.get(url)
				.then()
				.statusCode(SC_OK)
				.extract()
				.response();

		JsonPath json = response.jsonPath();
		return(json.getString("title"));
	}

}
