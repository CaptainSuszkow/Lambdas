package com.lambda;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.services.lambda.runtime.Context;

public class testAutenticate{

    public Boolean handleRequest(User input, Context context) {
        Authorizer a = new Authorizer();
        return a.verify(input.getUserToken());
    }
}
