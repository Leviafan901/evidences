package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Departments;

import java.util.List;
import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlDepartmentsDao extends AbstractJDBCDao<Departments, Integer> {

	 @Override
	    public String getSelectQuery() {
	        return "SELECT id, department, localities_id FROM Departments";
	    }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, department, localities_id FROM Departments WHERE id = ?;";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO Departments (id, department, localities_id) VALUES (?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE Departments SET id = ? department = ? localities_id = ? WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM Departments WHERE id = ?;";
	    }

	    @Override
	    public Departments create() throws PersistException {
	    	Departments newDep = new Departments();
	        return persist(newDep);
	    }

	    public MySqlDepartmentsDao(Connection connection) throws PersistException {
	        super(connection);
	    }

	    @Override
	    protected List<Departments> parseResultSet(ResultSet rs) throws PersistException {
	        LinkedList<Departments> result = new LinkedList<Departments>();
	        try {
	            while (rs.next()) {
	                Departments newDep = new Departments();
	                newDep.setId(rs.getInt("id"));
	                newDep.setDepartment(rs.getString("department"));
	                newDep.setLocation(rs.getInt("localities_id"));
	                result.add(newDep);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		Departments object) throws PersistException {
	        try {
	            statement.setInt(1, object.getId());
	            statement.setString(2, object.getDepartment());
	            statement.setInt(3, object.getLocation());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		Departments object) throws PersistException {
	        try {
	        	statement.setInt(1, object.getId());
	        	statement.setString(2, object.getDepartment());
	            statement.setInt(3, object.getLocation());
	            statement.setInt(4, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
}
