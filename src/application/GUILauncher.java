package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUILauncher {

	public void updatePopup(){
		try {
			Stage s = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("UpdateGUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			s.setTitle("Updating...");
			s.setScene(scene);
			s.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void settingsPopup(){
		try {
			Stage s = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("SettingsGUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			s.setTitle("Settings");
			s.setScene(scene);
			s.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
