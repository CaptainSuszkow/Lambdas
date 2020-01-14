package com.lambda.APImethods.Candidate.Tests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.CandidateTest;

import javax.ws.rs.core.Response;

public class Post {
    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);
    private static DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
            .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
            .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
            .build();


    public static Object handleRequest(CandidateTest request, Context context) {

        try {
            Authorizer.authenticate(request.getRequestToken());
        } catch (Exception ex) {
            return Response.Status.UNAUTHORIZED;
        }
        if (Authorizer.verify(request.getRequestToken())) {
            mapper.save(request, dynamoDBMapperConfig);
            return Response.Status.ACCEPTED;
        } else
            return Response.Status.UNAUTHORIZED;
    }
}
