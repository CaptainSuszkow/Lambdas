package com.lambda;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;

public class Authorizer {

    public static Boolean verify(String token){

        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();

        GetUserRequest authRequest = new GetUserRequest().withAccessToken(token);
        GetUserResult authResponse = cognitoClient.getUser(authRequest);

        if(Integer.parseInt(authResponse.getUserAttributes().get(2).getValue()) == 1) {
            return true;
        }
        else
            return false;
    }
}
