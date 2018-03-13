package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Users;

import java.util.List;
import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlUsersDao extends AbstractJDBCDao<Users, Integer> {

	private class PersistUser extends Users {
        public void setId(int userId) {
            super.setId(userId);
        }
    }
	
	 @Override
	    public String getSelectQuery() {
	        return "SELECT id, login, password_cache, access, departments_id"
	        		+ " FROM evidences.Users";
	    }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, login, password_cache, access, departments_id"
	        		+ " FROM evidences.Users WHERE id = ?;";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO evidences.Users (login, password_cache, access,"
	        		+ " departments_id) VALUES (?, ?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE evidences.Users SET login = ?"
	        		+ " password_cache = ? access = ? departments_id = ?"
	        		+ " WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM evidences.Users WHERE id = ?;";
	    }

	    @Override
	    public Users create() throws PersistException {
	        Users newUser = new Users();
	        return persist(newUser);
	    }

	    public MySqlUsersDao(Connection connection) throws PersistException {
	        super(connection);
	    }

	    @Override
	    protected List<Users> parseResultSet(ResultSet rs) throws PersistException {
	        LinkedList<Users> result = new LinkedList<Users>();
	        try {
	            while (rs.next()) {
	                PersistUser newUser = (PersistUser) new Users();
	                newUser.setId(rs.getInt("id"));
	                newUser.setLogin(rs.getString("login"));
	                newUser.setPassCache(rs.getString("password_cache"));
	                newUser.setAccess(rs.getString("access"));
	                newUser.setDep(rs.getInt("departments_id"));
	                result.add(newUser);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		Users object) throws PersistException {
	        try {
	            statement.setString(1, object.getLogin());
	            statement.setString(2, object.getPassCache());
	            statement.setString(3, object.getAccess());
	            statement.setInt(4, object.getDep());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		Users object) throws PersistException {
	        try {
	        	statement.setString(1, object.getLogin());
	            statement.setString(2, object.getPassCache());
	            statement.setString(3, object.getAccess());
	            statement.setInt(4, object.getDep());
	            statement.setInt(5, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
}
