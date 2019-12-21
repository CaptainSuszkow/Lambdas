package com.lambda.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

@DynamoDBDocument
public class User {

    @DynamoDBAttribute(attributeName = "UserName")
    private String userName;

    @DynamoDBIgnore
    private String userToken;

    public User(String userName, String userToken) {
        this.userName = userName;
        this.userToken = userToken;
    }

    public User() {
    }

    public User(String userToken) {
        this.userToken = userToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBIgnore
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
