package com.register;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.register.datamodel.Register;

import com.register.service.RegisterService;

public class RegisterLambda implements RequestHandler<Register,String> {
	
	String topicARN = "arn:aws:sns:us-west-2:349599946918:NotifyProfessor";

    @Override
    public String handleRequest(Register input, Context context) {
        context.getLogger().log("Input: " + input);
        
        String result = "";
        
        RegisterService registerService = new RegisterService();
        registerService.registerCourse(input.getCourseId(), "course", input.getDepartment());
        
        String message = "Hi Professor, Your course "+input.getCourseId()+" has been registered";
        
        AmazonSNS snsClient = AmazonSNSClientBuilder
    			.standard().withRegion(Regions.US_WEST_2)
    			.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
    			.build();
        
        PublishRequest publishRequest = new PublishRequest(topicARN,message);
        PublishResult publishResult = snsClient.publish(publishRequest);
        
        result += publishResult.getMessageId();
        result += " Message has been sent! ";

        
        return result;
    }

}
