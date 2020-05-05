package com.csye6625.courseservice.resources;

import java.util.List;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6625.courseservice.datamodel.Course;
import com.csye6625.courseservice.datamodel.Professor;
import com.csye6625.courseservice.services.CourseService;

@Path("courses")
public class CourseResource {

	CourseService service = new CourseService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses(@QueryParam("department") String department) {
		if (department == null)
			return service.getAllCourses();
		return service.getCourseByDepartment(department);

	}

	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourseById(@PathParam("courseId") Long courseId) {
		try {

			return Response.status(Response.Status.OK).entity(service.getCourse(courseId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		try {

			return Response.status(Response.Status.OK).entity(service.addCourse(course.getCourseId(),
					course.getCourseName(), course.getMaximumClassSize(), course.getDepartment())).build();

		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(@PathParam("courseId") long courseId) {
		try {

			return Response.status(Response.Status.OK).entity(service.deleteCourse(courseId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourseById(@PathParam("courseId") long newCourseId, Course course) {
		try {

			return Response.status(Response.Status.OK).entity(service.updateCourseId(newCourseId, course)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@Path("/{courseId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignProfessor(@PathParam("courseId") long courseId, Professor professor) {
		try {

			return Response.status(Response.Status.OK).entity(service.assignProfessor(courseId, professor)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
