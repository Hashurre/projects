package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@FXML private Button beginButton;
	@FXML private Button submitButton;
	@FXML private TextField textfieldNumPro;
	@FXML private Button submitProcessButton;
	@FXML private TextField tFieldA;
	@FXML private TextField tFieldP;
	@FXML private TextField tFieldE;

	
	private Parent root;
	private Stage stage;
	private Scene scene;
	private int indexI = 0;
	public static Integer numberOfProcesses;
	public static Process process;
	private int pid;
	private int arrivalTime;
	private int executionTime;
	public static Process[] processesArray;
	@Override
		public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
		Scene firstScene = new Scene(root);
		primaryStage.setScene(firstScene);
		primaryStage.setTitle("SJF: Process Scheduling Simulator");
		primaryStage.getIcons().add(new Image("/piclogo.png"));
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void beginSJF(ActionEvent e) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("SecondScene.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
		
	}
	public void submitSceneTwo(ActionEvent e) throws IOException {
			try {
				numberOfProcesses = Integer.parseInt(textfieldNumPro.getText());
				processesArray = new Process[numberOfProcesses];
				root = FXMLLoader.load(getClass().getResource("ThirdScene.fxml"));
	            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	            scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
				
				
			}
			catch(Exception c) {
				Alert al = new Alert(AlertType.INFORMATION);
				al.setTitle("Wrong Input");
				al.setHeaderText("Your input was incorrect.");
				al.setContentText("Please enter a real integer.");
				al.show();
				
			}
		
	}
	public void submitProcess(ActionEvent e) throws IOException {
		if(indexI < numberOfProcesses) {
			try {
				pid = Integer.parseInt(tFieldP.getText());
				arrivalTime = Integer.parseInt(tFieldA.getText());
				executionTime = Integer.parseInt(tFieldE.getText());
				process = new Process(pid,arrivalTime,executionTime);
				processesArray[indexI] = process;
				tFieldP.clear();
				tFieldE.clear();
				tFieldA.clear();
				indexI++;
			}
			catch(Exception ex) {
				Alert al = new Alert(AlertType.INFORMATION);
				al.setTitle("Wrong Input");
				al.setHeaderText("Your input was incorrect.");
				al.setContentText("Please enter a real integer.");
				al.show();
				tFieldP.clear();
				tFieldE.clear();
				tFieldA.clear();
			}

		}
		if(indexI == numberOfProcesses) {
			root = FXMLLoader.load(getClass().getResource("PrintScene.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            beginProgram();

            
		}
		
		
		
	}
	public static void beginProgram() {
        MainEngTest.main(null);
	}



	

	
	
	
}
