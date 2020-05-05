package com.csye6625.courseservice.resources;

import java.util.Date;
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

import com.csye6625.courseservice.datamodel.Professor;
import com.csye6625.courseservice.services.ProfessorService;

@Path("professors")
public class ProfessorResource {

	ProfessorService service = new ProfessorService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorByDepartment(@QueryParam("department") String department) {
		if (department == null)
			return service.getAllProfessors();
		return service.getProfessorsByDepartment(department);
	}

	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessor(@PathParam("professorId") long professorId) {
		System.out.println("Looking for Professor - " + professorId);
		try {

			return Response.status(Response.Status.OK).entity(service.getProfessor(professorId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProfessor(@PathParam("professorId") long professorId) {
		try {

			return Response.status(Response.Status.OK).entity(service.deleteProfessor(professorId)).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor p) {
		try {

			return Response.status(Response.Status.OK)
					.entity(service.addProfessor(p.getFirstName(), p.getLastName(), p.getDepartment(), new Date()))
					.build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProfessorId(@PathParam("professorId") long professorId, Professor professor) {
		try {

			return Response.status(Response.Status.OK).entity(service.updateProfessorId(professorId, professor))
					.build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (InternalServerErrorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

}
