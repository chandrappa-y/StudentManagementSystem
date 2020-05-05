package com.csye6625.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "gradeAssignment")
public class GradeAssignment {
	private int score;
	private long assignmentId;
	private String assignmentName;
	private String studentName;
	private long studentId;
	private long gradeId;

	public GradeAssignment() {}

	public GradeAssignment(long gradeId, int score, long assignmentId, String assignmentName, long studentId,
			String studentName) {
		this.gradeId = gradeId;
		this.score = score;
		this.assignmentId = assignmentId;
		this.studentId = studentId;
		this.assignmentName = assignmentName;
		this.studentName = studentName;
	}

	@DynamoDBAttribute(attributeName = "score")
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
		return;
	}

	@DynamoDBAttribute(attributeName = "assignmentId")
	public long getAssignmentId() {
		return this.assignmentId;
	}

	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
	}

	@DynamoDBAttribute(attributeName = "studentId")
	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@DynamoDBHashKey(attributeName = "gradeId")
	public long getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	@DynamoDBAttribute(attributeName = "studentName")
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
		return;
	}

	@DynamoDBAttribute(attributeName = "assignmentName")
	public String getAnnouncementName() {
		return this.assignmentName;
	}

	public void setAnnouncementName(String assignmentName) {
		this.assignmentName = assignmentName;
		return;
	}

}
