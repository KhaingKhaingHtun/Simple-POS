package application;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

public class CheckoutController implements Initializable {

	@FXML
	private Label lblTotalPrice;

	@FXML
	private ListView<String> listviewItems;

	@FXML
	void processClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			System.out.println("ListView is clicked.");
			// default = single select mode
			/*
			 * String selectedItem = listviewItems.getSelectionModel().getSelectedItem(); if
			 * (selectedItem != null) { System.out.println(selectedItem); }
			 */
			ObservableList<String> selectedItems = listviewItems.getSelectionModel().getSelectedItems();
			selectedItems.forEach(System.out::println);
		}

	}

	public void initData(Map<String, Integer> itemsMap) {
		int count = 1;
		int totalPrice = 0;
		for (final Entry<String, Integer> item : itemsMap.entrySet()) {
			listviewItems.getItems().add(count + ". " + item.getKey() + " = " + item.getValue());
			totalPrice += item.getValue();
			count++;
		}
		lblTotalPrice.setText("Total Price : " + String.valueOf(totalPrice));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		listviewItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

}