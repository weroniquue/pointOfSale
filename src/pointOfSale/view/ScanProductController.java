package pointOfSale.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pointOfSale.Main;
import pointOfSale.model.EmptyBarCodeException;
import pointOfSale.model.InvalidBarCodeException;
import pointOfSale.model.Product;

public class ScanProductController {

	@FXML
	private TextField barCodeField;

	private Stage stage;
	private Main main;

	public ScanProductController() {
		// TODO Auto-generated constructor stub
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void handleClose() {
		stage.close();
	}

	public void handleOk() {
		try {
			isBarCodeValid();

			Product newProduct = main.getDataBase().get(barCodeField.getText());
			main.getShoppingAccount().getShoppingList().add(newProduct);
			main.getShoppingAccount().calculateTotalPrice();

			handleClose();

		} catch (EmptyBarCodeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(stage);
			alert.setTitle("Empty field");
			alert.setHeaderText("Bar code field is empty!");
			alert.setContentText("Type correct value.");

			alert.showAndWait();
		} catch (InvalidBarCodeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(stage);
			alert.setTitle("Invalid field");
			alert.setHeaderText("Bar code is invalid!");
			alert.setContentText("The database does not contain such product.");

			alert.showAndWait();
		}

	}

	/**
	 * Checks if bar code is valid.
	 * 
	 * @throws EmptyBarCodeException
	 * @throws InvalidBarCodeException
	 */
	public void isBarCodeValid() throws EmptyBarCodeException, InvalidBarCodeException {
		if (barCodeField.getText() == null || barCodeField.getText().length() == 0) {
			throw new EmptyBarCodeException("Empty bar code");
		}

		if (!main.getDataBase().containsKey(barCodeField.getText())) {
			throw new InvalidBarCodeException("Data base doesnt contain this barcode");
		}

	}
}
