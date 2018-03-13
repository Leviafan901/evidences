package by.evidences.domain;

import by.evidences.dao.Identified;

public class Users implements Identified<Integer> {

	private Integer userId = null;
	private String login;
	private String passCache;
	private String access;
	private Integer forDep;

	public Users() {
		
	}
	
	public Users(String login, String passCache, String access, Integer forDep) {
		super();

		this.login = login;
		this.passCache = passCache;
		this.access = access;
		this.forDep = forDep;
	}
	
	public Integer getId() {
		return userId;
	}
	
	public void setId(Integer userId) {
		this.userId = userId;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassCache() {
		return passCache;
	}
	
	public void setPassCache(String passCache) {
		this.passCache = passCache;
	}
	
	public String getAccess() {
		return access;
	}
	
	public void setAccess(String access) {
		this.access = access;
	}
	
	public Integer getDep() {
		return forDep;
	}

	public void setDep(Integer forDep) {
		this.forDep = forDep;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", login=" + login + ", passCache=" + passCache + ", access=" + access
				+ ", forDep=" + forDep + "]";
	}
}
