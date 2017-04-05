package egger.software.baylandtag.ax_unit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// This is an integration test of controller and ui. Database is a dummy. 
// Note that we could also test ui and controller separate!
@RunWith(JavaFxJUnit4ClassRunner.class)
public class LoginSpec {

	public static class DummyDb implements Database {

		private BooleanProperty connected = new SimpleBooleanProperty(false);
		private StringProperty url = new SimpleStringProperty();
		private StringProperty user = new SimpleStringProperty();
		private StringProperty password = new SimpleStringProperty();

		@Override
		public void connect() {
			if (getUser().equals("root") && getPassword().equals("sys"))
				connected.set(true);
			else
				connected.set(false);
		}

		@Override
		public void disconnect() {
			connected.set(false);
		}

		@Override
		public boolean isConnected() {
			return connected.get();
		}

		@Override
		public void setConnected(boolean value) {
			connected.set(value);
		}

		@Override
		public BooleanProperty connectedProperty() {
			return connected;
		}

		@Override
		public String getUrl() {
			return url.get();
		}

		@Override
		public void setUrl(String value) {
			url.set(value);
		}

		@Override
		public StringProperty urlProperty() {
			return url;
		}

		@Override
		public String getUser() {
			return user.get();
		}

		@Override
		public void setUser(String value) {
			user.set(value);
		}

		@Override
		public StringProperty userProperty() {
			return user;
		}

		@Override
		public String getPassword() {
			return password.get();
		}

		@Override
		public void setPassword(String value) {
			password.set(value);
		}

		@Override
		public StringProperty passwordProperty() {
			return password;
		}

	}

	@Test
	public void theConnectedLabelInitiallyShowsNotConnected() {
		// given
		LoginUi targetUi = new LoginUi();
		targetUi.create();

		LoginController targetController = new LoginController(targetUi);
		targetController.initialize();

		DummyDb db = new DummyDb();
		targetController.setDatabase(db);

		// when
		String connectedLabelText = targetUi.getConnectedLabel().getText();

		// then
		assertThat(connectedLabelText, is("Not Connected"));
	}

	@Test
	public void urlUserAndPasswordTextFieldsAreBoundToTheirAccordingDbProperties() {
		// given
		LoginUi targetUi = new LoginUi();
		targetUi.create();

		LoginController targetController = new LoginController(targetUi);
		targetController.initialize();

		DummyDb db = new DummyDb();
		targetController.setDatabase(db);

		// when
		targetUi.getUsernameTextField().setText("user");
		targetUi.getPasswordTextField().setText("password");
		targetUi.getDatabaseUrlTextField().setText("url");

		// then
		assertThat(db.getPassword(), is("password"));
		assertThat(db.getUser(), is("user"));
		assertThat(db.getUrl(), is("url"));
	}

	@Test
	public void afterASuccessfulLoginTheConnectedLabelShouldShowConnected() {
		// given
		LoginUi targetUi = new LoginUi();
		targetUi.create();

		LoginController targetController = new LoginController(targetUi);
		targetController.initialize();

		DummyDb db = new DummyDb();
		targetController.setDatabase(db);

		targetUi.getUsernameTextField().setText("root");
		targetUi.getPasswordTextField().setText("sys");

		// when
		targetUi.getConnectButton().fire();

		// then
		assertThat(targetUi.getConnectedLabel().getText(), is("Connected"));

	}

	@Test
	public void afterAnUnsuccessfulLoginTheConnectedLabelShouldShowNotConnected() {
		// given
		LoginUi targetUi = new LoginUi();
		targetUi.create();

		LoginController targetController = new LoginController(targetUi);
		targetController.initialize();

		DummyDb db = new DummyDb();
		targetController.setDatabase(db);

		targetUi.getUsernameTextField().setText("root");
		targetUi.getPasswordTextField().setText("root");

		// when
		targetUi.getConnectButton().fire();

		// then
		assertThat(targetUi.getConnectedLabel().getText(), is("Not Connected"));

	}

	@Test
	public void afterDisconnectingTheConnectedLabelShouldShowNotConnected() {
		// given
		LoginUi targetUi = new LoginUi();
		targetUi.create();

		LoginController targetController = new LoginController(targetUi);
		targetController.initialize();

		DummyDb db = new DummyDb();
		targetController.setDatabase(db);

		targetUi.getUsernameTextField().setText("root");
		targetUi.getPasswordTextField().setText("sys");
		targetUi.getConnectButton().fire();

		// when
		targetUi.getDisconnectButton().fire();
		
		// then
		assertThat(targetUi.getConnectedLabel().getText(), is("Not Connected"));

	}

	
}
