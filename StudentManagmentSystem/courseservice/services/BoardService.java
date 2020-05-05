package com.csye6625.courseservice.services;

import javax.ws.rs.BadRequestException;
import com.csye6625.courseservice.datamodel.Board;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;

public class BoardService {
	CourseService courseService = new CourseService();

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public BoardService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public Board addAnnouncement(Long courseId, String announcement) {
		if (!courseService.courseExist(courseId))
		{	
			throw new BadRequestException("Incorrect course ID");
		}
		Board board = mapper.load(Board.class, courseId);
		if (board == null)
		{
			board = new Board(courseId, announcement);
		}
		board.getAnnouncementList().add(announcement);
		mapper.save(board);
		return board;
	}

	public Board getAnnouncement(long courseId) {
		Board board = mapper.load(Board.class, courseId);
		if (board == null) {
			throw new BadRequestException("No announcement made for the course");
		}
		return board;
	}

}
