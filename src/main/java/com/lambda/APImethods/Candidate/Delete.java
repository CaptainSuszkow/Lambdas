package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class Delete {

    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);



}
