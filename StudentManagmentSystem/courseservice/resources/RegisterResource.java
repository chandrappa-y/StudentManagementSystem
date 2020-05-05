package com.csye6625.courseservice.resources;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6625.courseservice.datamodel.Register;
import com.csye6625.courseservice.services.RegisterService;

@Path("register")
public class RegisterResource {

	RegisterService service = new RegisterService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentsRegisteredForCourse(@QueryParam("courseId") long courseId) {
		try {

			return Response.status(Response.Status.OK).entity(service.getStudentsRegisteredForCourse(courseId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerForCourse(Register register) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.registerForCourse(register.getStudentId(), register.getCourseId())).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dropCourse(Register register) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.dropCourse(register.getStudentId(), register.getCourseId())).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{studentId}")
	public Response getCoursesRegisteredByStudent(@PathParam("studentId") long studentId) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.getCoursesRegisteredByStudent(studentId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
