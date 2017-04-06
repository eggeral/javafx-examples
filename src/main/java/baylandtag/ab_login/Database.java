package baylandtag.ab_login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static boolean driverLoaded = false;
	private boolean connected;

	private String url;
	private String user;
	private String password;
	private Connection connection;

	public void connect() {
		try {
			if (!driverLoaded) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				driverLoaded = true;
			}

			connection = DriverManager.getConnection(url, user,  password);
			connected = true;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new DatabaseException("Could not connect to the database: " + url + ", user:" + user, e);
		}

	}

	public boolean isConnected() {
		return connected;
	}

	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			throw new DatabaseException("Could not close the database: " + url + ", user:" + user, e);		
		}
			
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
