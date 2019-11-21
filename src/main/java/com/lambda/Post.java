package com.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

public class Post {
    public static Object handleRequest(Test request, Context context) {
        if(Authorizer.verify(request.getUser().getUserToken())) {
            AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            mapper.save(request);
            return true;
        }
        else
            return false;
    }

}
