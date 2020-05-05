package com.register.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Register")
public class Register {
	private long registrationId;
	private long offeringId;
	private String offeringType;
	private String department;
	private float perUnitPrice;
	private long courseId;
	private long professorId;

	public Register() {}

	public Register(long registrationId,long courseId,String offeringType,String department,float perUnitPrice) {
		this.registrationId=registrationId;
		this.offeringId=courseId;
		this.courseId=courseId;
		this.offeringType=offeringType;
		this.department=department;
		this.perUnitPrice=perUnitPrice;
	}

	@DynamoDBHashKey(attributeName = "registrationId")
	public long getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(long registrationId) {
		this.registrationId = registrationId;
	}

	@DynamoDBAttribute(attributeName = "offeringId")
	public long getOfferingId() {
		return this.offeringId;
	}

	public void setOfferingId(long courseId) {
		this.offeringId = courseId;
	}
	
	
	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	public long getProfessorId() {
		return this.professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	
	@DynamoDBAttribute(attributeName = "offeringType")
	public String getOfferingType() {
		return this.offeringType;
	}

	public void setOfferingType(String offeringType) {
		this.offeringType = offeringType;
	}
	
	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBAttribute(attributeName = "perUnitPrice")
	public float getPerUnitPrice() {
		return this.perUnitPrice;
	}

	public void setPerUnitPrice(float perUnitPrice) {
		this.perUnitPrice = perUnitPrice;
	}
	
}

