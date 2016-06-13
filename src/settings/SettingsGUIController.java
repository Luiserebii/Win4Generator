package settings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import win4.Win4Generator;

public class SettingsGUIController implements Initializable {

	@FXML
	private CheckBox orderMattersBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//Set settings as they are in Win4Generator
		orderMattersBox.setSelected(Win4Generator.isOrderMatters);
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
