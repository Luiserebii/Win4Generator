package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GUIController implements Initializable {


	Win4Generator wg;

	@FXML
	private ImageView logoImage;

	@FXML
	private Label logoLabel;

	@FXML
	private GridPane numberPane;

	@FXML
	private Button generateButton;

	@FXML
	private Button updateButton;

	@FXML
	private Button settingsButton;

	@FXML
	private ImageView settingsImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			logoImage.setImage(new Image(new File("img/win4.png").toURI().toURL().toString()));
			ImageView imgView = new ImageView(new Image(new File("img/settings_gear2.png").toURI().toURL().toString(), 40, 40, true, true));
			imgView.setFitWidth(40);
			imgView.setFitHeight(40);
			settingsButton.setGraphic(imgView);
			settingsButton.setPadding(Insets.EMPTY); /*This styling stuff should be moved to .css*/
			settingsButton.setBorder(null);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wg = new Win4Generator();
		//wg.updateWinningNumbers();
		wg.loadPrevLottoNums();

//		DebugWin4Generator debug = new DebugWin4Generator();
//		debug.loadStuff();
//		debug.debugLottoNums();
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

	 @FXML
	 public void generateLottoNums(){
		 int[] lottoNums = wg.generateLottoNums();
		 writeToPane(lottoNums);
	 }


}
