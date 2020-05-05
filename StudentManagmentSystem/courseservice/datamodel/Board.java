package com.csye6625.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "board")
public class Board {
	private long courseId;
	private String announcement;
	private List<String> announcementList = new ArrayList<>();

	public Board() {}

	public Board(long courseId, String announcement) {
		this.courseId = courseId;
		this.announcement = announcement;
		announcementList.add(announcement);
	}

	@DynamoDBHashKey(attributeName = "courseId")
	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "announcement")
	public String getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	@DynamoDBAttribute(attributeName = "announcementList")
	public List<String> getAnnouncementList() {
		return this.announcementList;
	}

	public void setAnnouncementList(List<String> announcementList) {
		this.announcementList = announcementList;
	}
}
