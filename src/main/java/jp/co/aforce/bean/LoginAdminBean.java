package jp.co.aforce.bean;

public class LoginAdminBean {
    private long admin_id;
    private String verifier;
    private String salt;

    public LoginAdminBean() {}

    public long getAdmin_id() { return admin_id; }
    public void setAdmin_id(long admin_id) { this.admin_id = admin_id; }

    public String getVerifier() { return verifier; }
    public void setVerifier(String verifier) { this.verifier = verifier; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }
}
