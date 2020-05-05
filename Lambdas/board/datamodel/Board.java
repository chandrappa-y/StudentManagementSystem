package com.board.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Board")
public class Board {

	private long boardId;
	private List<String> announcementList = new ArrayList<>();

	public Board() {}

	public Board(long boardId) {
		this.boardId=boardId;
		announcementList.addAll(new ArrayList<String>());
	}

	@DynamoDBHashKey(attributeName = "boardId")
	public long getBoardId() {
		return this.boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	
	@DynamoDBAttribute(attributeName = "announcementList")
	public List<String> getAnnouncementList() {
		return this.announcementList;
	}

	public void setAnnouncementList(List<String> announcementList) {
		this.announcementList = announcementList;
	}
}

