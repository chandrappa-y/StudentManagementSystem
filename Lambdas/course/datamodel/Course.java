package com.student.course.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Course")
public class Course {
	private String department;
	private long courseId;
	private long professorId;
	private long boardId;
	private long taId;
	private List<Long> listOfRegisteredStudents = null;
	private String notificationTopic;

	public Course() {}

	public Course(long courseId, String department,long professorId, long boardId,long taId, List<Long> listOfRegisteredStudents,String notificationTopic) {
		this.courseId = courseId;
		this.department = department;
		this.professorId = professorId;
		this.boardId=boardId;
		this.taId=taId;
		this.listOfRegisteredStudents = listOfRegisteredStudents;
		this.notificationTopic=notificationTopic;
	}

	@DynamoDBAttribute(attributeName = "boardId")
	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName = "listOfRegisteredStudents")
	public List<Long> getListOfRegisteredStudents() {
		return listOfRegisteredStudents;
	}

	public void setListOfRegisteredStudents(List<Long> listOfRegisteredStudents) {
		this.listOfRegisteredStudents = listOfRegisteredStudents;
	}


	@DynamoDBHashKey(attributeName = "courseId")
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}


	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "professorId")
	public long getProfessorId() {
		return this.professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
		return;
	}
	
	@DynamoDBAttribute(attributeName = "taId")
	public long getTaId() {
		return this.taId;
	}

	public void setTaId(long taId) {
		this.taId = taId;
		return;
	}
	
	@DynamoDBAttribute(attributeName = "notificationTopic")
	public String getNotificationTopic() {
		return this.notificationTopic;
	}

	public String setNotificationTopic(String notificationTopic) {
		return this.notificationTopic = notificationTopic;
	}


	@DynamoDBIgnore
	@Override
	public String toString() {
		return ("Course Id : " + getCourseId() + " Department :" +getDepartment() + " Professor Id : "+getProfessorId()
		+" Board Id : "+getBoardId() + " List of students registered : "+getListOfRegisteredStudents());
	}
}

