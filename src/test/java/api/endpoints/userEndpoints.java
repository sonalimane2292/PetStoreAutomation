package api.endpoints;
import org.testng.annotations.Test;
import api.payload.User;
import io.restassured.response.Response;

import static  io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;


//Created to perform CRUP operations on user API
public class userEndpoints {
	
	
	public static Response createUser(User payload){
		
	Response response=	given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.post(Routes.post_url);
	return response;
		
	}
	
	public static Response readUser(String  userName){
		
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
			    .pathParam("username", userName)
			.when()
				.get(Routes.get_url);
		
		return response;
			
		}
	
	public static Response updateUser(User payload,String userName) {
		
				Response response=		given()
							.contentType("application/json")
							.accept("application.json")
							.pathParam("username", userName)
							.body(payload)
							
						.when()
							.put(Routes.update_url);
				return response;
						
						
	}
	
	public static Response deleteUser(String userName) {
		
	Response response=	given()
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
	return response;
	}
}
