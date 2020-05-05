package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import com.csye6625.courseservice.datamodel.Student;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;


public class StudentService {
	CourseService courseService = new CourseService();
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public StudentService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Student> getAllStudents() {
		List<Student> scanResult = mapper.scan(Student.class, new DynamoDBScanExpression());
		return scanResult;
	}

	public Student getStudentById(long studentId) {
		Student student = mapper.load(Student.class, studentId);
		if (student == null) {
			throw new BadRequestException("Student Id doesn't exist");
		}
		return student;
	}

	public Student addStudent(String firstName, String lastName, String department) {
		long size = mapper.count(Student.class, new DynamoDBScanExpression());
		long nextAvailableId = size + 1 * 100;
		Student duplicate = mapper.load(Student.class, nextAvailableId);
		if (duplicate != null) {
			nextAvailableId++;
		}
		Student student = new Student(nextAvailableId, firstName, lastName, department);
		mapper.save(student);
		return student;
	}

	public Student deleteStudent(long studentId) {
		if (!studentExist(studentId)) {
			throw new BadRequestException("Student Id doesn't exist");
		}
		Student student = mapper.load(Student.class, studentId);
		mapper.delete(student);
		return student;
	}

	public Student updateStudentId(long studentId, Student student) {
		if (!studentExist(student.getStudentId())) {
			throw new BadRequestException("Student Id doesn't exist");
		}
		Student s = mapper.load(Student.class, student.getStudentId());
		s.setStudentId(studentId);
		mapper.delete(student);
		mapper.save(s);
		return s;
	}

	public List<Student> getStudentsByDepartment(String department) {
		ArrayList<Student> list = new ArrayList<>();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> scanResult = mapper.scan(Student.class, scanExpression);

		for (Student Student : scanResult) {
			if (Student.getDepartment().equals(department))
				list.add(Student);
		}
		return list;
	}

	public boolean studentExist(long studentId) {
		Student student = mapper.load(Student.class, studentId);
		if (student != null)
			return true;
		return false;
	}

}
