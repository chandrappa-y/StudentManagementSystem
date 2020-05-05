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

import com.csye6625.courseservice.datamodel.Student;
import com.csye6625.courseservice.services.StudentService;

@Path("students")
public class StudentResource {

	StudentService service = new StudentService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByDepartment(@QueryParam("department") String department) {
		if (department == null)
			return service.getAllStudents();
		return service.getStudentsByDepartment(department);
	}

	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentById(@PathParam("studentId") long studentId) {
		System.out.println("Looking for student - " + studentId);
		try {

			return Response.status(Response.Status.OK).entity(service.getStudentById(studentId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStudent(@PathParam("studentId") long studentId) {
		try {

			return Response.status(Response.Status.OK).entity(service.deleteStudent(studentId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student s) {
		return service.addStudent(s.getFirstName(), s.getLastName(), s.getDepartment());
	}

	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudentId(@PathParam("studentId") long studentId, Student student) {
		try {

			return Response.status(Response.Status.OK).entity(service.updateStudentId(studentId, student)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
