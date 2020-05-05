package com.student.course;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.student.course.datamodel.Course;
import com.student.course.service.CourseService;
import com.student.course.service.ProfessorService;

public class CourseLambda implements RequestHandler<Course, Course> {

	CourseService service = new CourseService();

	String topicARN = "arn:aws:sns:us-west-2:349599946918:NotifyProfessor";

	@Override
	public Course handleRequest(Course input, Context context) {

		context.getLogger().log("Input: " + input);
		Course course = new Course();

		try {
			course = service.addCourse(input.getCourseId(), input.getDepartment(), input.getProfessorId(),
					input.getBoardId(), input.getTaId(), input.getListOfRegisteredStudents(), topicARN);
		} catch (RuntimeException e1) {
			throw new CourseExist("Course exist");
		}

		AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.US_WEST_2)
				.withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).build();

		ProfessorService professorService = new ProfessorService();

		String emailId;

		try {
			emailId = professorService.getProfessorEmailId(input.getProfessorId());
			final SubscribeRequest subscribeRequest = new SubscribeRequest(topicARN, "email", emailId);
			snsClient.subscribe(subscribeRequest);
		} catch (RuntimeException e) {
			throw e;
		}

		return course;
	}

}
