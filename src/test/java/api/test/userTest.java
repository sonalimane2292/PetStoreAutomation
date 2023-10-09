package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTest {
	
	Faker faker;
	User userpayload;
	
	public Logger logger;
	@BeforeClass
	public void setUpData() {
		
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUserName(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		//logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		Response response=userEndpoints.createUser(userpayload);
		response.then().log().all();
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		Response response = userEndpoints.readUser(this.userpayload.getUserName());
		response.then().log().all();
		response.statusCode();
		
	}
	
	@Test(priority = 3)
	public void testUpdateUserName() {
		//update data using payload
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		
		Response response=userEndpoints.updateUser(userpayload, this.userpayload.getUserName());
		response.then().log().all();
		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		Response response=userEndpoints.deleteUser(this.userpayload.getUserName());
		response.then().statusCode(200);
	}

}
