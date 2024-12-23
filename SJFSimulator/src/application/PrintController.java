package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrintController  {
	@FXML Label resultLabel;
	@FXML Button generateGraphButton;
	private static String resultsString;
	public static void getResults(String t) {
		resultsString = t;
	}
	public void viewResults() {
		resultLabel.setText(resultsString);
	}
	public void generateGraph(ActionEvent e) {
		new TestingGui(MainEngTest.hld);
	}

	
	
	

}
