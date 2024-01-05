package objects;

import java.io.Serializable;
import org.json.JSONObject;

public class User implements Serializable{
    private int id;
    private String name;
    private String phone;
    private String role;

    public User(int id, String name, String phone, String role) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }
    
    public User() {
        this.id = 0;
        this.name = "";
        this.phone = "";
        this.role = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phone", phone);
        json.put("role", role);
        json.put("id", id);
        return json;
    }
}
