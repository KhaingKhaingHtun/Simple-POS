package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private Label lblGender;

	@FXML
	private Label lblItems;

	@FXML
	private Label lblName;

	@FXML
	private Label lblRole;

	@FXML
	private Label lblTotal;

	private Map<String, Integer> itemsMap = new HashMap<>();
	private Map<String, Integer> selectedItemsMap = new HashMap<>();

	@FXML
	void processCheckout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckoutUI.fxml"));
		Parent parent = loader.load();
		CheckoutController checkoutController = loader.getController();
		checkoutController.initData(selectedItemsMap);
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		// Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Order");
		stage.show();

	}

	@FXML
	void processItems(ActionEvent event) {
		CheckBox checkBox = (CheckBox) event.getSource();
		String label = checkBox.getText();
		if (checkBox.isSelected()) {
			selectedItemsMap.put(label, itemsMap.get(label));
		} else {
			selectedItemsMap.remove(label);
		}
		lblItems.setText("Items : " + String.valueOf(selectedItemsMap.size()));

		int total = 0;
		for (Entry<String, Integer> item : selectedItemsMap.entrySet()) {
			total += item.getValue();
		}
		lblTotal.setText("Total : " + String.valueOf(total));
	}

	@FXML
	void processLogout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();

		Parent mainRoot = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
		Scene mainScene = new Scene(mainRoot);
		stage.setScene(mainScene);
		stage.setTitle("Login");
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblName.setText(Data.getEmail());
		lblRole.setText(Data.getRole());
		lblGender.setText(Data.getGender());

		itemsMap.put("Cola", 1200);
		itemsMap.put("Pepsi", 1000);
		itemsMap.put("7up", 800);
		itemsMap.put("Speed", 1300);
		itemsMap.put("ToeToe", 500);
		itemsMap.put("Pringles", 3000);
		itemsMap.put("Pizza", 1500);
		itemsMap.put("Burger", 2000);
		itemsMap.put("Shampoo", 200);
		itemsMap.put("Soap", 800);
		itemsMap.put("FacialWash", 6000);
		itemsMap.put("Bread", 700);
		itemsMap.put("Pussi Cake", 2300);
		itemsMap.put("Coffee", 250);
		itemsMap.put("Tea", 300);
		itemsMap.put("Milkshake", 1000);
		itemsMap.put("Jelly", 500);
		itemsMap.put("Fruit", 2500);
		itemsMap.put("Decogen", 2200);
		itemsMap.put("Conedin", 400);
	}

}
