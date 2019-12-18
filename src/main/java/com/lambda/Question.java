package com.lambda;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Question {

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correct;
    private String id;
    private Integer questionType;
    private String questionContent;
    private String questionAnswer;

    public Question() {
    }

    public Question(String answerA, String answerB, String answerC, String answerD, String correct, String id, Integer questionType, String questionContent) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correct = correct;
        this.id = id;
        this.questionType = questionType;
        this.questionContent = questionContent;
    }

    public Question(String id, Integer questionType, String questionContent, String questionAnswer) {
        this.id = id;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.questionAnswer = questionAnswer;
    }


    @DynamoDBAttribute(attributeName = "AnswerA")
    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }


    @DynamoDBAttribute(attributeName = "AnswerB")
    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }


    @DynamoDBAttribute(attributeName = "AnswerC")
    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    @DynamoDBAttribute(attributeName = "AnswerD")
    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }


    @DynamoDBAttribute(attributeName = "Correct")
    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @DynamoDBAttribute(attributeName = "QuestionID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @DynamoDBAttribute(attributeName = "questionType")
    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer type) {
        questionType = type;
    }

    @DynamoDBAttribute(attributeName = "questionAnswer")
    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }


    @DynamoDBAttribute(attributeName = "QuestionContent")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }



}
