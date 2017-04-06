package baylandtag.au_display_image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();

		
		// 1.
		Image image = new Image(Utils.getResourceInCurrentPackage("bayern.jpg").toString());
		
		ImageView imageView = new ImageView(image);
		root.getChildren().add(imageView);
		
		// 2. Image as tool tip
		Label label = new Label("hover please");
		root.getChildren().add(label);
		
		Tooltip imageTooltip = new Tooltip("Tool tip");
		ImageView tooltipImageView = new ImageView(image);
		imageTooltip.setGraphic(tooltipImageView);
		label.setTooltip(imageTooltip);
		
		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
