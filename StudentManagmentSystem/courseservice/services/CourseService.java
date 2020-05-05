package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import com.csye6625.courseservice.datamodel.Course;
import com.csye6625.courseservice.datamodel.Professor;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class CourseService {
	ProfessorService professorService = new ProfessorService();
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public CourseService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Course> getAllCourses() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> scanResult = mapper.scan(Course.class, scanExpression);
		return scanResult;
	}

	public Course addCourse(Long courseId, String courseName, int maximumClassSize, String department) {
		Course course = new Course(courseId, courseName, maximumClassSize, department);
		Course c = mapper.load(Course.class, courseId);
		if (c == null){
			mapper.save(course);
		}
		else{
			throw new BadRequestException("Course Already Exist");
		}
		return course;
	}

	public Course getCourse(Long courseId) {
		Course course = mapper.load(Course.class, courseId);
		if (course == null) {
			throw new BadRequestException("Course Id doesn't exist");
		}
		return course;
	}

	public Course deleteCourse(long courseId) {
		Course deleteCourse = mapper.load(Course.class, courseId);
		if (deleteCourse == null) {
			throw new BadRequestException("Course ID doesn't exist");
		}
		mapper.delete(deleteCourse);
		return deleteCourse;
	}

	public Course updateCourseId(long newId, Course course) {
		Course updateCourse = mapper.load(Course.class, course.getCourseId());
		if (updateCourse == null) {
			throw new BadRequestException("Course Id doesn't exist");
		}
		updateCourse.setCourseId(newId);
		mapper.delete(course);
		mapper.save(updateCourse);
		return updateCourse;
	}

	public List<Course> getCourseByDepartment(String department) {
		ArrayList<Course> list = new ArrayList<>();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> scanResult = mapper.scan(Course.class, scanExpression);

		for (Course course : scanResult) {
			if (course.getDepartment().equals(department))
				list.add(course);
		}
		return list;
	}

	public boolean courseExist(long courseId) {
		Course course = mapper.load(Course.class, courseId);
		if (course != null) {
			return true;
		}	
		return false;
	}

	public Course assignProfessor(long courseId, Professor professor) {
		if (!professorService.professorExist(professor.getProfessorId())) {
			throw new BadRequestException("Professor Id is incorrect");
		}
		if (!courseExist(courseId)) {
			throw new BadRequestException("Course Id doesnt exist");
		}
		
		Professor prof = professorService.getProfessor(professor.getProfessorId());
		Course course = mapper.load(Course.class, courseId);
		course.setProfessor(prof.getFirstName());
		mapper.save(course);
		return course;
	}

	public void addAssignment(long assignmentId, long courseId) {
		Course course = mapper.load(Course.class, courseId);	
		if(!course.getAssignment().contains(assignmentId)){
			course.getAssignment().add(assignmentId);
		}
		mapper.save(course);
		return;
	}
	
	public void deleteAssignment(long assignmentId, long courseId) {
		Course course = mapper.load(Course.class, courseId);
		if(course.getAssignment().contains(assignmentId)){
			course.getAssignment().remove(assignmentId);
		}
		mapper.save(course);
		return;
	}
}
