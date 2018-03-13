package by.evidences.mysql;

import by.evidences.dao.AbstractJDBCDao;
import by.evidences.domain.Files;
import java.util.List;
import by.evidences.dao.PersistException;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MySqlFilesDao extends AbstractJDBCDao<Files, Integer> {

	private class PersistFile extends Files {
		public void setId(int fileId) {
			super.setId(fileId);
		}
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, file, description, departments_id, date," + " user_id FROM evidences.Files";
	}

	@Override
	public String getQueryByPK() {
		return "SELECT id, file, description, departments_id, date," + " user_id FROM evidences.Files WHERE id = ?;";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO evidences.Files (file, description, departments_id, date, user_id) VALUES (?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE evidences.Files SET file = ?" + " description = ? departments_id = ? date = ? user_id = ? WHERE id = ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM evidences.Files WHERE id = ?;";
	}

	@Override
	public Files create() throws PersistException {
		Files newFile = new Files();
		return persist(newFile);
	}

	public MySqlFilesDao(Connection connection) throws PersistException {
		super(connection);
	}

	@Override
	protected List<Files> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Files> result = new LinkedList<Files>();
		try {
			while (rs.next()) {
				PersistFile newFile = (PersistFile) new Files();
				newFile.setId(rs.getInt("id"));
				Blob fileBlob = rs.getBlob("file");
				newFile.setFile(fileBlob.getBytes(1, (int) fileBlob.length()));
				newFile.setDescription(rs.getString("description"));
				newFile.setDepId(rs.getInt("departments_id"));
				newFile.setDate(rs.getDate("date"));
				newFile.setIdUser(rs.getInt("user_id"));
				result.add(newFile);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Files object) throws PersistException {
		try {
			InputStream inputStream = new FileInputStream(new File(object.getFilePath()));
			statement.setBlob(1, inputStream);
			statement.setString(2, object.getDescription());
			statement.setInt(3, object.getDepId());
			statement.setDate(4, java.sql.Date.valueOf(object.getDate()));
			statement.setInt(5, object.getIdUser());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Files object) throws PersistException {
		try {
			InputStream inputStream = new FileInputStream(new File(object.getFilePath()));
			statement.setBlob(1, inputStream);
			statement.setString(2, object.getDescription());
			statement.setInt(3, object.getDepId());
			statement.setDate(4, java.sql.Date.valueOf(object.getDate()));
			statement.setInt(5, object.getIdUser());
			statement.setInt(6, object.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
}
