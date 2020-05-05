package com.csye6625.courseservice.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.BadRequestException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6625.courseservice.datamodel.DynamoDBConnector;
import com.csye6625.courseservice.datamodel.Professor;

public class ProfessorService {

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper;

	public ProfessorService() {
		dynamoDb = new DynamoDBConnector();
		DynamoDBConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Professor> getAllProfessors() {
		List<Professor> scanResult = mapper.scan(Professor.class, new DynamoDBScanExpression());
		return scanResult;
	}

	public Professor addProfessor(String firstName, String lastName, String department, Date joiningDate) {
		long size = mapper.count(Professor.class, new DynamoDBScanExpression());
		long nextAvailableId = size + 1;
		Professor duplicate = mapper.load(Professor.class, nextAvailableId);
		if (duplicate != null) {
			nextAvailableId++;
		}
		
		Professor professor = new Professor(nextAvailableId, firstName, lastName, department, joiningDate.toString());
		mapper.save(professor);
		return professor;
	}

	public Professor getProfessor(long professorId) {
		Professor professor = mapper.load(Professor.class, professorId);
		if (professor == null) {
			throw new BadRequestException("Professor Id doesn't exist");
		}
		return professor;
	}

	public Professor deleteProfessor(long professorId) {

		Professor professor = mapper.load(Professor.class, professorId);
		if (professor == null) {
			throw new BadRequestException("Professor Id doesn't exist");
		}
		mapper.delete(professor);
		return professor;
	}

	public Professor updateProfessorId(long professorId, Professor professor) {
		Professor prof = mapper.load(Professor.class, professor.getProfessorId());
		if (prof == null) {
			throw new BadRequestException("Professor Id doesn't exist");
		}
		prof.setProfessorId(professorId);
		mapper.delete(professor);
		mapper.save(prof);
		return prof;
	}

	public List<Professor> getProfessorsByDepartment(String department) {
		ArrayList<Professor> list = new ArrayList<>();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> scanResult = mapper.scan(Professor.class, scanExpression);

		for (Professor professor : scanResult) {
			if (professor.getDepartment().equals(department))
				list.add(professor);
		}
		return list;
	}

	public boolean professorExist(long professorId) {
		Professor p = mapper.load(Professor.class, professorId);
		if (p != null)
		{
			return true;
		}
		return false;
	}
}
