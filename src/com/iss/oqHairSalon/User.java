package com.iss.oqHairSalon;

public class User {
    private String loginName;
    private String loginPassword;

    public User(){}
    public User(String loginName, String loginPassword) {
        setLoginName(loginName);
        setLoginPassword(loginPassword);
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
