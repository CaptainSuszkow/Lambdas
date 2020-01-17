package com.lambda.APImethods.Tests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Test;

import javax.ws.rs.core.Response;

public class Post {

    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Test request, Context context) {

        try {
            Authorizer.authenticate(request.getUser().getUserToken());
        }
        catch(Exception ex){
            return Response.Status.UNAUTHORIZED;
        }

        if(Authorizer.verify(request.getUser().getUserToken())) {
            request.getUser().setUserName(Authorizer.getUserName(request.getUser().getUserToken()));
            mapper.save(request);
            return Response.Status.CREATED;
        }
        else
            return Response.Status.UNAUTHORIZED;
    }
}
