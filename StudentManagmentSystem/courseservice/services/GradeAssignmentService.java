package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import com.csye6625.courseservice.datamodel.Assignment;
import com.csye6625.courseservice.datamodel.GradeAssignment;
import com.csye6625.courseservice.datamodel.Student;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class GradeAssignmentService {

	StudentService studentService = new StudentService();
	AssignmentService assignmentService = new AssignmentService();

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public GradeAssignmentService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<GradeAssignment> getGradeOfAllAssignments(long studentId) {
		if (!studentService.studentExist(studentId)) {
			throw new BadRequestException("Incorrect StudentId");
		}

		List<GradeAssignment> scanResult = mapper.scan(GradeAssignment.class, new DynamoDBScanExpression());
		ArrayList<GradeAssignment> list = new ArrayList<>();
		for (GradeAssignment grade : scanResult) {
			if (grade.getStudentId() == (studentId))
				list.add(grade);
		}
		return list;

	}

	public GradeAssignment getGrade(long assignmentId, long studentId) {
		long id = getGradeId(assignmentId, studentId);
		GradeAssignment grade = mapper.load(GradeAssignment.class, id);
		if (grade == null) {
			throw new BadRequestException("Wrong Grade Id");
		}
		return grade;
	}

	public GradeAssignment gradeAssignment(long assignmentId, long studentId, int score) {
		long id = getGradeId(assignmentId, studentId);
		if (!assignmentService.assignmentExist(assignmentId)) {
			throw new BadRequestException("Assignment doesn't exist");
		}
		Assignment assignment = assignmentService.getAssignmentById(assignmentId);
		long courseId = assignment.getCourseId();
		Student student = studentService.getStudentById(studentId);
		GradeAssignment grade;
		for (long cid : student.getRegisteredCourseList()) {
			if (courseId == cid) {
				if (assignment.getMaxScore() < score) {
					throw new BadRequestException(
							"The maximum score for the assignment is " + assignment.getMaxScore());
				}
				grade = new GradeAssignment(id, score, assignmentId, assignment.getAssignmentName(), studentId,
						student.getFirstName());
				System.out.println(grade);
				mapper.save(grade);
				return grade;
			}
		}
		throw new BadRequestException("Student is not registered to the course hence can't grade");
	}

	public long getGradeId(long assignmentId, long studentId) {
		String a = Long.toString(assignmentId);
		String b = Long.toString(studentId);
		String s = a + b;
		long id = Long.parseLong(s);
		return id;
	}

	public GradeAssignment updateGrade(long assignmentId, long studentId, int score) {
		long id = getGradeId(assignmentId, studentId);
		GradeAssignment grade = mapper.load(GradeAssignment.class, id);
		if (grade == null) {
			throw new BadRequestException("This Assignment Id or StudentId is incorrect");
		}
		grade.setScore(score);
		mapper.save(grade);
		return grade;
	}

	public GradeAssignment deleteGrade(long gradeId) {
		GradeAssignment grade = mapper.load(GradeAssignment.class, gradeId);
		if (grade == null) {
			throw new BadRequestException("Grade Id doesn't exist");
		}
		mapper.delete(grade);
		return grade;
	}

}
