package com.lambda.APImethods;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class GetList {
    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Test request, Context context) {

        try {
            Authorizer.authenticate(request.getUser().getUserToken());
        }
        catch(Exception ex){
            return Response.Status.UNAUTHORIZED;
        }


        List<Test> output = new ArrayList<>();
        List<Test> input = mapper.scan(Test.class, new DynamoDBScanExpression());

        String userName = Authorizer.getUserName(request.getUser().getUserToken());

        for(int  i = 0; i < input.size(); i++){
            if( input.get(i).getQuestions() != null &&
                input.get(i).getUser() != null){

                if(input.get(i).getUser().getUserName().equals(userName))
                    output.add(input.get(i));
            }
        }
        return  output;
    }

}
