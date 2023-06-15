package jsonpracticle;

import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class JsonpBean {
	
	private String jsonStr;
	private Customer customer;
	
	JsonObject jsonObject;
	
	public JsonpBean() { 
		customer = new Customer();
	}
	
		
	public void buildJson() {
		
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		
		jsonObject = 
			jsonObjectBuilder.
				add("firstName", "Isuru").
				add("lastName", "Virajitha").
				add("email", "isuruw3campus.com").build();
		
		StringWriter stringWriter = new StringWriter();
		
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)){
				jsonWriter.writeObject(jsonObject);
		}
		setJsonStr(stringWriter.toString());
	}
	
	private void setJsonStr(String string) {
		this.jsonStr = string;
	}
	
	public Customer parseJson() {
		
		try(JsonReader jsonReader = Json.createReader(new StringReader(jsonStr))){
			jsonObject = jsonReader.readObject();
		}
		
		customer.setFirstName(jsonObject.getString("firstName"));
		customer.setLastName(jsonObject.getString("lastName"));
		customer.setEmail(jsonObject.getString("email"));
		
		return customer;
	}
		
	
	public static void main(String[]args) {
		JsonpBean jb = new JsonpBean();
		jb.buildJson();
		Customer cus =jb.parseJson();
		System.out.println("First Name "+cus.getFirstName());
	}

}
