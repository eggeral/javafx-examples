package baylandtag.ag_dummy_person_table;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Utils {

	public static void showException(Exception ex) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(ex.getMessage());
		if (ex.getCause() != null)
			alert.setContentText(ex.getCause().getMessage().substring(0, 100));

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");
		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
	
	public static void loadDatabaseProperties(Database database) {
		Path databasePropertiesPath = databasePropertiesPath();
		if (Files.exists(databasePropertiesPath)) {

			Properties properties = new Properties();
			try (FileReader reader = new FileReader(databasePropertiesPath.toFile())) {

				properties.load(reader);
				database.setUrl(properties.getProperty("url"));
				database.setUser(properties.getProperty("username"));
				database.setPassword(properties.getProperty("password"));

			} catch (IOException ex) {
				System.out.println("Error reading properties from: " + databasePropertiesPath());
				ex.printStackTrace();
			}
			
		}
	}

	private static Path databasePropertiesPath() {
		String userHome = System.getProperty("user.home");
		if (userHome == null)
			userHome = ".";
		return Paths.get(userHome, "baylandtag.properties");
	}

	public static void saveDatabaseProperties(Database database) throws IOException{
		Properties properties = new Properties();
		properties.setProperty("url", database.getUrl());
		properties.setProperty("username", database.getUser());
		properties.setProperty("password", database.getPassword());

		try (FileWriter writer = new FileWriter(databasePropertiesPath().toFile())) {
			properties.store(writer, "Baylandtag DB properties");
		}
	}


}
