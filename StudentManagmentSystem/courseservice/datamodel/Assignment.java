package com.csye6625.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "assignment")
public class Assignment {
	private long assignmentId;
	private long courseId;
	private String assignmentName;
	private String assignmentContent;
	private int maxScore = 10;

	public Assignment() {}

	public Assignment(long assignmentId, long courseId, String assignmentName, String assignmentContent, int maxScore) {
		this.assignmentId = assignmentId;
		this.courseId = courseId;
		this.assignmentName = assignmentName;
		this.assignmentContent = assignmentContent;
		this.maxScore = (maxScore == 0) ? 10 : maxScore;
	}

	@DynamoDBHashKey(attributeName = "assignmentId")
	public long getAssignmentId() {
		return this.assignmentId;
	}

	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
		return;
	}

	@DynamoDBAttribute(attributeName = "courseId")
	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "maxScore")
	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
		return;
	}

	@DynamoDBAttribute(attributeName = "assignmentName")
	public String getAssignmentName() {
		return this.assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	@DynamoDBAttribute(attributeName = "assignmentContent")
	public String getAssignmentContent() {
		return this.assignmentContent;
	}

	public void setAssignmentContent(String assignmentContent) {
		this.assignmentContent = assignmentContent;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "Assignment ID = " + getAssignmentId() + "Assignment Name = " + getAssignmentName() + "Course ID = "
				+ getCourseId();
	}
}
