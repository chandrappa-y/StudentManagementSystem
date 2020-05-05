package com.validate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ValidateInput implements RequestHandler<Validate, Boolean> {

    @Override
    public Boolean handleRequest(Validate input, Context context) {
        context.getLogger().log("Input: " + input);
        if(input.getBoardId()==0 && input.getListOfRegisteredStudents()==null && !input.getDepartment().equalsIgnoreCase("seminars")
        		&& input.getNotificationTopic()==null)
        	return true;
        return false;
    }

}
