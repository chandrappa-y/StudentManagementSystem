package com.csye6625.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "register")
public class Register {
	private long courseId;
	private long studentId;
	private List<Long> studentRegistered = new ArrayList<>();

	public Register() {}

	public Register(long studentId, long courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	@DynamoDBHashKey(attributeName = "courseId")
	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "studentId")
	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName = "studentRegistered")
	public List<Long> getStudentRegisteredList() {
		return this.studentRegistered;
	}

	public void setStudentRegisteredList(List<Long> studentRegistered) {
		this.studentRegistered = studentRegistered;
	}

}
