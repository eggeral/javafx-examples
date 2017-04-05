package egger.software.baylandtag.ax_unit_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MysqlDatabase implements Database {

	private static boolean driverLoaded = false;
	private BooleanProperty connected = new SimpleBooleanProperty(false);
	private StringProperty url = new SimpleStringProperty();
	private StringProperty user = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private Connection connection;

	@Override
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

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#disconnect()
	 */
	@Override
	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
			setConnected(false);
		} catch (SQLException e) {
			throw new DatabaseException("Could not close the database: " + getUrl() + ", user:" + getUser(), e);
		}

	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#isConnected()
	 */
	@Override
	public boolean isConnected() {
		return connected.get();
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#setConnected(boolean)
	 */
	@Override
	public void setConnected(boolean value) {
		this.connected.set(value);
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#connectedProperty()
	 */
	@Override
	public BooleanProperty connectedProperty() {
		return connected;
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#getUrl()
	 */
	@Override
	public String getUrl() {
		return url.get();
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String value) {
		url.set(value);
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#urlProperty()
	 */
	@Override
	public StringProperty urlProperty() {
		return url;
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#getUser()
	 */
	@Override
	public String getUser() {
		return user.get();
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#setUser(java.lang.String)
	 */
	@Override
	public void setUser(String value) {
		user.set(value);
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#userProperty()
	 */
	@Override
	public StringProperty userProperty() {
		return user;
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#getPassword()
	 */
	@Override
	public String getPassword() {
		return password.get();
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String value) {
		password.set(value);
	}

	/* (non-Javadoc)
	 * @see egger.software.baylandtag.ax_unit_test.IDatabase#passwordProperty()
	 */
	@Override
	public StringProperty passwordProperty() {
		return password;
	}

}
