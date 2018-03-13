package by.evidences.domain;

import by.evidences.dao.Identified;

public class Districts implements Identified<Integer> {

	private Integer districtsId = null;
	private String district;
	private Integer localId;

	public Districts() {
		
	}
	
	public Districts(Integer districtsId, String district, Integer localId) {
		super();
        this.districtsId = districtsId;
		this.district = district;
		this.localId = localId;
	}
	
	public Integer getId() {
		return districtsId;
	}
	
	public void setId(Integer districtsId) {
		this.districtsId = districtsId;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public Integer getLocalId() {
		return localId;
	}
	
	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	@Override
	public String toString() {
		return "Districts [districtsId=" + districtsId + ", district=" + district + ", localId=" + localId + "]";
	}
}
