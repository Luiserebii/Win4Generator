package update;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class UpdateGUIController implements Initializable {

	public UpdateHandler uh;

	@FXML
	private Label titleLabel;

	@FXML
	private ProgressBar updateBar;

	@FXML
	private Label percentageLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		uh = new UpdateHandler(this);
		uh.updateWinningNumbers();
	}

	public void setProgress(double x){ //takes double 0.00, sets updateBar and percentageLabel - 0.00 to 1.0
		updateBar.setProgress(x);
		percentageLabel.setText(Double.toString(x*100) + "%");
	}

	public void setTitleLabel(String str){
		titleLabel.setText(str);
	}

}
