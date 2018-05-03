package pointOfSale.view;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pointOfSale.Main;
import pointOfSale.model.Product;

public class MainPageController {

	@FXML
	private TableView<Product> receiptTable;

	@FXML
	private TableColumn<Product, String> productColumn;

	@FXML
	private TableColumn<Product, Number> priceColumn;

	@FXML
	private Label totalPrice;

	//Reference to the main app.
	private Main main;

	public MainPageController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		///Initialize the receipt table with two columns.
		productColumn.setCellValueFactory(cellData -> cellData.getValue().getNameOfProduct());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());


	}

	/**
	 * Reference to main app.
	 * @param main
	 */
	public void setMain(Main main) {
		this.main = main;

		totalPrice.setText("0.00");
		//Set label with total price of shopping
		totalPrice.textProperty().bind(main.getShoppingAccount().getTotalPrice().asString());

		// Add obsevable list to the table
		receiptTable.setItems(main.getShoppingAccount().getShoppingList());

	}

	/**
	 * New window to scan new product.
	 */
	public void scanNewProduct() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/ScanProduct.fxml"));
			AnchorPane scanProduct = loader.load();

			Stage scanProductStage = new Stage();
			scanProductStage.setTitle("Scan product");
			scanProductStage.initModality(Modality.WINDOW_MODAL);
			scanProductStage.initOwner(main.getMainStage());

			Scene scene = new Scene(scanProduct);
			scanProductStage.setScene(scene);

			ScanProductController controller = loader.getController();
			controller.setStage(scanProductStage);
			controller.setMain(main);

			scanProductStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printReceipt() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Print");
		alert.setHeaderText("Are you sure?");
		alert.setContentText("Do you want to print a receipt?");

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes) {
			main.getShoppingAccount().newShoppingAccount();
		}
	}

}
