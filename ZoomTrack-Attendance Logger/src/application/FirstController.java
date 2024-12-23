package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FirstController implements Initializable {
	@FXML private Label zoomTrackLabel;
	@FXML private ImageView logo;
	@FXML Label description;
	@FXML ImageView arrow;
	private boolean isClicked = false;
	private Parent root;
	private Scene scene;
	private Stage stage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition trans = new TranslateTransition();
		trans.setNode(zoomTrackLabel);
		trans.setToX(875);
		trans.setDuration(Duration.seconds(1));
		trans.play();		
		TranslateTransition trans3 = new TranslateTransition();
		trans3.setNode(description);
		trans3.setToY(-300);
		trans3.setDuration(Duration.seconds(1));
		trans3.play();	
		TranslateTransition trans4 = new TranslateTransition();
		trans4.setNode(arrow);
		trans4.setByX(-250);
		trans4.setDuration(Duration.seconds(1));
		trans4.play();
		
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
		transSpeed.setByX(500);
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
		root = FXMLLoader.load(getClass().getResource("SecondScene.fxml"));
		stage = (Stage)((Node)a.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}

}
