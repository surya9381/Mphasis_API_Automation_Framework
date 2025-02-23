package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

	public static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User userPayload)
	{
		String post_url = getUrl().getString("post_url");
		Response response = given()
		     .accept(ContentType.JSON)
		     .contentType(ContentType.JSON)
		     .body(userPayload)
		
		.when()
		     .post(post_url);
		return response;
	}
	
	public static Response getUser(String userName)
	{
		String get_url = getUrl().getString("get_url");
		Response response = given()
		     .pathParam("username", userName)
		
		.when()
		     .get(get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User userPayload)
	{
		String update_url = getUrl().getString("update_url");
		Response response = given()
		     .accept(ContentType.JSON)
		     .contentType(ContentType.JSON)
		     .body(userPayload)
		     .pathParam("username", userName)
		
		.when()
		     .put(update_url);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getUrl().getString("delete_url");
		Response response = given()
		     .pathParam("username", userName)
		
		.when()
		     .delete(delete_url);
		
		return response;
	}
}
