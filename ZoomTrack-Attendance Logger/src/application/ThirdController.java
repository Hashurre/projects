package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class ThirdController implements Initializable {
	@FXML private Label aList;
	@FXML private Label dateLabel;
	private String date = AttendanceLogger.date;
	private String print = AttendanceLogger.list;
	private String present = AttendanceLogger.present;
	@FXML private TextArea textA;
	@FXML private Label presentL;
	@FXML private Label aTen;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			String dateFormat = date.substring(0,4);
			dateFormat+="-";
			dateFormat += date.substring(4,6);
			dateFormat +="-";
			dateFormat += date.substring(6);
			dateLabel.setText(dateFormat);
			textA.setText(print);
			presentL.setText(present);
			TranslateTransition trans = new TranslateTransition();
			trans.setNode(dateLabel);
			trans.setByY(80);
			trans.setDuration(Duration.seconds(1));
			trans.play();
			TranslateTransition trans1 = new TranslateTransition();
			trans1.setNode(presentL);
			trans1.setByY(80);
			trans1.setDuration(Duration.seconds(1));
			trans1.play();
			TranslateTransition trans2 = new TranslateTransition();
			trans2.setNode(aTen);
			trans2.setByY(80);
			trans2.setDuration(Duration.seconds(1));
			trans2.play();
		}
		catch(Exception e) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("Wrong Input Format");
			al.setHeaderText("Your input file format was incorrect.");
			al.setContentText("Please submit a Zoom chat file which is formatted correctly");
			al.show();
			try {
				AttendanceLogger.main(null);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
	}
	
	

}
