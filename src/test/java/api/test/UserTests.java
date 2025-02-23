package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setup()
	{
		userPayload=new User();
		faker=new Faker();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("********** Creating user *************");
		Response response = UserEndpoints2.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*********** user is created *************");
	}
	
	@Test(priority = 2)
	public void testGetUser()
	{
		logger.info("*********** Displaying user details *************");
		Response response = UserEndpoints2.getUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********** user is displayed *************");
	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		logger.info("************ updating user  ****************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************** Successfully updated user data *****************");
		// After update the response
		
		Response responseAfterUpdate=UserEndpoints.getUser(this.userPayload.getUsername());
		 
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		logger.info("********** deleting user **********");
		Response response = UserEndpoints2.deleteUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********** user got deleted ***********");
	}
}
