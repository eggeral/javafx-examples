package egger.software.baylandtag.ai_style_and_refactor_the_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Database {

	private static boolean driverLoaded = false;
	private BooleanProperty connected = new SimpleBooleanProperty(false);
	private StringProperty url = new SimpleStringProperty();
	private StringProperty user = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private Connection connection;

	public void connect() {
		try {

			if (!driverLoaded) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				driverLoaded = true;
			}

			connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			setConnected(true);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new DatabaseException("Could not connect to the database: " + getUrl() + ", user:" + getUser(), e);
		}

	}

	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
			setConnected(false);
		} catch (SQLException e) {
			throw new DatabaseException("Could not close the database: " + getUrl() + ", user:" + getUser(), e);
		}

	}

	public List<Member> getAllMembers() {
	
		try {
			List<Member> members = new ArrayList<>();
			PreparedStatement statement = connection
					.prepareStatement("SELECT id, Name, Vorname, Titel FROM abgeordneter ORDER BY Name");

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String surname = resultSet.getString("Name");
				String forename = resultSet.getString("Vorname");
				String title = resultSet.getString("Titel");
				members.add(new Member(id, surname, forename, title));
			}

			return members;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get members from database", e);
		}
	}

	public boolean isConnected() {
		return connected.get();
	}

	public void setConnected(boolean value) {
		this.connected.set(value);
	}

	public BooleanProperty connectedProperty() {
		return connected;
	}

	public String getUrl() {
		return url.get();
	}

	public void setUrl(String value) {
		url.set(value);
	}

	public StringProperty urlProperty() {
		return url;
	}

	public String getUser() {
		return user.get();
	}

	public void setUser(String value) {
		user.set(value);
	}

	public StringProperty userProperty() {
		return user;
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String value) {
		password.set(value);
	}

	public StringProperty passwordProperty() {
		return password;
	}

}
