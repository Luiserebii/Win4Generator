package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import win4.Win4Generator;

public class GUIController implements Initializable {

	Win4Generator wg;
	GUILauncher gl;

	ImageView imgView;

	@FXML
	private ImageView logoImage;

	@FXML
	private Label logoLabel, dateLabel;

	@FXML
	private GridPane numberPane;

	@FXML
	private Button generateButton;

	@FXML
	private Button updateButton;

	@FXML
	private Button settingsButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			logoImage.setImage(new Image(new File("img/win4.png").toURI().toURL().toString()));
			imgView = new ImageView(new Image(new File("img/settings_gear2.png").toURI().toURL().toString(), 40, 40, true, true));
			imgView.setFitWidth(40);
			imgView.setFitHeight(40);
			settingsButton.setGraphic(imgView);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wg = new Win4Generator();
		//wg.updateWinningNumbers();
		wg.loadPrevLottoNums();
		setLatestDate();

//		DebugWin4Generator debug = new DebugWin4Generator();
//		debug.loadStuff();
//		debug.debugLottoNums();

		gl = new GUILauncher();
	}

	public void writeToPane(int[] inArr){

        ObservableList<Node> children = numberPane.getChildren();
        int cnt = 0;
        for(Node node : children) {
        	((TextField) node).setText(String.valueOf(inArr[cnt]));
        	cnt++;
        }

	}

	 public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) { //totally copy-pasted from SO, but it's easy to understand how this works
	        Node result = null;
	        ObservableList<Node> childrens = gridPane.getChildren();
	        for(Node node : childrens) {
	            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	                result = node;
	                break;
	            }
	        }
	        return result;
	 }

	 public void setLatestDate(){
		 dateLabel.setText("Latest Win4 Date: " + wg.loadLatestDate());
	 }

	 @FXML
	 public void generateLottoNums(){
		 int[] lottoNums = wg.generateLottoNums();
		 writeToPane(lottoNums);
	 }

	 @FXML
	 public void openSettings(){
		 gl.settingsPopup();
	 }

	 @FXML
	 public void updatePopup(){
		 gl.updatePopup();
	 }

	 @FXML
	 public void enterSettingsButton(){
		 try {
			imgView.setImage(new Image(new File("img/settings_gear2_gray.png").toURI().toURL().toString(), 40, 40, true, true));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	 @FXML
	 public void exitSettingsButton(){
		 try {
			imgView.setImage(new Image(new File("img/settings_gear2.png").toURI().toURL().toString(), 40, 40, true, true));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


}
