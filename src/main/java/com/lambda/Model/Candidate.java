package com.lambda.Model;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "cc_tests")
public class Candidate {

    private String testUUID;
    private String candidateName;
    private String recruiterName;
    private Test test;
    private List<String> answers;
    private Double score;
    private String requestToken;



    public Candidate() {

    }


    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getTestUUID() {
        return testUUID;
    }
    public void setTestUUID(String testUUID) {
        this.testUUID = testUUID;
    }

    @DynamoDBAttribute(attributeName = "candidateName")
    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    @DynamoDBAttribute(attributeName = "recruiterName")
    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    @DynamoDBAttribute(attributeName = "Test")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
    @DynamoDBAttribute(attributeName = "answers")
    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @DynamoDBAttribute(attributeName = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @DynamoDBIgnore
    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}
