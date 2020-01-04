package com.lambda.APImethods.Candidate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Cognito.Authorizer;
import com.lambda.Model.Candidate;

import javax.ws.rs.core.Response;

public class Post {
    private static AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
    private static DynamoDBMapper mapper = new DynamoDBMapper(client);
    private static Candidate candidateFromDB;

    public static Object handleRequest(Candidate request, Context context) {

        try {
            Authorizer.authenticate(request.getRequestToken());
        } catch (Exception ex) {
            return Response.Status.UNAUTHORIZED;
        }

        if (Authorizer.verify(request.getRequestToken())) {
            mapper.save(request);
            return Response.Status.ACCEPTED;
        } else {

            candidateFromDB = mapper.load(Candidate.class, request.getTestUUID());
            request.setCandidateName(Authorizer.getUserName(request.getRequestToken()));

            if (request.getTest() != null) {
                    return Response.Status.FORBIDDEN;
            }
            if (candidateFromDB.getCandidateName() != null) {
                if (!candidateFromDB.getCandidateName().equals(request.getCandidateName()))
                    return Response.Status.FORBIDDEN;
            }
            if (candidateFromDB.getTestUUID() != null) {
                if (!candidateFromDB.getTestUUID().equals(request.getTestUUID()))
                    return Response.Status.FORBIDDEN;
            }
            if (request.getRecruiterName() != null) {
                    return Response.Status.FORBIDDEN;
            }
            if (request.getScore() != null) {
                    return Response.Status.FORBIDDEN;
            }
            if (candidateFromDB.getAnswers() != null) {
                if (!candidateFromDB.getAnswers().isEmpty())
                    return Response.Status.FORBIDDEN;
            }

            mapper.save(request);
            return Response.Status.ACCEPTED;
        }
    }
}
