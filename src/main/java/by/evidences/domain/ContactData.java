package by.evidences.domain;

import by.evidences.dao.Identified;

public class ContactData implements Identified<Integer> {

	private Integer id = null;
	private String firstName;
	private String lastName;
	private String email;
	private Integer telNum;
	private Integer userData;
	private Integer fileData;

	public ContactData() {
		
	}
	
	public ContactData(String firstName, String lastName, String email,
			Integer telNum, Integer userData, Integer fileData) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telNum = telNum;
		this.userData = userData;
		this.fileData = fileData;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer dataId) {
		this.id = dataId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    public void setTelNum(Integer telNum) {
    	this.telNum = telNum;
    }
	
	public Integer getTelNum() {
		return telNum;
	}
	
	public Integer getUserData() {
		return userData;
	}

	public void setUserData(Integer userData) {
		this.userData = userData;
	}
	
	public Integer getFileData() {
		return fileData;
	}

	public void setFileData(Integer fileData) {
		this.fileData = fileData;
	}

	@Override
	public String toString() {
		return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", telNum=" + telNum + ", userData=" + userData + ", fileData=" + fileData + "]";
	}

}
