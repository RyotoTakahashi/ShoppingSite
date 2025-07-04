package jp.co.aforce.bean;

import java.sql.Timestamp;

public class AdminTokenBean {
    private long token_id;
    private long user_id;
    private String token;
    private Timestamp expires_at;

    public AdminTokenBean() {}

    public long getToken_id() { return token_id; }
    public void setToken_id(long token_id) { this.token_id = token_id; }

    public long getUser_id() { return user_id; }
    public void setUser_id(long user_id) { this.user_id = user_id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Timestamp getExpires_at() { return expires_at; }
    public void setExpires_at(Timestamp expires_at) { this.expires_at = expires_at; }
}
