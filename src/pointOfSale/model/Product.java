package pointOfSale.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * This class is a model of product.
 *
 */

public class Product {
	/**
	 * @param barCode
	 *            The bar code of product.
	 * @param nameOfProduct
	 *            The name of product.
	 * @param price
	 *            The price of product.
	 */
	private StringProperty barCode;
	private StringProperty nameOfProduct;
	private DoubleProperty price;

	public Product() {
		this.barCode = new SimpleStringProperty();
		this.nameOfProduct = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return barCode.get();
	}

	public StringProperty getBarCodeProperty() {
		return barCode;
	}

	public String getBarCode() {
		return barCode.get();
	}

	public void setBarCode(String barCode) {
		this.barCode.set(barCode);
	}

	public StringProperty getNameOfProduct() {
		return nameOfProduct;
	}

	public void setNameOfProduct(String nameOfProduct) {
		this.nameOfProduct.set(nameOfProduct);
	}

	public DoubleProperty getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price.set(price);
	}

}
