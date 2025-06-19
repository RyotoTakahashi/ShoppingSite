package jp.co.aforce.bean;

import java.sql.Timestamp;

public class AdminTokenBean {
    private long token_id;
    private long admin_id;
    private String token;
    private Timestamp expires_at;
    private boolean used;
    private Timestamp created_at;

    public AdminTokenBean() {}

    public long getToken_id() { return token_id; }
    public void setToken_id(long token_id) { this.token_id = token_id; }

    public long getAdmin_id() { return admin_id; }
    public void setAdmin_id(long admin_id) { this.admin_id = admin_id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Timestamp getExpires_at() { return expires_at; }
    public void setExpires_at(Timestamp expires_at) { this.expires_at = expires_at; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }
}
