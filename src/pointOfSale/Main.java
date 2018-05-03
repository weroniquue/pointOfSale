package pointOfSale;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pointOfSale.model.Product;
import pointOfSale.model.ShoppingAccount;
import pointOfSale.view.MainPageController;

public class Main extends Application {

	private Stage mainStage;
	private BorderPane rootLayout;

	private ShoppingAccount shoppingAccount;

	// K-barcode, V-product
	private HashMap<String, Product> dataBaseOfProduct;

	public Main() {

		this.shoppingAccount = new ShoppingAccount();
		readDataBase();

	}

	
	/**
	 * Read database from txt file.
	 */
	public void readDataBase() {
		this.dataBaseOfProduct = new HashMap<String, Product>();

		Scanner read = new Scanner(Main.class.getResourceAsStream("db.txt"));

		while (read.hasNext()) {
			String[] product = read.nextLine().split(";");

			Product tmp1 = new Product();
			tmp1.setBarCode(product[0]);
			tmp1.setNameOfProduct(product[1]);
			tmp1.setPrice(Double.parseDouble(product[2]));

			dataBaseOfProduct.put(product[0], tmp1);

		}
		read.close();

	}

	@Override
	public void start(Stage primaryStage) {

		this.mainStage = primaryStage;
		this.mainStage.setTitle("Point of Sale");

		initRootLayout();
		showMainPage();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			//Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			mainStage.setScene(scene);
			mainStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Shows main page view inside the root layout.
	 */
	public void showMainPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainPage.fxml"));
			AnchorPane mainPage = loader.load();

			rootLayout.setCenter(mainPage);

			MainPageController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getMainStage() {
		return mainStage;
	}

	/**
	 * Returns the shopping account.
	 * @return
	 */
	public ShoppingAccount getShoppingAccount() {
		return shoppingAccount;
	}

	/**
	 * Returns the hash map which is the mock database.
	 * @return
	 */
	public HashMap<String, Product> getDataBase() {
		return dataBaseOfProduct;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
