package rodeo.agile.impress.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZaikoDao {
	
	String dbPath;

	public ZaikoDao(String path) {
		this.dbPath = path;
	}

	public void insert(int id, int suryo) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			try(ResultSet rs = statement.executeQuery("SELECT 1 FROM items WHERE id = " + id + ";");)
			{
				if(!rs.next())
					throw new RuntimeException("items id = " + id + " not exist");
			}
			
			
			try(ResultSet rs = statement.executeQuery("SELECT 1 FROM zaiko WHERE id = " + id + ";");)
			{
				if(!rs.next())
					statement.execute("INSERT INTO zaiko (id, suryo) VALUES (" + id + ", " + suryo + ");");
				else
					statement.execute("UPDATE zaiko SET suryo = suryo + " + suryo + " WHERE id = " + id + ";");
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}

}
