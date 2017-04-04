package egger.software.baylandtag.ao_filter_table;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {

	private Properties properties;
	private Path propertiesPath;

	public Configuration() {
		propertiesPath = getPropertiesPath();
	}

	public void load() throws IOException {

		properties = new Properties();

		if (Files.exists(propertiesPath)) {
			try (FileReader reader = new FileReader(propertiesPath.toFile())) {
				properties.load(reader);
			}
		}

	}

	public void save() throws IOException {
		
		try (FileWriter writer = new FileWriter(propertiesPath.toFile())) {
			properties.store(writer, "Baylandtag DB properties");
		}
		
	}

	private Path getPropertiesPath() {

		String userHome = System.getProperty("user.home");
		if (userHome == null)
			userHome = ".";
		return Paths.get(userHome, "baylandtag.properties");

	}

	public String getDatabaseUrl() {
		return properties.getProperty("url", "");
	}

	public void setDatabaseUrl(String url) {
		properties.setProperty("url", url);
	}

	public String getUsername() {
		return properties.getProperty("username", "");
	}

	public void setUsername(String username) {
		properties.setProperty("username", username);
	}

	public String getPassword() {
		return properties.getProperty("password", "");
	}

	public void setPassword(String password) {
		properties.setProperty("password", password);
	}

	public Double getStageWidth() {
		return Double.parseDouble(properties.getProperty("stageWidth", "800.0"));
	}

	public void setStageWidth(double stageWidth) {
		properties.setProperty("stageWidth", Double.toString(stageWidth));
	}

	public Double getStageHeight() {
		return Double.parseDouble(properties.getProperty("stageHeight", "600.0"));
	}

	public void setStageHeight(double stageHeight) {
		properties.setProperty("stageHeight", Double.toString(stageHeight));
	}

	
}
