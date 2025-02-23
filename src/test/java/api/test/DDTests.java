package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String userName, String fname, String lname, String useremail, String password, String ph)
	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(password);
		userPayload.setPhone(ph);
		
		Response response = UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String UserName)
	{
		Response response = UserEndpoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
