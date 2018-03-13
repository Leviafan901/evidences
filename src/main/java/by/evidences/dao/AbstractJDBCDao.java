package by.evidences.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

	private final static Logger logger = Logger.getLogger(AbstractJDBCDao.class);
	
    protected Connection connection;//getConnection
    private PreparedStatement selectStat = null;
    private PreparedStatement createStat = null;
    private PreparedStatement deleteStat = null;
    private PreparedStatement queryByPKStat = null;
    private PreparedStatement updateStat = null;
    
    public AbstractJDBCDao(Connection connection) throws PersistException {
        this.connection = connection;
    }

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();
    
    /**
     * Возвращает sql запрос для получения всех записей по ключу.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getQueryByPK();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    @Override
    public T persist(T object) throws PersistException {
    	ResultSet rs = null;
        try {
        	if(this.createStat == null) {
        	    this.createStat = connection.prepareStatement(getCreateQuery(), PreparedStatement.RETURN_GENERATED_KEYS);
        	}
            prepareStatementForInsert(createStat, object);
            int count = createStat.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
        	rs = createStat.getGeneratedKeys();
            Integer key = null;
            if (rs.next()) {
            	key = rs.getInt(1);
            }
            object.setId((PK) key);
                
        } catch (Exception e) {
            throw new PersistException("Can't persist data!", e);
        } finally {
        	if (rs != null) {
        		try {
        			rs.close();
        		} catch (Exception e) {
        			logger.warn(e);
        		}
             }
        }
        return object;
    }

    @Override
    public T getByPK(Integer key) throws PersistException {
        List<T> list;
        ResultSet rs = null;
        try {
        	if (this.queryByPKStat == null) {
        		this.queryByPKStat = connection.prepareStatement(getQueryByPK());
        	}
            queryByPKStat.setInt(1, key);
            rs = queryByPKStat.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
        	if (rs != null) {
        		try {
        			rs.close();
        		} catch (Exception e) {
        			logger.warn(e);
        		}
             }
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        try {
        	if (this.updateStat == null) {
        		this.updateStat = connection.prepareStatement(getUpdateQuery());
        	}
            prepareStatementForUpdate(updateStat, object);
            int count = updateStat.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException("Can not updatedata from the DB!", e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        try {
        	if (this.deleteStat == null) {
        		this.deleteStat = connection.prepareStatement(getDeleteQuery());
        	}
            deleteStat.setObject(1, object.getId());

            int count = deleteStat.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException("Can not delete data from the DB!", e);
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        ResultSet rs  = null;
        try {
            if (this.selectStat == null) {
            	this.selectStat = connection.prepareStatement(getSelectQuery());
            }
            rs = selectStat.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException("Error: can't get all rows"
            		+ " from DB! Object.getAll()", e);
        } finally {
        	if (rs != null) {
        		try {
        			rs.close();
        		} catch (Exception e) {
        			logger.warn(e);
        		}
             }
        }
        return list;
    }
    
    @Override
    public void close() throws PersistException {
    	List<PreparedStatement> listStat = new ArrayList<>();
    	listStat.add(this.createStat);
    	listStat.add(this.selectStat);
    	listStat.add(this.deleteStat);
    	listStat.add(this.updateStat);
    	listStat.add(this.queryByPKStat);
    	
    	PreparedStatement stat;
    	Exception e = null;
    	for (Iterator<PreparedStatement> iter = listStat.iterator();iter.hasNext(); ) {
    	    stat = iter.next();
    	    if (stat != null) {
    		    try {
    			    stat.close();
    		        } catch (Exception exp) {
    		    	    e = exp;
    	            }
    	        }
    	    }
    	if(connection != null) {
    		try {
    			connection.close();
            } catch (Exception exception){
           	    throw new PersistException("Error:can't close connection!", exception);
            } 
        }
    	if (e != null) {
		     throw new PersistException(e);
    	}
    }
}
