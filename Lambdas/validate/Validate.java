package com.validate;

import java.util.ArrayList;
import java.util.List;

public class Validate {
	
		private long boardId;
		private long courseId;
		private long professorId;
		private long taId;
		private String department;
		private List<Long> listOfRegisteredStudents;
		private String notificationTopic;

		public Validate() {}

		public Validate(long boardId, long courseId,String department,long professorId,long taId, List<Long> listOfRegisteredStudents,String notificationTopic) {
			this.boardId=boardId;
			this.courseId=courseId;
			this.department=department;
			this.professorId=professorId;
			this.taId=taId;
			this.listOfRegisteredStudents=listOfRegisteredStudents;
			this.notificationTopic=notificationTopic;
		}
		
		public long getBoardId() {
			return this.boardId;
		}

		public void setBoardId(long boardId) {
			this.boardId = boardId;
		}
		
		public long getCourseId() {
			return this.courseId;
		}

		public void setCourseId(long courseId) {
			this.courseId = courseId;
		}
		
		public long getProfessorId() {
			return this.boardId;
		}

		public void setProfessorId(long professorId) {
			this.professorId = professorId;
		}
		
		public long getTaId() {
			return this.taId;
		}

		public void setTaId(long taId) {
			this.taId = taId;
		}
		
		public String getDepartment() {
			return this.department;
		}

		public String setDepartment(String department) {
			return this.department = department;
		}
		
		public String getNotificationTopic() {
			return this.notificationTopic;
		}

		public String setNotificationTopic(String notificationTopic) {
			return this.notificationTopic = notificationTopic;
		}
		
		
		public List<Long> getListOfRegisteredStudents() {
			return this.listOfRegisteredStudents;
		}

		public void setListOfRegisteredStudents(List<Long> listOfRegisteredStudents) {
			this.listOfRegisteredStudents = listOfRegisteredStudents;
		}

}
