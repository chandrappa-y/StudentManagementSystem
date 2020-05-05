package com.student.course.service;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.student.course.datamodel.DynamoDBConnector;
import com.student.course.datamodel.Professor;



public class ProfessorService {

		static DynamoDBConnector dynamoDb;
		DynamoDBMapper mapper;

		public ProfessorService() {
			dynamoDb = new DynamoDBConnector();
			DynamoDBConnector.init();
			mapper = new DynamoDBMapper(dynamoDb.getClient());
		}
		
		public String getProfessorEmailId(long professorId) {
			Professor professor = mapper.load(Professor.class, professorId);
			if(professor==null) {
				throw new RuntimeException("Did not find professor details to send notification");
			}
//			return professor.getEmailId();
			return "yashaswinichandr@gmail.com";
		}
}

