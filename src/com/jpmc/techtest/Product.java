/**
 * 
 */
package com.jpmc.techtest;

/**
 *      A product class contains the product details such as it's type, price
 *      quantity,operation done,total price of an product,etc.

 *
 */
public class Product {
	
	private String productType;
	
	private double productPrice;
	
	private int quantity;
	
	private int totalQuantity=0;
	
	private double totalPrice=0.0;
	
	private String operator=null;
	
    // Constructor
    public Product(String type) {
        this.setProductType(type);
    }

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity += totalQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public double calculatePrice(int quantity2, double productPrice2) {
		return quantity * productPrice;
	}

	public void updateTotalPrice(double productValue) {
		this.totalPrice += productPrice;
		
	}

    
}
