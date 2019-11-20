package com.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

public class testPots {

    public static Object handleRequest(PersonRequest request, Context context) {
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(request);
        return request;
    }
}
