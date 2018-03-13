package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.ContactData;

import java.util.List;
import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlContactdataDao extends AbstractJDBCDao<ContactData, Integer> {
	
	 @Override
	    public String getSelectQuery() {
	        return "SELECT id, first_name, last_name, email, tel_num,"
	        		+ " user_id, file_id FROM Contact_Data";
	    }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, first_name, last_name, email, tel_num,"
	        		+ " user_id, file_id FROM Contact_Data"
	        		+ " WHERE id = ?;";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO Contact_Data (first_name, last_name, email, tel_num,"
	        		+ " user_id, file_id) VALUES (?, ?, ?, ?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE Contact_Data SET first_name = ?"
	        		+ " last_name = ? email = ? tel_num = ? user_id = ?"
	        		+ " file_id = ? WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM Contact_Data WHERE id = ?;";
	    }

	    @Override
	    public ContactData create() throws PersistException {
	        ContactData conData = new ContactData();
	        return persist(conData);
	    }

	    public MySqlContactdataDao(Connection connection) throws PersistException {
	        super(connection);
	    }

	    @Override
	    protected List<ContactData> parseResultSet(ResultSet rs) throws PersistException {
	        LinkedList<ContactData> result = new LinkedList<ContactData>();
	        try {
	            while (rs.next()) {
	                ContactData conData = new ContactData();
	                conData.setId(rs.getInt("id"));
	                conData.setFirstName(rs.getString("first_name"));
	                conData.setLastName(rs.getString("last_name"));
	                conData.setEmail(rs.getString("email"));
	                conData.setTelNum(rs.getInt("tel_num"));
	                conData.setUserData(rs.getInt("user_id"));
	                conData.setFileData(rs.getInt("file_id"));
	                result.add(conData);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		ContactData object) throws PersistException {
	        try {
	            statement.setString(1, object.getFirstName());
	            statement.setString(2, object.getLastName());
	            statement.setString(3, object.getEmail());
	            statement.setInt(4, object.getTelNum());
	            statement.setInt(5, object.getUserData());
	            statement.setInt(6, object.getFileData());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		ContactData object) throws PersistException {
	        try {
	        	statement.setString(1, object.getFirstName());
	            statement.setString(2, object.getLastName());
	            statement.setString(3, object.getEmail());
	            statement.setInt(4, object.getTelNum());
	            statement.setInt(5, object.getUserData());
	            statement.setInt(6, object.getFileData());
	            statement.setInt(7, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
}
