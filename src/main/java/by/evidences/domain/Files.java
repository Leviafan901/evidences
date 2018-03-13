package by.evidences.domain;

import by.evidences.dao.Identified;

import java.util.Arrays;
import java.util.Date;
import java.time.*;

public class Files implements Identified<Integer> {

	private Integer fileId;
	private byte[] file;
	private String description;
	private Integer depId;
	private LocalDate date;
	private Integer idUser;
	private String filePath;

	public Files() {
		
	}
	
	public Files(byte[] file, String description, Integer depId, LocalDate date,
			Integer idUser) {
		super();
		this.file = file;
		this.description = description;
		this.depId = depId;
		this.date = date;
		this.idUser = idUser;
	}
	
	public Files(String filePath, String description, Integer depId, LocalDate date,
			Integer idUser) {
		super();
		this.filePath = filePath;
		this.description = description;
		this.depId = depId;
		this.date = date;
		this.idUser = idUser;
	}
	
	public Integer getId() {
		return fileId;
	}
	
	public void setId(Integer fileId) {
		this.fileId = fileId;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	public LocalDate getDate() {
		LocalDate date = LocalDate.now();
		return date;
	}
	
	public void setDate(Date date) {
		this.date = Instant.ofEpochMilli(date.getTime()).
				atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", file=" + Arrays.toString(file) + ", description=" + description
				+ ", depId=" + depId + ", date=" + date + ", idUser=" + idUser + ", filePath=" + filePath + "]";
	}
}
