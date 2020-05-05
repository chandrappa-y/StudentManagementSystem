package com.board.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {

	static AmazonDynamoDB dynamoDb;

	public static void init() {
		if (dynamoDb == null) {
			dynamoDb = AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
					.withRegion("us-west-2").build();
		}

	}

	public AmazonDynamoDB getClient() {
		return dynamoDb;
	}
}
