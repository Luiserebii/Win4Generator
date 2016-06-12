package win4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

public class SettingsGUIController implements Initializable {

	@FXML
	private CheckBox orderMattersBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void toggleOrderMatters(){
		if(orderMattersBox.isSelected()) { //technically, could refactor this into setting isOrderMatters to isSelected, but will keep this way for readibility
			Win4Generator.isOrderMatters = true;
		} else {
			Win4Generator.isOrderMatters = false;
		}
	}



}
