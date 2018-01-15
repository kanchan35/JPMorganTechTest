/**
 * 
 */
package com.jpmc.techtest;

/**
 *      Sale class processes each product message and appends it in the sales log.
 *      Uses a message parser to parse the incoming messages and collects the
 *      product information and prepares a product object for sale processing.
 *      Ignores processing of any invalid messages and processes until it reaches the
 *      log report limit.
 */
public class Sale {
	
    // public field log to store messages and product details
    public SaleLog saleLog;
    
    // Adjustment of product price is handled within this object e.g. add 20p, subtract 20p, etc.
    private AdjustPrice adjustPrice;

    // This holds the product details
    private Product product;
    
    // Constructor
    public Sale() {
    	saleLog = new SaleLog();
    }

	public boolean processNotification(String inputMsg) {
		
        // MessageParser helps to parse the incoming messages and obtain product sale information.
        MessageParser messageParser;

        // Process the given message
        messageParser = new MessageParser(inputMsg);
		
        // Get the product type e.g 'apple'
        String productType = messageParser.getProductType();

        // Check if product type is empty return false and do nothing.
        if (productType.isEmpty()) {
            return false;
        }

        //Returns an existing product else returns a new Product object
        this.product = saleLog.getProduct(productType);

        // Prepare the product details for adjustment
        this.adjustPrice = new AdjustPrice(product);

        // Set the product details from the parsed message
        this.product.setQuantity(messageParser.getProductQuantity());
        this.product.setTotalQuantity(messageParser.getProductQuantity());
        this.product.setProductPrice(messageParser.getProductPrice());
        this.product.setOperator(messageParser.getOperatorType());

        // Set the total value of the product.
        setProductTotalPrice();

        // Set the sale log reports
        saleLog.setReports(inputMsg);

        // Update the product with the new details
        saleLog.updateProduct(product);

        return true;
    }

    // Set or update Total product price based on any adjustment if given.
    // Also update the log for adjustments made.
    private void setProductTotalPrice() {
        double adjustedPrice;
        double productValue;

        if (!product.getOperator().isEmpty()) {
            adjustedPrice = adjustPrice.getAdjustedPrice();
            saleLog.setAdjustmentReports(adjustPrice.adjustmentReport());
            product.setTotalPrice(adjustedPrice);
        } else {
            productValue = product.calculatePrice(product.getQuantity(), product.getProductPrice());
            product.updateTotalPrice(productValue);
        }
    }
	

}
