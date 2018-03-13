package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Localities;

import java.io.Serializable;
import java.util.List;
import by.evidences.dao.GenericDao;
import by.evidences.dao.Identified;
import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlLocalitiesDao extends AbstractJDBCDao<Localities, Integer> {
	
	private class PersistLocality extends Localities {
        public void setId(int locId) {
            super.setId(locId);
        }
    }
	
	 @Override
	    public String getSelectQuery() {
	        return "SELECT id, locality, district_avaible"
	        		+ " FROM evidences.Localities";
	    }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, locality, district_avaible"
	        		+ " FROM evidences.Localities WHERE id = ?;";
	    }

	 @Override
	 public String getCreateQuery() {
	     return "INSERT INTO evidences.Localities (id, locality, district_avaible)"
	        		+ " VALUES (?, ?, ?);";
	 }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE evidences.Localities SET id = ?"
	        		+ " locality = ? district_avaible = ? WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM evidences.Localities WHERE id = ?;";
	    }

	    @Override
	    public Localities create() throws PersistException {
	        Localities newLoc = new Localities();
	        return persist(newLoc);
	    }

	    public MySqlLocalitiesDao(Connection connection) throws PersistException {
	        super(connection);
	    }

	    @Override
	    protected List<Localities> parseResultSet(ResultSet rs) throws PersistException {
	        LinkedList<Localities> result = new LinkedList<Localities>();
	        try {
	            while (rs.next()) {
	                PersistLocality newLoc = (PersistLocality) new Localities();
	                newLoc.setId(rs.getInt("id"));
	                newLoc.setLocality(rs.getString("locality"));
	                newLoc.setDistrictAv(rs.getBoolean("district_avaible"));
	                result.add(newLoc);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		Localities object) throws PersistException {
	        try {
	            statement.setInt(1, object.getId());
	            statement.setString(2, object.getLocality());
	            statement.setBoolean(3, object.getDistrictAv());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		Localities object) throws PersistException {
	        try {
	        	statement.setInt(1, object.getId());
	        	statement.setString(2, object.getLocality());
	            statement.setBoolean(3, object.getDistrictAv());
	            statement.setInt(4, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
}
