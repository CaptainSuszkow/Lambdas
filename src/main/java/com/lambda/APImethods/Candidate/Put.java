package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Candidate;

import javax.ws.rs.core.Response;

public class Put {

    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Candidate request, Context context)  {

        try {
            Authorizer.authenticate(request.getRequestToken());
        }
        catch(Exception ex){
            return Response.Status.UNAUTHORIZED;
        }

        if(Authorizer.verify(request.getRequestToken())) {
            request.setRecruiterName(Authorizer.getUserName(request.getRequestToken()));
            mapper.save(request);
            return  Response.Status.CREATED;
        }
        else
            return Response.Status.UNAUTHORIZED;
    }
}
