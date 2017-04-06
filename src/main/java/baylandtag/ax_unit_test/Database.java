package baylandtag.ax_unit_test;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface Database {

	void connect();

	void disconnect();

	boolean isConnected();

	void setConnected(boolean value);

	BooleanProperty connectedProperty();

	String getUrl();

	void setUrl(String value);

	StringProperty urlProperty();

	String getUser();

	void setUser(String value);

	StringProperty userProperty();

	String getPassword();

	void setPassword(String value);

	StringProperty passwordProperty();

}