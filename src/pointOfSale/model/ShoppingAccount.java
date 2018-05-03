package pointOfSale.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * 
 * This class is a model of shopping account.
 *
 */
public class ShoppingAccount {
	
	/**
	 * @param shoppingList - A list of bought products.
	 * @param totalPrice - A total price of shopping.
	 */
	
	private ObservableList<Product> shoppingList = FXCollections.observableArrayList();;
	private DoubleProperty totalPrice;

	public ShoppingAccount() {
		totalPrice = new SimpleDoubleProperty(0);
	}

	public ObservableList<Product> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(ObservableList<Product> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public DoubleProperty getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(DoubleProperty totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * It calculates total price of shopping.
	 */
	public void calculateTotalPrice() {
		Double total = new Double(0.0);
		
		for (Product product : shoppingList) {
			total += product.getPrice().get();
		}
		
		totalPrice.set(BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}

	public void newShoppingAccount() {
		shoppingList.clear();
		totalPrice.set(0.00);
	}
}
