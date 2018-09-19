package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUILauncher {

	public void updatePopup(){
		popup("/view/UpdateGUI.fxml", "/view/application.css", "Updating...");
	}

	public void settingsPopup(){
		popup("/view/SettingsGUI.fxml", "/view/application.css", "Settings");
	}

	public void popup(String fxmlPath, String cssPath, String title){
		try {
			Stage s = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
			s.setTitle(title);
			s.setScene(scene);
			s.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
