package com.jpmc.techtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the Main class from where the application can executed.
 * It requires an input file to run this application and the 
 * output is logged on the system console.
 */

public class MessageProcessorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader inputFile=null;
        // Initiate the sale object
        Sale sale = new Sale();
        // Read inputs from test file
        try {
            String line;
            inputFile = new BufferedReader(new FileReader("./inputData/testData.txt"));
            while((line = inputFile.readLine()) != null) {
            	
                // process message for each sale notification
                sale.processNotification(line);

                // Call the report
                // report only generates after every 10th iteration and stops on 50th iteration.
                sale.saleLog.report();
            }
            sale.saleLog.report();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        finally{
        	if(inputFile != null){
        		try {
					inputFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        		
        }

	}

}
