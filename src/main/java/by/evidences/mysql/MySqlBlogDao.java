package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Blog;

import java.util.List;

import org.apache.log4j.Logger;

import by.evidences.dao.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MySqlBlogDao extends AbstractJDBCDao<Blog, Integer> {

	private final static Logger LOGGER = Logger.getLogger(AbstractJDBCDao.class);
    private PreparedStatement queryByAuthorIdStat = null;
    
    public MySqlBlogDao(Connection connection) throws PersistException {
        super(connection);
    }

	 @Override
	 public String getSelectQuery() {
	     return "SELECT id, blog_news, blog_title, author FROM Blog;";
	 }
	 
	 @Override
	    public String getQueryByPK() {
	        return "SELECT id, blog_news, blog_title, author FROM Blog"
	        		+ " WHERE id = ?;";
	    }
	 

	 public String getByAuthorId() {
	        return "SELECT id, blog_news, blog_title, author FROM Blog"
	        		+ " WHERE author = ?;";
	 }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO Blog (blog_news, blog_title, author) VALUES (?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE Blog SET blog_news = ? blog_title = ?"
	        		+ " author = ? WHERE id = ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM Blog WHERE id = ?;";
	    }

	    @Override
	    protected List<Blog> parseResultSet(ResultSet rs)
	    		throws PersistException {
	        LinkedList<Blog> result = new LinkedList<Blog>();
	        try {
	            while (rs.next()) {
	                Blog blog = new Blog();
	                blog.setId(rs.getInt("id"));
	                blog.setBlogNews(rs.getString("blog_news"));
	                blog.setBlogTitle(rs.getString("blog_title"));
	                blog.setAuthor(rs.getInt("author"));
	                result.add(blog);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement,
	    		Blog object) throws PersistException {
	        try {
	            statement.setString(1, object.getBlogTitle());
	            statement.setString(2, object.getBlogNews());
	            statement.setInt(3, object.getAuthor());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement,
	    		Blog object) throws PersistException {
	        try {
	            statement.setString(1, object.getBlogTitle());
	            statement.setString(2, object.getBlogNews());
	            statement.setInt(3, object.getAuthor());
	            statement.setInt(4, object.getId());
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	    }
	    

	    public List<Blog> getByAuthorId(Integer key) throws PersistException {
	        List<Blog> list;
	        ResultSet rs = null;
	        try {
	            if (this.queryByAuthorIdStat == null) {
	            	this.queryByAuthorIdStat = connection.prepareStatement(getByAuthorId());
	            }
	            queryByAuthorIdStat.setInt(1, key);
	            rs = queryByAuthorIdStat.executeQuery();
	            list = parseResultSet(rs);
	        } catch (Exception e) {
	            throw new PersistException("Error: can't get all rows"
	            		+ " from DB! Object.getAll()", e);
	        } finally {
	        	if (rs != null) {
	        		try {
	        			rs.close();
	        		} catch (Exception e) {
	        			LOGGER.warn(e);
	        		}
	             }
	        }

	        return list;
	    }
	    
	    @Override
	    public void close() throws PersistException {
	    	try {
	    		super.close();
	    	} catch (Exception e) {
	    		throw new PersistException("Not closed statement!", e);
	    	} finally {
	    		if (this.queryByAuthorIdStat != null) {
	    			try {
	    		        this.queryByAuthorIdStat.close();
	    			} catch (Exception e) {
	    				LOGGER.warn(e);
	    			}
	    		}
	    	}
	    }

		@Override
		public Blog create() throws PersistException {
			Blog blog = new Blog();
	        return persist(blog);
		}
}
