package org.ryyaan2004.chat.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

	@GET
	public Response getAllUsers() {

		// Gson gsonBuilder = new
		// GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		// JsonElement jsonElement = gsonBuilder.toJsonTree(
		// CobraChat.getAllUsers() );
		// JsonObject jsonObject = new JsonObject();
		// jsonObject.add("items", jsonElement);
		// String json = gsonBuilder.toJson(jsonObject);

//		return Response.ok(json).build();
		String json = "{'employees':[{'firstName':'John', 'lastName':'Doe'}, {'firstName':'Anna', 'lastName':'Smith'},{'firstName':'Peter', 'lastName':'Jones'}]}";
		return Response.ok(json).build();
	}
}
