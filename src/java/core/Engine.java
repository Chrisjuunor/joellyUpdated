package core;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONObject;
import service.DBService;

@Path("start")
public class Engine {
    JSONObject json;
    DBService dbService;
    
    public Engine() {
        dbService = new DBService();
    }

    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        json = new JSONObject();
        json.put("status", "OK");
        return json.toString();
    }
    
    @Path("account_creation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String accountCreation(@FormParam("name") String name,
            @FormParam("phone") String phone,  @FormParam("password") String password,
            @FormParam("role") String role) {
        json = new JSONObject();
        json.put("status", "OK");
        json.put("userID", dbService.createAccount(name, phone, password, role));
        return json.toString();
    }
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormParam("phone") String phone,
            @FormParam("password") String password) {
        json = new JSONObject();
        json.put("status", "OK");
        json.put("user", dbService.performLogin(phone, password).toJSON());
        return json.toString();
    }
}
