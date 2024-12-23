package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
		Scene firstScene = new Scene(root);
		primaryStage.setScene(firstScene);
		primaryStage.setTitle("ZoomTrack: Attendance Logger");
		primaryStage.getIcons().add(new Image("/piclogo1.png"));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
