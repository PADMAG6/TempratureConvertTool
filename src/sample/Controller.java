package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodTextRun;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomLable;

	@FXML
	public ChoiceBox choiceBox;

	@FXML
	public TextField textField;

	@FXML
	public Button button;

	private static final String CIto_F_TEXT= "Celsius to Fahrenheit";
	private static final String Fr_to_CI_TEXT= "Fahrenheit to Celsius";

	private boolean isCI_to_F = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(CIto_F_TEXT);
		choiceBox.getItems().add(Fr_to_CI_TEXT);


		choiceBox.setValue(CIto_F_TEXT);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue.equals(CIto_F_TEXT)) {
				isCI_to_F = true;
			} else {
				isCI_to_F = false;
			}
		});

		button.setOnAction(event -> {
			convert();
		});
	} 
	private void convert(){
		String input = textField.getText();  //23.4 => "23.4"

		float enterTemperature= 0.0f;
		try {

			enterTemperature = Float.parseFloat(input);
		} catch (Exception exception) {
			warnUser();
			return;
			//no code excecuted ..
		}

		float newTemperature = 0.0f;

			if (isCI_to_F) {
				newTemperature = (enterTemperature * 9 / 5) + 32;
			} else {
				newTemperature = (enterTemperature - 32) * 5 / 9;
			}

			display(newTemperature);

		}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}


	private void display(float newTemperature) {
		String unit = isCI_to_F? " F" : " C";
		System.out.println("The new temperature is: " + newTemperature + unit );

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is: " + newTemperature + unit);
		alert.show();

	}


}

