package api.endpoints;
import org.testng.annotations.Test;



import api.payload.User;
import io.restassured.response.Response;

import static  io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;

import java.util.ResourceBundle;


//Created to perform CRUP operations on user API
public class userEndpoints2 {
	
	//Method to get URLs from Properties file
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");//Load properties file
		return routes;		
				
	}
	
	
	public static Response createUser(User payload){
		String post_url=getURL().getString("post_url");
	Response response=	given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.post(post_url);
	return response;
		
	}
	
	public static Response readUser(String  userName){
		
		String get_url=getURL().getString("get_url");
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
			    .pathParam("username", userName)
			.when()
				.get(get_url);
		
		return response;
			
		}
	
	public static Response updateUser(User payload,String userName) {
		String update_url=getURL().getString("update_url");
				Response response=		given()
							.contentType("application/json")
							.accept("application.json")
							.pathParam("username", userName)
							.body(payload)
							
						.when()
							.put(update_url);
				return response;
						
						
	}
	
	public static Response deleteUser(String userName) {
		String delete_url=getURL().getString("delete_url");
	Response response=	given()
			.pathParam("username", userName)
		
		.when()
			.delete(delete_url);
	return response;
	}
}
