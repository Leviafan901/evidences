package by.evidences.dao;

import java.io.Serializable;

/**
 * 
 * @author a s u s
 * Interface identifieding objects
 */
public interface Identified<PK extends Serializable> {

	/** Return identificator of object */
    public PK getId();

	public void setId(PK key);
}
