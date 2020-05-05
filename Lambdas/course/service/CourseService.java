package com.student.course.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.student.course.CourseExist;
import com.student.course.datamodel.Course;
import com.student.course.datamodel.DynamoDBConnector;



public class CourseService{
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public CourseService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public Course addCourse(Long courseId, String department, long professorId, long boardId, long taId,List<Long> listOfRegisteredStudents
			,String notificationTopic) throws CourseExist {

		Course course = new Course(courseId, department, professorId, boardId,taId,listOfRegisteredStudents,notificationTopic);
		Course c = mapper.load(Course.class, courseId);
		
		if (c == null){
			mapper.save(course);
		}
		else{
			throw new RuntimeException("course Exist");
		}
		
		
		return course;
	}

}
