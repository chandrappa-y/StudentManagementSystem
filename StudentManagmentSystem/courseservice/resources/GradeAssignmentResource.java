package com.csye6625.courseservice.resources;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6625.courseservice.datamodel.GradeAssignment;
import com.csye6625.courseservice.services.GradeAssignmentService;

@Path("grade")
public class GradeAssignmentResource {

	GradeAssignmentService service = new GradeAssignmentService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{studentId}")
	public Response getGradeOfAllAssignments(@PathParam("studentId") long studentId) {
		try {

			return Response.status(Response.Status.OK).entity(service.getGradeOfAllAssignments(studentId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getGrade(GradeAssignment grade) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.getGrade(grade.getAssignmentId(), grade.getStudentId())).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gradeAssignment(GradeAssignment grade) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.gradeAssignment(grade.getAssignmentId(), grade.getStudentId(), grade.getScore()))
					.build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGrade(GradeAssignment grade) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.updateGrade(grade.getAssignmentId(), grade.getStudentId(), grade.getScore()))
					.build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{gradeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAssignment(@PathParam("gradeId") long gradeId) {
		try {

			return Response.status(Response.Status.OK).entity(service.deleteGrade(gradeId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
