package baylandtag.at_combo_box;

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


	public void addMaritalStatus(MaritalStatus status) {

		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO familienstand SET Bezeichnung = ?")) {

			statement.setString(1, status.getName());
			statement.executeUpdate();

			ResultSet idResultSet = statement.getGeneratedKeys();
			if (idResultSet.next())
				status.setId(idResultSet.getInt(1));
			else
				throw new DatabaseException("Could not add marital status " + status + " into database");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Could not add marital status " + status + " into database", e);
		}

	}

	
	public void addMember(Member member) {

		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO abgeordneter SET Name = ?, Vorname = ?, Titel = ?, k_id = ?, f_id = ?")) {

			statement.setString(1, member.getSurname());
			statement.setString(2, member.getForename());
			statement.setString(3, member.getTitle());
			statement.setInt(4, member.getConfession().getId());
			statement.setInt(5, member.getMaritalStatus().getId());

			statement.executeUpdate();

			ResultSet idResultSet = statement.getGeneratedKeys();
			if (idResultSet.next())
				member.setId(idResultSet.getInt(1));
			else
				throw new DatabaseException("Could not add member " + member + " into database");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Could not add member " + member + " into database", e);
		}

	}

	public void updateMember(Member member) {

		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE abgeordneter SET Name = ?, Vorname = ?, Titel = ?, k_id = ?, f_id = ? WHERE id = ?")) {

			statement.setString(1, member.getSurname());
			statement.setString(2, member.getForename());
			statement.setString(3, member.getTitle());
			statement.setInt(4, member.getConfession().getId());
			statement.setInt(5, member.getMaritalStatus().getId());
			statement.setInt(6, member.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Could not update member " + member, e);
		}

	}

	public void deleteMember(Member member) {

		try (PreparedStatement statement = connection.prepareStatement("DELETE FROM abgeordneter WHERE id = ?")) {

			statement.setInt(1, member.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Could not delete member " + member + ".", e);
		}

	}

	public List<Member> getAllMembers() {

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Name, Vorname, Titel, k_id, f_id FROM abgeordneter ORDER BY Name")) {

			List<Member> members = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				members.add(createMember(resultSet));
			}

			return members;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get members from database", e);
		}

	}

	public List<Confession> getAllConfessions() {

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Bezeichnung FROM konfession ORDER BY Bezeichnung")) {

			List<Confession> confessions = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				confessions.add(createConfession(resultSet));
			}

			return confessions;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get confessions from database", e);
		}

	}

	public List<MaritalStatus> getAllMaritalStatus() {

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Bezeichnung FROM familienstand ORDER BY Bezeichnung")) {

			List<MaritalStatus> maritalStatus = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maritalStatus.add(createMaritalStatus(resultSet));
			}

			return maritalStatus;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get marital status from database", e);
		}

	}
	
	public List<Party> getAllParties() {

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Name, Bezeichnung FROM partei ORDER BY Name")) {

			List<Party> parties = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("Name");
				String description = resultSet.getString("Bezeichnung");
				parties.add(new Party(id, name, description));
			}

			return parties;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get parties from database", e);
		}
	}

	public List<Member> getMembersOfParty(Party party) {

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT DISTINCT(a.id), a.Name, a.Vorname, a.Titel, a.k_id, a.f_id from abgeordneter a "
						+ "LEFT JOIN zt_partei_abg z ON a.id = z.a_id LEFT JOIN partei p ON p.id = z.p_id "
						+ "WHERE p.id=? ORDER BY Name")) {

			statement.setInt(1, party.getId());
			List<Member> members = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				members.add(createMember(resultSet));
			}

			return members;
		} catch (SQLException e) {
			throw new DatabaseException("Could not get members for: " + party + " from database", e);
		}
	}

	public Confession getConfession(int id) {
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Bezeichnung FROM konfession WHERE id = ?;")) {

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new DatabaseException("Confession for id:" + id + " not found");

			Confession confession = createConfession(resultSet);

			if (resultSet.next())
				throw new DatabaseException("More than one confession found for id:" + id);

			return confession;

		} catch (SQLException e) {
			throw new DatabaseException("Could not get confession for id: " + id + " from database", e);
		}

	}


	public MaritalStatus getMaritalStatus(int id) {
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT id, Bezeichnung FROM familienstand WHERE id = ?;")) {

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new DatabaseException("Marital status for id:" + id + " not found");

			MaritalStatus maritalStatus = createMaritalStatus(resultSet);

			if (resultSet.next())
				throw new DatabaseException("More than one marital status found for id:" + id);

			return maritalStatus;

		} catch (SQLException e) {
			throw new DatabaseException("Could not get marital status for id: " + id + " from database", e);
		}

	}

	private Confession createConfession(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String name = resultSet.getString("Bezeichnung");
		
		return new Confession(id, name);

	}

	private MaritalStatus createMaritalStatus(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String name = resultSet.getString("Bezeichnung");
		
		return new MaritalStatus(id, name);

	}

	
	private Member createMember(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String surname = resultSet.getString("Name");
		String forename = resultSet.getString("Vorname");
		String title = resultSet.getString("Titel");
		int confessionId = resultSet.getInt("k_id");
		int maritalStatusId = resultSet.getInt("f_id");

		Confession confession = null;
		if (confessionId != 0)
			confession = getConfession(confessionId);

		MaritalStatus maritalStatus = null;
		if (maritalStatusId != 0)
			maritalStatus = getMaritalStatus(maritalStatusId);

		
		return new Member(id, surname, forename, title, confession, maritalStatus);

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
