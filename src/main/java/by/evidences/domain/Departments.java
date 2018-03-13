package by.evidences.domain;

import by.evidences.dao.Identified;

public class Departments implements Identified<Integer> {

	private Integer departmentsId = null;
	private String department;
	private Integer location;

	public Departments() {
		
	}
	
	public Departments(Integer departmentsId, String department, Integer location) {
		super();
        this.departmentsId = departmentsId;
		this.department = department;
		this.location = location;
	}
	
	public Integer getId() {
		return departmentsId;
	}
	
	public void setId(Integer departmentsId) {
		this.departmentsId = departmentsId;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Integer getLocation() {
		return location;
	}
	
	public void setLocation(Integer location) {
		this.location = location;
	}
}
