package com.lambda;

import com.amazonaws.services.lambda.runtime.Context;

public class testAutenticate{

    public Boolean handleRequest(User input, Context context) {

        return Authorizer.verify(input.getUserToken());
    }
}
