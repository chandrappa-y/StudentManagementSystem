package com.csye6625.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "student")
public class Student {
	private long studentId;
	private String firstName;
	private String lastName;
	private String department;
	private int maxRegisteredCreditHrs = 9;
	private List<Long> registeredCourseList = new ArrayList<>();

	public Student() {}

	public Student(long studentId, String firstName, String lastName, String department) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}

	@DynamoDBHashKey(attributeName = "studentId")
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute(attributeName = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute(attributeName = "maxRegisteredCreditHrs")
	public int getMaxRegisteredCreditHrs() {
		return maxRegisteredCreditHrs;
	}

	public void setMaxRegisteredCreditHrs(int maxRegisteredCreditHrs) {
		this.maxRegisteredCreditHrs = maxRegisteredCreditHrs;
	}

	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "registeredCourseList")
	public List<Long> getRegisteredCourseList() {
		return this.registeredCourseList;
	}

	public void setRegisteredCourseList(List<Long> registeredCourseList) {
		this.registeredCourseList = registeredCourseList;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "StudentId = " + getStudentId() + ",FirstName = " + getFirstName() + ", LastName = " + getLastName()
				+ ",Program Enrolled = " + getDepartment();
	}
}
