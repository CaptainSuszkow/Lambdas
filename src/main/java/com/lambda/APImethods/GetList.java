package com.lambda.APImethods;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Test;

import javax.ws.rs.core.Response;

public class GetList {
    public static Object handleRequest(Test request, Context context) {

        try {
            Authorizer.authenticate(request.getUser().getUserToken());
        }
        catch(Exception ex){
            return Response.Status.UNAUTHORIZED;
        }

        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        return mapper.scan(Test.class, new DynamoDBScanExpression());
    }

}
