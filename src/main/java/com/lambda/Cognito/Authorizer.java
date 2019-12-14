package com.lambda.Cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class Authorizer {

    public static Object authenticate(String token) {
        Algorithm algorithm = Algorithm.RSA256(new CognitoRSAKeyProvider());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("https://cognito-idp.us-east-1.amazonaws.com/us-east-1_rHL3nVPk0")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

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
