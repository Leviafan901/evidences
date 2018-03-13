package by.evidences.domain;

import by.evidences.dao.Identified;

public class Localities implements Identified<Integer> {

	private Integer localitiesId = null;
	private String locality;
	private boolean districtAv;

	public Localities() {
		
	}
	
	public Localities(Integer localitiesId, String locality, boolean districtAv) {
		super();
        this.localitiesId = localitiesId;
		this.locality = locality;
		this.districtAv = districtAv;
	}
	
	public Integer getId() {
		return localitiesId;
	}
	
	public void setId(Integer localitiesId) {
		this.localitiesId = localitiesId;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public boolean getDistrictAv() {
		return districtAv;
	}
	
	public void setDistrictAv(boolean districtAv) {
		this.districtAv = districtAv;
	}

	@Override
	public String toString() {
		return "Localities [localitiesId=" + localitiesId + ", locality=" + locality + ", districtAv=" + districtAv
				+ "]";
	}
}
