package com.register.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.register.datamodel.DynamoDBConnector;
import com.register.datamodel.Register;


public class RegisterService {

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public RegisterService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public void registerCourse(long courseId,String offeringType,String department) {
		//create Id
		long size = mapper.count(Register.class, new DynamoDBScanExpression());
		long nextAvailableId = size + 1 * 1000;
		Register register = mapper.load(Register.class, nextAvailableId);
		float perUnitPrice = 6000;
		if (register == null){
			register = new Register(nextAvailableId,courseId,offeringType,department,perUnitPrice);
		}else {
			nextAvailableId +=1;
			register = new Register(nextAvailableId,courseId,offeringType,department,perUnitPrice);
		}
		mapper.save(register);
		return;
	}

}
