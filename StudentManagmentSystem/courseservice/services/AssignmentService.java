package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6625.courseservice.datamodel.Assignment;
import com.csye6625.courseservice.datamodel.Course;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;


public class AssignmentService {

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public AssignmentService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	CourseService courseService = new CourseService();

	public Assignment addAssignment(long courseId, String assignmentName, String assignmentContent, int maxScore) {

		if (!courseService.courseExist(courseId))
		{
			throw new BadRequestException("Course Id is incorrect");
		}	
		
		long size = mapper.count(Assignment.class, new DynamoDBScanExpression());
		long nextAvailableId = size + 1 * 1000;
		Assignment duplicate = mapper.load(Assignment.class, nextAvailableId);
		if (duplicate != null) {
			nextAvailableId++;
		}
		
		Assignment assignment = new Assignment(nextAvailableId, courseId, assignmentName, assignmentContent, maxScore);
		mapper.save(assignment);
		courseService.addAssignment(nextAvailableId, courseId);
		return assignment;
	}

	public List<Assignment> getAllAssignments() {
		List<Assignment> scanResult = mapper.scan(Assignment.class, new DynamoDBScanExpression());
		return scanResult;
	}

	public List<Assignment> getAssignmentsOfCourse(long courseId) {
		List<Assignment> list = new ArrayList<>();
		
		if (!courseService.courseExist(courseId)) {
			throw new BadRequestException("Course ID is incorrect");
		}
		
		Course course = courseService.getCourse(courseId);
		List<Long> assignmentList = course.getAssignment();
		for (Long id : assignmentList) {
			Assignment assignment = mapper.load(Assignment.class, id);
			list.add(assignment);
		}
		return list;
	}

	public Assignment deleteAssignment(long assignmentId) {
		Assignment deleteAssignment = mapper.load(Assignment.class, assignmentId);
		if (deleteAssignment == null) {
			throw new BadRequestException("Assignment ID doesn't exist");
		}
		courseService.deleteAssignment(assignmentId, deleteAssignment.getCourseId() );
		mapper.delete(deleteAssignment);
		return deleteAssignment;
	}

	public Assignment getAssignmentById(long assignmentId) {
		Assignment assignment = mapper.load(Assignment.class, assignmentId);
		if (assignment != null)
		{	
			return assignment;
		}
		throw new BadRequestException("Assignment ID is incorrect");
	}

	public boolean assignmentExist(long assignmentId) {
		Assignment assignment = mapper.load(Assignment.class, assignmentId);
		if (assignment != null)
		{	
			return true;
		}
		return false;
	}
}
