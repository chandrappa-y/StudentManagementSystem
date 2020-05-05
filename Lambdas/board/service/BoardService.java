package com.board.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.board.datamodel.Board;
import com.board.datamodel.DynamoDBConnector;

public class BoardService {
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public BoardService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public long addBoard() {
		//create Id
		long size = mapper.count(Board.class, new DynamoDBScanExpression());
		long nextAvailableId = size + 1 * 100;
		Board board = mapper.load(Board.class, nextAvailableId);
		if (board == null){
			board = new Board(nextAvailableId);
		}else {
			nextAvailableId +=1;
			board = new Board(nextAvailableId);
		}
		mapper.save(board);
		return board.getBoardId();
	}

}

