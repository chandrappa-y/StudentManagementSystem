package com.board;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.board.datamodel.Board;
import com.board.service.BoardService;

public class BoardLambda implements RequestHandler<Board, Long> {

    @Override
    public Long handleRequest(Board input, Context context) {
        
        BoardService service = new BoardService();
        long boardId = service.addBoard(); 
        return boardId;
    }

}
