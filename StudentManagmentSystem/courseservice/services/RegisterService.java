package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import com.csye6625.courseservice.datamodel.Course;
import com.csye6625.courseservice.datamodel.Register;
import com.csye6625.courseservice.datamodel.Student;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


public class RegisterService {

	StudentService studentService = new StudentService();
	CourseService courseService = new CourseService();

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public RegisterService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Student> getStudentsRegisteredForCourse(long courseId) {
		if (!courseService.courseExist(courseId)) {
			throw new BadRequestException("Course Id doesn't exist");
		}
		Register register = mapper.load(Register.class, courseId);
		if (register == null) {
			throw new BadRequestException("No student has registered for this course");

		}
		List<Long> list = register.getStudentRegisteredList();
		List<Student> result = new ArrayList<>();
		for (Long id : list) {
			result.add(studentService.getStudentById(id));
		}
		return result;
	}

	public Student registerForCourse(long studentId, long courseId) {
		if (!studentService.studentExist(studentId)) {
			throw new BadRequestException("Student Id doesn't exist");
		}
		if (!courseService.courseExist(courseId)) {
			throw new BadRequestException("Course Id doesn't exist");
		}
		
		Register register = mapper.load(Register.class, courseId);
		if (register == null) {
			register = new Register(studentId, courseId);
		}
		
		Student student = studentService.getStudentById(studentId);
		List<Long> list = student.getRegisteredCourseList();
		for (long id : list) {
			if (id == courseId) {
				throw new BadRequestException("Already registered");
			}
		}
		student.getRegisteredCourseList().add(courseId);
		register.getStudentRegisteredList().add(studentId);
		mapper.save(register);
		mapper.save(student);
		return student;
	}

	public Student dropCourse(long studentId, long courseId) {
		if (!studentService.studentExist(studentId)) {
			throw new BadRequestException("Student Id doesn't exist");
		}
		if (!courseService.courseExist(courseId)) {
			throw new BadRequestException("Course Id doesn't exist");
		}
		Register register = mapper.load(Register.class, courseId);
		if (register == null) {
			throw new BadRequestException("No one has registered for the course");
		}
		List<Long> list = register.getStudentRegisteredList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == studentId) {
				list.remove(i);
				break;
			}
		}

		Student student = studentService.getStudentById(studentId);
		List<Long> courseList = student.getRegisteredCourseList();
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i) == courseId) {
				courseList.remove(i);
				break;
			}
		}
		mapper.save(register);
		mapper.save(student);
		return student;
	}

	public List<Course> getCoursesRegisteredByStudent(long studentId) {
		Student student = studentService.getStudentById(studentId);
		if (student == null) {
			throw new BadRequestException("No student with this ID exist");
		}
		List<Long> list = student.getRegisteredCourseList();
		List<Course> result = new ArrayList<>();
		for (Long id : list) {
			if (courseService.courseExist(id)){
				result.add(courseService.getCourse(id));
			}
		}
		return result;
	}
}
