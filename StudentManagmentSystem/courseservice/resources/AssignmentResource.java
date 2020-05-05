package com.csye6625.courseservice.resources;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6625.courseservice.datamodel.Assignment;
import com.csye6625.courseservice.services.AssignmentService;

@Path("assignments")
public class AssignmentResource {

	AssignmentService service = new AssignmentService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAssignment(Assignment assignment) {
		try {

			return Response.status(Response.Status.OK).entity(service.addAssignment(assignment.getCourseId(),
					assignment.getAssignmentName(), assignment.getAssignmentContent(), assignment.getMaxScore()))
					.build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAllAssignments() {
		return service.getAllAssignments();
	}

	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignmentOfCourse(@PathParam("courseId") long courseId) {
		try {

			return Response.status(Response.Status.OK).entity(service.getAssignmentsOfCourse(courseId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{assignmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAssignment(@PathParam("assignmentId") long assignmentId) {
		try {

			return Response.status(Response.Status.OK).entity(service.deleteAssignment(assignmentId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
