package com.lambda;

public class AuthenticateUserResponse{

    private String userName;
    private String openIdToken;
    private String status;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getOpenIdToken() { return openIdToken; }
    public void setOpenIdToken(String openIdToken) { this.openIdToken = openIdToken; }

    public String getStatus() {	return status; }
    public void setStatus(String status) { this.status = status; }
}