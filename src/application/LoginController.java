package application;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private Label lblStatus;

	@FXML
	private ComboBox<String> comboRole;

	@FXML
	private ToggleGroup genderGroup;

	@FXML
	private TextField pfPassword;

	@FXML
	private RadioButton rbFemale;

	@FXML
	private RadioButton rbMale;

	@FXML
	private TextField tfEmail;

	private String gender = "";

	private List<User> userList;

	@FXML
	void processGender(ActionEvent event) {
		if (rbMale.isSelected()) {
			gender = "Male";
		}

		if (rbFemale.isSelected()) {
			gender = rbFemale.getText();
		}
	}

	@FXML
	void processLogin(ActionEvent event) throws IOException {
		String email = tfEmail.getText().trim();
		String password = pfPassword.getText();
		String role = comboRole.getValue();

		lblStatus.setVisible(true);
		if (email.isEmpty() || password.isEmpty() || role == null || gender.isEmpty()) {
			lblStatus.setTextFill(Paint.valueOf("red"));
			lblStatus.setText("* all fields are required");
			return;
		}

		boolean valid = userList.stream().anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password));

		if (valid) {
			lblStatus.setTextFill(Paint.valueOf("green"));
			lblStatus.setText("Access Granted!!");

			Data.setEmail(email);
			Data.setRole(role);
			Data.setGender(gender);

			// Stage stage = new Stage(); //ကိုယ့်ဖာသာဆောက်စရာမလိုဘဲ event object
			// ထဲကနေဖမ်းမယ်။
			/*
			 * Node node = (Node) event.getSource(); Scene scene = node.getScene(); //scene
			 * ရပြီဆိုရင် stage obj ရပြီ။ Stage stage = (Stage) scene.getWindow();
			 * stage.hide();
			 */
			// stage obj တစ်ခုတည်းကနေ UI တွေအများကြီးပြလို့ရတယ်။ UI တစ်ခုကို stage
			// တစ်ခုဆောက်စရာမလိုဘူး။

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.hide();

			Parent mainRoot = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			stage.setScene(mainScene);
			stage.setTitle("Main UI");
			stage.show();
		}

		if (!valid) {
			lblStatus.setTextFill(Paint.valueOf("red"));
			lblStatus.setText("Invalid Username or Password");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> roleList = FXCollections.observableArrayList("Admin", "Manager", "Cashier");

		comboRole.setItems(roleList);

		userList = Arrays.asList(new User("a@gmail.com", "1234"), new User("b@gmail.com", "apple"),
				new User("c@gmail.com", "admin"), new User("d@gmail.com", "orange"), new User("e@gmail.com", "hello"));
	}

}
