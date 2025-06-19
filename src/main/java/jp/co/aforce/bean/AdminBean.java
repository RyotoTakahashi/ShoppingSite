package jp.co.aforce.bean;

import java.sql.Timestamp;

public class AdminBean {
    private long admin_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private Timestamp created_at;
    private Timestamp updated_at;

    public AdminBean() {}

    public long getAdmin_id() { return admin_id; }
    public void setAdmin_id(long admin_id) { this.admin_id = admin_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }
    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }
}
