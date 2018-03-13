package by.evidences.mysql;

import by.evidences.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.*;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    
    private Properties getProperties() throws PersistException {
    	Properties prop = null;
    	try {
    		prop = new Properties();
    		prop.load(getClass().getClassLoader()
    				.getResourceAsStream("prop.properties"));
        } catch (IOException e) {
        	throw new PersistException("Error: can't get properties file!", e);
        }
            return prop;
    }
    
    private Connection createConnection() throws PersistException {
    	Connection connection = null;
    	try {
    	    connection = DriverManager.getConnection(
    	    		getProperties().getProperty("DBUrl"),
    	    		getProperties().getProperty("user"),
    	    		getProperties().getProperty("password"));
        } catch (SQLException e) {
            throw new PersistException("Error: connection is not avaible now,"
            		+ "can't get access! MySQLDaoFactory.createConnection()", e);
        }
       return connection;
    }

    @Override
    public MySqlBlogDao getBlogDao() throws PersistException {
        return new MySqlBlogDao(createConnection());
    }
    
    @Override
    public MySqlContactdataDao getContactDataDao() throws PersistException {
        return new MySqlContactdataDao(createConnection());
    }
    
    @Override
    public MySqlDepartmentsDao getDepartmentsDao() throws PersistException {
        return new MySqlDepartmentsDao(createConnection());
    }
    
    @Override
    public MySqlDistrictsDao getDistrictsDao() throws PersistException {
        return new MySqlDistrictsDao(createConnection());
    }
    
    @Override
    public MySqlFilesDao getFilesDao() throws PersistException {
        return new MySqlFilesDao(createConnection());
    }
    
    @Override
    public MySqlLocalitiesDao getLocalitiesDao() throws PersistException {
        return new MySqlLocalitiesDao(createConnection());
    }
    
    @Override
    public MySqlUsersDao getUsersDao() throws PersistException {
        return new MySqlUsersDao(createConnection());
    }
    

    public MySqlDaoFactory() throws PersistException {
    	try {
            Class.forName(getProperties().getProperty("driver"));
        } catch (ClassNotFoundException e) {
        	throw new PersistException("Error: can't register driver!", e);
        }
    }
}
