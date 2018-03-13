package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Districts;

import java.util.List;
import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlDistrictsDao extends AbstractJDBCDao<Districts, Integer> {

	private class PersistDistrict extends Districts {
        public void setId(int locId) {
            super.setId(locId);
        }
    }
	
	 @Override
	    public String getSelectQuery() {
	        return "SELECT id, district, localities_id"
	        		+ " FROM evidences.Districts";
	    }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, district, localities_id"
	        		+ " FROM evidences.Districts WHERE id = ?;";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO evidences.Districts (id, district, localities_id)"
	        		+ " VALUES (?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE evidences.Districts SET id = ?"
	        		+ " district = ? localities_id = ? WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM evidences.Districts WHERE id = ?;";
	    }

	    @Override
	    public Districts create() throws PersistException {
	    	Districts newDist = new Districts();
	        return persist(newDist);
	    }

	    public MySqlDistrictsDao(Connection connection) throws PersistException {
	        super(connection);
	    }

	    @Override
	    protected List<Districts> parseResultSet(ResultSet rs) throws PersistException {
	        LinkedList<Districts> result = new LinkedList<Districts>();
	        try {
	            while (rs.next()) {
	                PersistDistrict newDist = (PersistDistrict) new Districts();
	                newDist.setId(rs.getInt("id"));
	                newDist.setDistrict(rs.getString("district"));
	                newDist.setLocalId(rs.getInt("localities_id"));
	                result.add(newDist);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		Districts object) throws PersistException {
	        try {
	            statement.setInt(1, object.getId());
	            statement.setString(2, object.getDistrict());
	            statement.setInt(3, object.getLocalId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		Districts object) throws PersistException {
	        try {
	        	statement.setInt(1, object.getId());
	        	statement.setString(2, object.getDistrict());
	            statement.setInt(3, object.getLocalId());
	            statement.setInt(4, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
}
