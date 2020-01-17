/*package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.APImethods.GetList;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Candidate;
import com.lambda.Model.Test;
import com.lambda.Model.User;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Candidate temp;
        try {
             temp = mapper.scan(Candidate.class, scanExpression).get(0);
        }
        catch (Exception ex){
            return Response.Status.NOT_FOUND;
        }


//        eav.clear();
//        eav.put(":val", new AttributeValue().withS(recruiterName));
//
//        scanExpression = new DynamoDBScanExpression()
//                .withFilterExpression("#key = :val").withExpressionAttributeValues(eav);
//        scanExpression.addExpressionAttributeNamesEntry("#key","user.userName");
//        scanExpression.setFilterExpression("#key = :val");
//        scanExpression.setExpressionAttributeValues(eav);

        List<Test> output = new ArrayList<>();
        List<Test> input = mapper.scan(Test.class, new DynamoDBScanExpression());


        for(int  i = 0; i < input.size(); i++) {
            boolean answered = false;
            if( input.get(i).getQuestions() != null &&
                    input.get(i).getUser() != null) {

                if(input.get(i).getUser().getUserName().equals(temp.getRecruiterName())) {
                    for (int j = 0; j < temp.getDoneTests().size(); j++) {
                        if (input.get(i).getTestUUID().equals(temp.getDoneTests().get(j).getTestUUID())) {
                            answered = true;
                        }

                    }
                    if(!answered){
                        output.add(input.get(i));
                    }
                }

            }
        }
        return output;
        }

}*/
package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.APImethods.GetList;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Candidate;
import com.lambda.Model.Test;
import com.lambda.Model.User;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMyself {
    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static Object handleRequest(Candidate request, Context context) {

        try {
            Authorizer.authenticate(request.getRequestToken());
        } catch (Exception ex) {
            return Response.Status.UNAUTHORIZED;
        }

        Map<String, AttributeValue> eav = new HashMap<>();

        eav.put(":val", new AttributeValue()
                .withS(Authorizer.getUserName(request.getRequestToken())));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("candidateName =  :val").withExpressionAttributeValues(eav);
        String recruiterName;
        try {
            recruiterName = mapper.scan(Candidate.class, scanExpression).get(0).getRecruiterName();
        } catch (Exception ex) {
            return Response.Status.NOT_FOUND;
        }


        eav.clear();
        eav.put(":val", new AttributeValue().withS(recruiterName));

        scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#key = :val").withExpressionAttributeValues(eav);
        scanExpression.addExpressionAttributeNamesEntry("#key", "user.userName");
        scanExpression.setFilterExpression("#key = :val");
        scanExpression.setExpressionAttributeValues(eav);

        List<Test> output = new ArrayList<>();
        List<Test> input = mapper.scan(Test.class, new DynamoDBScanExpression());


        Test[] tests = (Test[]) input.stream().filter(test -> test.getQuestions() != null && test.getUser() != null).toArray();
        for (Test test : tests){
            if (test.getUser().getUserName().equals(recruiterName)) {
                output.add(test);
            }
        }
//        for (Test test : input) {
//            if (test.getQuestions() != null && test.getUser() != null) {
//                if (test.getUser().getUserName().equals(recruiterName)) {
//                    output.add(test);
//                }
//            }
//        }
        return output;
    }

}