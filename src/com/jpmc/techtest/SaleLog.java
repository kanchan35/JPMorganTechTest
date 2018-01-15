/**
 * 
 */
package com.jpmc.techtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *      Stores information about the sale notifications and sale adjustments made to the products.
 *      Provides a report in tabular form for after every 10 transactions.It stops accepting new
 *      messages after every 50th message. The adjustment made are shown after the 50th message.
 *
 */
public class SaleLog {
	
    private Map<String, Product> items = new HashMap<>();

    // Used to total the sale value of the product. @note: does not store total value of the all products
    private double totalSalesValue;

    // Logs all sales messages which are successful.
    private  List<String> reports;

    // Logs all the adjustment reports of the sale transaction.
    private List<String> adjustmentReports;

    // Constructor
    public SaleLog() {
        this.reports = new ArrayList();
        this.adjustmentReports = new ArrayList();
        this.totalSalesValue = 0.0;
    }

	public Product getProduct(String type) {
	
		return items.getOrDefault(type,new Product(type));
	}

	public void updateProduct(Product product) {

		items.put(product.getProductType(), product);
		
	}

	public void setReports(String newReport) {
		
		this.reports.add(newReport);
		
	}
	
    // Get all the adjustment report as an array list
    public List getAdjustmentReports() {return adjustmentReports;}

	public void setAdjustmentReports(String adjustmentReport) {

		this.adjustmentReports.add(adjustmentReport);
		
	}

    // return the total sales value
    public double getTotalSalesValue() {
        return totalSalesValue;
    }

    // Append any given value to the totalSalesValue field
    public void appendTotalSalesValue(double productTotalPrice) { totalSalesValue += productTotalPrice;}

    // Set total sales value with the given value
    public void setTotalSalesValue(double productTotalPrice) { totalSalesValue = productTotalPrice;}

    /* Report outputs sales information to system console on every 10th report iteration using modulo.
     * Displays in a table formatted structure and stops execution of the application after 50th message
     * iteration.
    */
    public void report() {

        // Report after 10th iteration and not at the beginning.
        if((reports.size() % 10) == 0 && reports.size() !=0) {
            setTotalSalesValue(0.0);
            //System.out.println(normalReports);
            System.out.println("Details of the Last 10 Transactions::");
            System.out.println("*************** Log Report *****************");
            System.out.println("|Product           |Quantity   |Value      |");
            System.out.println("--------------------------------------------");
            items.forEach((k,v) -> formatReports(k,v));
            System.out.println("-------------------------------------------");
            System.out.println(String.format("|%-30s|%-11.2f|","Total Sales",getTotalSalesValue()));
            System.out.println("-------------------------------------------");
            System.out.println("End\n\n");
        }

        // Report and stop execution after 50th message
        if((reports.size() % 50) == 0 && reports.size() !=0) {
            System.out.println("Application reached 50 messages limit.It cannot process further. The following are the adjustments made::\n");

            // Display all the adjustment reports so far recorded.
            getAdjustmentReports().forEach(System.out::println);
            System.exit(1);
        }
    }

    // Format the report with right padding structure. populates product details on each line.
    public void formatReports(String type, Product product) {
        String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(), product.getTotalPrice());
        appendTotalSalesValue(product.getTotalPrice());
        System.out.println(lineItem);
    }
}
