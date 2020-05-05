package com.csye6625.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "course")
public class Course {
	private String department;
	private List<Long> assignments = new ArrayList<>();
	private long courseId;
	private String courseName;
	private int creditHours;
	private int maximumClassSize;
	private int classSize = 0;
	private String professor;

	public Course() {}

	public Course(long courseId, String courseName, int maximumClassSize, String department) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.maximumClassSize = maximumClassSize;
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "courseName")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@DynamoDBAttribute(attributeName = "classSize")
	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	@DynamoDBAttribute(attributeName = "creditHours")
	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	@DynamoDBHashKey(attributeName = "courseId")
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "maximumClassSize")
	public int getMaximumClassSize() {
		return maximumClassSize;
	}

	public void setMaximumClassSize(int maximumClassSize) {
		this.maximumClassSize = maximumClassSize;
	}

	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "professor")
	public String getProfessor() {
		return this.professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
		return;
	}

	@DynamoDBAttribute(attributeName = "assignments")
	public List<Long> getAssignment() {
		return this.assignments;
	}

	public void setAssignment(List<Long> assignments) {
		this.assignments = assignments;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "Course Id = " + getCourseId() + ", Course Name = " + getCourseName() + ", Credit Hours = "
				+ getCreditHours() + ", Maximum Class Size = " + getMaximumClassSize() + ", Seats left = "
				+ (getMaximumClassSize() - getClassSize());
	}
}
