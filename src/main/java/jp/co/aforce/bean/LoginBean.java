package jp.co.aforce.bean;

public class LoginBean {
    private long user_id;
    private String verifier;
    private String salt;

    public LoginBean() {}

    public long getUser_id() { return user_id; }
    public void setUser_id(long user_id) { this.user_id = user_id; }

    public String getVerifier() { return verifier; }
    public void setVerifier(String verifier) { this.verifier = verifier; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }
}
