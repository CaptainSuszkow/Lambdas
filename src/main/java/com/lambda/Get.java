package com.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

public class Get {
    public static Object handleRequest(Test request, Context context) {

            AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            return mapper.load(Test.class, request.getTestUUID());
    }

}
