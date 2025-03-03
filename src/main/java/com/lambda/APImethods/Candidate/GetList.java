package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Candidate;


import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class GetList {

    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Candidate request, Context context) {

        try {
            Authorizer.authenticate(request.getRequestToken());
        } catch (Exception ex) {
            return Response.Status.UNAUTHORIZED;
        }

        if(Authorizer.verify(request.getRequestToken())) {

            Map<String, AttributeValue> eav = new HashMap<>();

            eav.put(":val",new AttributeValue()
                    .withS(Authorizer.getUserName(request.getRequestToken())));

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression(" recruiterName =  :val ").withExpressionAttributeValues(eav);
            return mapper.scan(Candidate.class, scanExpression);
        }
        else
            return Response.Status.UNAUTHORIZED;

    }
}
