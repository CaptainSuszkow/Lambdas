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

public class GetMyself {
    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Candidate request, Context context) {

        try {
            Authorizer.authenticate(request.getRequestToken());
        }
        catch (Exception ex) {
            return Response.Status.UNAUTHORIZED;
        }

        Map<String, AttributeValue> eav = new HashMap<>();

        eav.put(":val",new AttributeValue()
                .withS(Authorizer.getUserName(request.getRequestToken())));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("candidateName =  :val").withExpressionAttributeValues(eav);
        return mapper.scan(Candidate.class, scanExpression);
        }

}