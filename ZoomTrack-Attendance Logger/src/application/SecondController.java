package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SecondController implements Initializable {
	@FXML private Label labelInstruction;
	@FXML private Label plus;
	@FXML private ImageView arrow;
	private boolean isClicked = false;
	private Parent root;
	private Scene scene;
	private Stage stage;
	public boolean lock = false;
	public static String staticPath;
	public static boolean checkExp = AttendanceLogger.chckException;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition trans = new TranslateTransition();
		trans.setNode(labelInstruction);
		trans.setToY(75);
		trans.setDuration(Duration.seconds(1));
		trans.play();
		
		TranslateTransition trans2 = new TranslateTransition();
		trans2.setNode(plus);
		trans2.setToY(-410);
		trans2.setDuration(Duration.seconds(1));
		trans2.play();
		
	}
	
	public void fileDrag(DragEvent e) {
		if(e.getDragboard().hasFiles()) {
			e.acceptTransferModes(TransferMode.ANY);
			
		}
		
	}
	public void fileDrop(DragEvent e) throws FileNotFoundException {
		if(lock == false) {
			File file = (e.getDragboard().getFiles().get(0));
			String name = file.getName();
			if(name.substring(name.length()-3,name.length()).equals("txt")) {
				staticPath = file.getAbsolutePath();
				AttendanceLogger.main(null);
				animation();
			}
			else {
				Alert al = new Alert(AlertType.INFORMATION);
				al.setTitle("Wrong Input");
				al.setHeaderText("Your input file was incorrect.");
				al.setContentText("Please submit a Zoom chat file which ends with a .txt.");
				al.show();
			}
		}

	}
	public void animation() {
		if(checkExp == false) {
			TranslateTransition trans = new TranslateTransition();
			trans.setNode(arrow);
			trans.setByX(-530);
			trans.setDuration(Duration.seconds(1));
			trans.play();
			lock = true;
			
		}
		AttendanceLogger.chckException = false;
		checkExp = false;
	}
	public void hoverGreen() {
		ColorAdjust ca = new ColorAdjust();
		ca.setContrast(0.3);
		ca.setBrightness(-0.32);
		ca.setHue(-0.26);
		ca.setSaturation(0);
		arrow.setEffect(ca);
		
		
	}
	public void hoverOffBlue() {
		if(isClicked == false) {
			ColorAdjust ca = new ColorAdjust();
			ca.setContrast(0.3);
			ca.setBrightness(-0.32);
			ca.setHue(0.38);
			ca.setSaturation(0);
			arrow.setEffect(ca);
		}
	}
	
	public void nextScene(MouseEvent a) throws IOException, InterruptedException {
		hoverGreen();
		isClicked = true;
		TranslateTransition transSpeed = new TranslateTransition();
		transSpeed.setNode(arrow);
		transSpeed.setByX(530);
		transSpeed.setDuration(Duration.seconds(0.3));
		transSpeed.setOnFinished(e->{
			try {
				waitT(a);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		transSpeed.play();
	}
	public void waitT(MouseEvent a) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ThirdScene.fxml"));
		stage = (Stage)((Node)a.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public static void exceptionHandle(boolean chck) {
		if(chck == true) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("Wrong Input Format");
			al.setHeaderText("Your input file format was incorrect.");
			al.setContentText("Please submit a Zoom chat file which is formatted correctly");
			al.show();
		}
		
	}

	
	
	

}
