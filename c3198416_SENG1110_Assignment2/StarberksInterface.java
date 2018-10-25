/** 
File name: StarberksInterface.java
Author: Nguyen Quang Trung
Student number: c3198416
E-mail Address: quangtrung.nguyen@uon.edu.au
Programming Assignment 2
Last Changed: May 27, 2016
Description: The file contains StarberksInterface class which acts as the interface
for the program. It will receive input from the user, display all output, check
invalid inputs and display all error messages. It uses Store class's methods to
access and modify the data in a product object of the Product class.
*/ 

import java.util.*;
import java.io.*;

public class StarberksInterface 
{
    static Scanner console = new Scanner(System.in);  
    
    //Create two Store objects as instance variables
    static Store LambtonStore;
    static Store CallaghanStore;
    
    //Constructor
    public StarberksInterface()
    {
    	if(LambtonStore == null && CallaghanStore == null)
    	{
    		LambtonStore = new Store();
        	CallaghanStore = new Store();

    	}
    }
    
    //Main method to create instance of this class and call the run() method
	public static void main(String[] args) 
	{    
		StarberksInterface intFace = new StarberksInterface();
        intFace.run();
    }
	
	/*
	 * Name:  run()
	 * Description: A method to display the Main menu and ask for user option.
	 * Pre-conditions: The method is called from the main method when the file is run.
	 * Post-conditions: The method will call other methods according to the user option.
	 */
	private void run() 
	{
		System.out.println("\nWelcome to Starberk's Coffee! \n\n"
		    	+ "1. Choose Store \n"
		    	+ "2. Display stores \n"
		    	+ "3. Open \n"
		    	+ "4. Save \n"
		    	+ "5. Exit \n\n"
		    	+ "Please select one of the above options (1-5): ");    
	    int mainOption = validateIntegerInput(1);
		switch (mainOption)
		{
		    case 1: selectStore();
		                break;
		    case 2: displayStore();
		        	    break;
		    case 3: readProductFile();
		        	    break;
		    case 4: writeProductFile();
		        	    break;            
		    case 5: System.exit(0);
		                break;    
		}
	}
	
	/*
	 * Name: selectStore()
	 * Description: A method to ask users to select a store.
	 * Pre-conditions: The method is called from the run() method when option 1 is selected by user.
	 * Post-conditions: The method will call displayStoreMenu(int storeNumber) method according to 
	 *                  the user selection of which store.
	 */
	public static void selectStore()
	{
		//Get and validate user input of store name
		System.out.println("{Lambton, Callaghan} \n"
				         + "Please select a store: ");
		String storeSelection = validateStringInput(1);
		switch (storeSelection)
		{
		    case "lambton": System.out.println("\nYou have selected Lambton store. \n");
			                displayStoreMenu(1);
		    	            break;
		    case "callaghan": System.out.println("\nYou have selected Callaghan store. \n");
			                  displayStoreMenu(2);
		    	              break;
		} 
	} 
    
	/*
	 * Name:  displayStoreMenu(int storeNumber)
	 * Parameter: store number (1: Lambton, 2: Callaghan)
	 * Description: A method to display second-level menu, ask users to select an option.
	 * Pre-conditions: The method is called from the selectStore() method.
	 * Post-conditions: The method will call other methods according to the user option.
	 */
	public static void displayStoreMenu(int storeNumber)
	{
		System.out.println("1. Add/Edit product \n"
		                 + "2. Delete product \n"
		                 + "3. Display product \n"
		                 + "4. Display all products \n"
		                 + "5. Exit Store \n\n"
		                 + "Please select one of the above options (1-5): ");
	    int storeOption = validateIntegerInput(1);
		switch (storeOption)
		{
		    case 1: addNewProduct(storeNumber);
		                break;
		    case 2: deleteProduct(storeNumber);
		        	    break;
		    case 3: displayProduct(storeNumber);
		        	    break;
		    case 4: displayAllProducts(storeNumber);
		        	    break;             
		    case 5: main(null);
		                break;
		}
	}
	
	/*
	 * Name:  addNewProduct(int storeNumber)
	 * Parameter: store number (1: Lambton, 2: Callaghan)
	 * Description: A method to ask the user to input the name of a product to add new/edit a product
	 * Pre-conditions: The method is called from the displayStoreMenu(int storeNumber) method 
	 *                 when option 1 is selected by user.
	 * Post-conditions: The method will call inputProductData(int storeNumber, String name, int option)
	 *                  method according to the user option.
	 */
	public static void addNewProduct(int storeNumber)
	{
    	String name = validateNameInput(1);
    	boolean productExist = checkProduct(name,storeNumber);
    	if(productExist == true) 
    	{
    		System.out.println("This product already existed. \n"
    			  + "Do you want to change its name (N) or its data (D)? (Enter N or D): ");
    		String answer = validateStringInput(3);
    		if(answer.equals("n")) changeName(storeNumber,name);
    		// 0 indicates modifying existing product 
    		else if (answer.equals("d")) inputProductData(storeNumber,name,0); 
    	}
    	else
    		// 1 indicates creating a new product
    		inputProductData(storeNumber,name,1); 
	}

	/*
	 * Name:  inputProductData(int storeNumber, String name, int option)
	 * Parameters: store number, product name, and option
	 * Description: A method to ask the user to input the data of a product: demand rate, inventory cost,
	 *              unit cost, setup cost and selling price.
	 * Pre-conditions: The method is called from the addNewProduct() method with store number,
	 *                 valid name and option passed to its parameters.
	 * Post-conditions: The method will call other methods accordingly to user option.
	 */
	public static void inputProductData(int storeNumber, String name, int option)
	{
    	System.out.println("Enter the demand rate of product: ");
    	int demand = console.nextInt();
    	while (demand < 1) 
    	{
    		System.out.println("Demand rate must be positive. Please enter again: ");
        	demand = console.nextInt();
    	}	
    	System.out.println("Enter the setup cost of product: ");
    	double setupCost = console.nextDouble();
    	while (setupCost < 0) 
    	{
    		System.out.println("Setup cost cannot be negative. Please enter again: ");
        	setupCost = console.nextDouble();
    	}
    	System.out.println("Enter the inventory cost of product: ");
    	double inventoryCost = console.nextDouble();
    	while (inventoryCost < 0) 
    	{
    		System.out.println("Inventory cost cannot be negative. Please enter again: ");
        	inventoryCost = console.nextDouble();
    	}
    	System.out.println("Enter the unit cost of product: ");
    	double unitCost = console.nextDouble();
    	while (unitCost < 0) 
    	{
    		System.out.println("Unit cost cannot be negative. Please enter again: ");
        	unitCost = console.nextDouble();
    	}
    	System.out.println("Enter the selling price of product: ");
    	double sellingPrice = console.nextDouble();
    	while (sellingPrice <= 0) 
    	{
    		System.out.println("Selling price must be positive. Please enter again: ");
    		sellingPrice = console.nextDouble();
    	}
    	if(option == 1)  //A new product will be created
    	{
    		if(storeNumber == 1) 
    			LambtonStore.addNewProduct(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);  
    		else if(storeNumber == 2)
    			CallaghanStore.addNewProduct(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);  
    		System.out.println("Product " + name + " was added successfully! \n");
    	}
    	else if(option == 0) //Data of an existing product will be modified
    	{
    		if(storeNumber == 1)
    			LambtonStore.updateProductData(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);
    		if(storeNumber == 2)
    			CallaghanStore.updateProductData(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);
    		System.out.println("Product " + name + " has been updated successfully! \n");
    	}
    	returnToMenu(storeNumber);
	}
	
	/*
	 * Name: changeName((int storeNumber, String name) 
	 * Parameters: A valid existing product name, store number (1: Lambton, 2: Callaghan)
	 * Description: A method to change name of an existing product.
	 * Pre-conditions: The method is called from the addNewProduct(int storeNumber) method
	 * Post-conditions: The method will update name of a product
	 */
    public static void changeName(int storeNumber, String name) 
    {
    	String newName =  validateNameInput(2);
    	if(storeNumber == 1)
    		LambtonStore.changeName(name,newName);
    	else if(storeNumber == 2)
    		CallaghanStore.changeName(name,newName);
		System.out.println("Product " + name + " has been changed to " + newName +".\n");
		returnToMenu(storeNumber);
    }
    
	/*
	 * Name: deleteProduct(int storeNumber)
	 * Parameters: store number (1: Lambton, 2: Callaghan)
	 * Description: A method to remove an existing product. 
	 * Pre-conditions: The method is called from displayStoreMenu(int storeNumber) method.
	 * Post-conditions: The method will remove an existing product in chosen store.
	 */ 
    public static void deleteProduct(int storeNumber)
    {
    	//Check if any product exists in store
    	int productCount = checkStoreFull(storeNumber); 
    	//User input of product name
    	if(productCount > 0)
    	{
        	String name = validateNameInput(1);
        	boolean productExist = checkProduct(name,storeNumber);
        	if(productExist == false)
        	{
        		System.out.println("This product does not exist. \n");	
        		returnToMenu(storeNumber);
        	}
        	else if(productExist == true && storeNumber == 1)
        		LambtonStore.removeProduct(name);
        	else if(productExist == true && storeNumber == 2)
        		CallaghanStore.removeProduct(name);
    		System.out.println("Product " + name + " has been deleted. \n");
    		returnToMenu(storeNumber);
    	}

    }
    
	/*
	 * Name: displayProduct(int storeNumber)
	 * Parameters: store number (1: Lambton, 2: Callaghan)
	 * Description: A method to display data of an existing product. User input a valid name.
	 * Pre-conditions: The method is called from displayStoreMenu(int storeNumber) method.
	 * Post-conditions: The method will display data of an existing product according to the user input of the 
	 *                   product name.
	 */
    public static void displayProduct(int storeNumber) 
    {
    	//Display all products names in chosen store
    	int productCount = checkStoreFull(storeNumber);
	    if (productCount > 0 && storeNumber == 1) 
	    	System.out.println(LambtonStore.toString());   //Use of toString() method in Store Class
	    else if (productCount > 0 && storeNumber == 2) 
	    	System.out.println(CallaghanStore.toString()); //Use of toString() method in Store Class
	    //User input of product name
    	System.out.println("Please select a product: ");
    	String name = console.next().toLowerCase(); 
    	boolean productExist = checkProduct(name,storeNumber);
    	if(productExist == false)
    	{
    		System.out.println("This product does not exist. \n");	
    		returnToMenu(storeNumber);
    	}
    	//Display details of chosen product
	    System.out.println("\nInformation about product " + name + ":");
    	if(storeNumber == 1)
    		System.out.println(LambtonStore.getProductDetails(name));
    	if(storeNumber == 2)
    		System.out.println(CallaghanStore.getProductDetails(name));
    	//Ask if users want to see replenishment strategy
    	System.out.println("Do you like to view the replenishment strategy for this product? (Yes/No): ");	
    	String answer = validateStringInput(2);
    	if(answer.equals("no"))
    		displayStoreMenu(storeNumber);
    	else if(answer.equals("yes"))
    		showReplenishmentStrategy(storeNumber, name);
    }
    
	/*
	 * Name:  showReplenishmentStrategy(int storeNumber, String name)
	 * Parameters: product name, store number (1: Lambton, 2: Callaghan)
	 * Description: A method to display Replenishment Strategy of an existing product. 
	 *             User input a valid name and number of week.
	 * Pre-conditions: The method is called from the displayProduct(int storeNumber) method.
	 * Post-conditions: The method will display Replenishment Strategy of an existing product 
	 *                  according to the user input of the product name and week
	 */
    public static void showReplenishmentStrategy(int storeNumber, String name)
    {
    	//Ask user for number of weeks
    	System.out.println("Please enter the number of weeks: ");  
    	int week = validateIntegerInput(2);
    	//Obtain EOQuantitty and demand of the product
    	int EOQuantity = 0,
    	    demand = 0;
    	if(storeNumber == 1)
    	{
    		EOQuantity = LambtonStore.calculateEOQuantity(name);
    		demand = LambtonStore.getDemand(name);
    	}
        else if(storeNumber == 2)
        {
        	EOQuantity = CallaghanStore.calculateEOQuantity(name);
            demand = CallaghanStore.getDemand(name);
        }
    	//Declare variables
    	int inventoryAmount = EOQuantity,
    	    totalInventoryAmount = 0,  //total inventory amount of a product in a run
    	    orderQuantity = EOQuantity,//total inventory order quantity of a product in a run
    	    orderCount = 0,            //total inventory oder count
    	    lastOrderQuantity = 0,     
    	    lastOrderWeek = 0,
    	    lastInventory = 0;         //last week inventory amount
    	double cost = 0,
    	       profit = 0;
    	//Error message for infeasible EOQuantity 
    	if(EOQuantity < demand)
    	{
	    	System.out.println("It is not possible to have a replacement strategy "
	    			         + "with the inputs given, please edit the product details. \n");
	    	returnToMenu(storeNumber);
    	}
    	//Find out the week of last order
    	for(int n=1; n<=week; n++)  //First, last week inventory amount is calculated
    	{		    		       //This is for displaying a  better strategy later on.
	    	if(inventoryAmount >= demand) 
	    		inventoryAmount -= demand;  		
	    	else
	    		inventoryAmount += EOQuantity - demand;
    	}
    	lastInventory = inventoryAmount;
    	lastOrderWeek = week - (int) (EOQuantity - lastInventory) / demand; //A simple formula to calculate   
    	inventoryAmount = EOQuantity - demand;                              //the last order week
    	//Display the strategy
    	System.out.println("\nReplenisment Strategy for product "+name+"\n\n"
    			         + "Week \t" + "Quantity Order \t" 
    	                 + "Demand \t" + "Inventory \t\n" );
    	for(int n=1; n<=week; n++)
    	{
	    	totalInventoryAmount += inventoryAmount;
	    	System.out.println(n + "\t" + orderQuantity + "\t\t" + demand 
	    				         + "\t" + inventoryAmount + "\t\n");		    		
	    	if(inventoryAmount >= demand) 
	    	{
	    		orderQuantity = 0;               //Non-order week
	    		inventoryAmount -= demand; 
	    	}
	    	else
	    	{                                    //Modify the order quantity of the last order week
	    		if(n == lastOrderWeek - 1)       //To have the last week inventory to be 0.  	
	    		{                                //This works for all user input of number of weeks. 			                                                
		    		orderQuantity = EOQuantity - lastInventory; 
		    		orderCount++;
		    		inventoryAmount += orderQuantity - demand;
		    		lastOrderQuantity = orderQuantity;
	    		}
	    		else
	    		{                                //Normal order week
		    		orderQuantity = EOQuantity;
		    		orderCount++;
		    		inventoryAmount += orderQuantity - demand;
	    		}
	    	}
    	}
    	//Calculate and display cost + profit
    	if(storeNumber == 1)
    	{
     		cost = LambtonStore.calculateCost(name,lastOrderQuantity,totalInventoryAmount,orderCount);	   	    
        	profit = LambtonStore.calculateProfit(name,cost,week);
    	}
        else if(storeNumber == 2)
        {
     		cost = CallaghanStore.calculateCost(name,lastOrderQuantity,totalInventoryAmount,orderCount);	   	    
        	profit = CallaghanStore.calculateProfit(name,cost,week);
        }
    	if(profit >=0)
    		System.out.println("The total cost for this product is $"+cost
    			              +". The total profit is $"+profit+". \n");
    	else if(profit < 0)    //Display as a loss
    		System.out.println("The total cost for this product is $"+cost
			                  +". The total loss is $"+profit+". \n");
    	returnToMenu(storeNumber);
    }

	/*
	 * Name: displayAllProducts(int storeNumber)
	 * Parameters: store number (1: Lambton, 2: Callaghan)
	 * Description: A method to display data of all products in a store.
	 * Pre-conditions: The method is called from displayStoreMenu(int storeNumber) method.
	 * Post-conditions: The method will display data of all products in chosen store, sorted by user 
	 *                  choice (by demand or name)
	 */ 
    public static void displayAllProducts(int storeNumber)
    {
    	int productCount = checkStoreFull(storeNumber);
    	//Ask if user wants to view products by name or demand
    	if(productCount > 0)
    		System.out.println("Do you want to sort the products by name (N) or demand (D)? "
    				         + "Please enter N or D: ");
    	    String answer = validateStringInput(3);
    	//Lambton Store
	    if(storeNumber == 1)  
	    {
	    	System.out.println("\nProducts of Lambton Store. Money units are displayed in ($).\n");
	    	//sorting
	    	if(answer.equals("d"))
	    		LambtonStore.sortProducts("demand"); 
	    	else if(answer.equals("n"))
	    		LambtonStore.sortProducts("name");
	    }
	    //Callaghan Store
		if(storeNumber == 2) 
		{
	    	System.out.println("\nProducts of Callaghan Store. Money units are displayed in ($).\n");
	    	//sorting
	    	if(answer.equals("d"))
	    		CallaghanStore.sortProducts("demand");
	    	else if(answer.equals("n"))
	    		CallaghanStore.sortProducts("name"); 
		}
		returnToMenu(storeNumber);
    }
    
	/*
	 * Name: displayStore()
	 * Description: A method to display product count, names in each store
	 * Pre-conditions: The method is called from run() method when option 2 is selected.
	 * Post-conditions: The method will display general data of all products in each storer 
	 */
	public static void displayStore()
	{
		//Lambton Store
		int productCount = LambtonStore.countStoreProducts();
		System.out.println("Store: Lambton\n" + "\tNumber of products: " + productCount);
		if(productCount > 0)
			System.out.println(LambtonStore.toString());      //Use of toString() method in Store Class
		//Callaghan Store
		productCount = CallaghanStore.countStoreProducts();
		System.out.println("Store: Callaghan\n" + "\tNumber of products: " + productCount);
		if(productCount > 0)
			System.out.println(CallaghanStore.toString());    //Use of toString() method in Store Class
		returnToMenu(0);
	}
	
	/*
	 * Name: readProductFile()
	 * Description: A method to read data of new products from file chosen by user.
	 * Pre-conditions: The method is called from run() method when option 3 is selected.
	 * Post-conditions: The method will add these new products to required stores.
	 */
    public static void readProductFile()
    {
    	//User input of file name
    	System.out.println("Please enter the file name: ");
    	String fileName = console.next().concat(".dat");
    	System.out.println();
    	Scanner inputStream = null;
    	//Handling exceptions
    	try
    	{
    		inputStream = new Scanner (new File (fileName));
    	}
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Error opening the file " + fileName);
    		readProductFile();
    	}
    	//Read each line from the file
    	if (inputStream.hasNextLine())
    	{
    		//Declare variables for later uses
    		String nextLine = inputStream.nextLine(),
    			   store, name;
    	    int    demand;
    	    double setupCost, unitCost, inventoryCost, sellingPrice;
    	    //If the next line read is a store name
    		while(nextLine.equals("Lambton:") || nextLine.equals("Callaghan:") && inputStream.hasNextLine())
    		{
    			store = nextLine;
	    		inputStream.nextLine();
    			nextLine = inputStream.nextLine().substring(6);
    			//If the next line read is data of a product
    			while(nextLine.equals("Lambton:") == false && nextLine.equals("Callaghan:") == false 
    				  && inputStream.hasNextLine())
    			{
    				name = nextLine;
    	    		demand = Integer.parseInt(inputStream.nextLine().substring(13));
    	    		setupCost = Double.parseDouble(inputStream.nextLine().substring(12));
    	    	    unitCost = Double.parseDouble(inputStream.nextLine().substring(11));
    	    	    inventoryCost = Double.parseDouble(inputStream.nextLine().substring(16));
    	    	    sellingPrice = Double.parseDouble(inputStream.nextLine().substring(15));
                    //Overwrite product with similar name (if any) with new product
    	    	    if(store.equals("Lambton:"))
    	    	    {
    	    	    	LambtonStore.removeProduct(name);
        	    	    LambtonStore.addNewProduct(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);
    	    	    }
    	    	    if(store.equals("Callaghan:"))
    	    	    {
    	    	    	CallaghanStore.removeProduct(name);
    	    	    	CallaghanStore.addNewProduct(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);
    	    	    }
    	    		//Display summary of products in file      	    	
    	    	    System.out.println(store + "\t" + name);
    	    	    //If next line after space is not a product name   
                    if(inputStream.hasNextLine())
    	    	    {
        	    	    inputStream.nextLine();
        	            nextLine = inputStream.nextLine();
        	    	    if(nextLine.equals("Lambton:") == false && nextLine.equals("Callaghan:") == false)
        	    			nextLine = nextLine.substring(6); 
    	    		}    	    		   	    			   	    
    			}
    		}
    	}	
       	inputStream.close();
       	System.out.println("These products have been added successfully!\n");
       	returnToMenu(0);
    }
    
	/*
	 * Name: writeProductFile()
	 * Description: A method to save all data of stores into a file (.dat)  
	 * Pre-conditions: The method is called from run() method when option 4 is selected.
	 * Post-conditions: The method will create a new file, or overwrite existing file 
	 *                   with all current data in each store
	 */ 
    public static void writeProductFile()
    {
    	//User input of file name
    	System.out.println("\nPlease enter the file name: ");
    	String fileName = console.next().concat(".dat");
    	System.out.println();
    	PrintWriter outputStream = null; 
        //Handling exceptions
    	try
    	{
    		outputStream = new PrintWriter(fileName);
    	}
    	catch (FileNotFoundException e)
    	{
            System.out.println ("Error opening the file " + fileName);
            writeProductFile();
    	}
    	//Write products of Lambton store into file
    	outputStream.println("Lambton:\n"); //Store nanme
    	int arrayLength = LambtonStore.getProductArrayLength();
    	for(int index=0; index<arrayLength; index++)
    	{
    		String name = LambtonStore.getName(index);
    		if(name.equals("null") == false)
    		{
    			//Details of each product
    			outputStream.println("Name: " + name + "\n"
    					           + "demand rate: " + LambtonStore.getDemand(name) + "\n"
    					           + "setup cost: " + LambtonStore.getSetupCost(name) + "\n"
    					           + "unit cost: " + LambtonStore.getUnitCost(name) + "\n"
    					           + "inventory cost: " + LambtonStore.getInventoryCost(name) + "\n"
    					           + "selling price: " + LambtonStore.getPrice(name) + "\n");
    			System.out.println("Lambton: " + name);
    		}
    	}
    	//Write products of Callaghan store into file
    	outputStream.println("Callaghan:\n");  //Store name  	
    	arrayLength = CallaghanStore.getProductArrayLength();
    	for(int index=0; index<arrayLength; index++)
    	{
    		String name = CallaghanStore.getName(index);
    		if(name.equals("null") == false)
    		{
    			//Details of each product
    			outputStream.println("Name: " + name + "\n"
    					           + "demand rate: " + CallaghanStore.getDemand(name) + "\n"
    					           + "setup cost: " + CallaghanStore.getSetupCost(name) + "\n"
    					           + "unit cost: " + CallaghanStore.getUnitCost(name) + "\n"
    					           + "inventory cost: " + CallaghanStore.getInventoryCost(name) + "\n"
    					           + "selling price: " + CallaghanStore.getPrice(name) + "\n");
    			System.out.println("Callaghan: " + name);
    		}
    	}
    	System.out.println("These products have been saved to the file " + fileName + " successfully.\n");
    	outputStream.close();
    	returnToMenu(0);
    }
    
    //A method to check if a product exists in a store by name provided
    public static boolean checkProduct(String name, int storeNumber)
    {
    	boolean productExist = true;
    	if(storeNumber == 1) 
    		productExist = LambtonStore.checkProduct(name);   //Check if a product exists in Lambton store
    	else if(storeNumber == 2) 
    		productExist = CallaghanStore.checkProduct(name); //Check if a product exists in Callaghan store
    	return productExist;
    }
    
    //A method to count and return the number of products in a store to caller
    public static int checkStoreFull(int storeNumber)
    {
    	int productCount = 0;
       	if(storeNumber == 1) productCount = LambtonStore.countStoreProducts();
    	else if(storeNumber == 2) productCount = CallaghanStore.countStoreProducts();
    	if(productCount == 0)
    	{
    		System.out.println("There is no product in this store. \n");
    		returnToMenu(storeNumber);
    	}
    	return productCount;
    }
    
	//A method to get, validate and return name input of a product from the user to caller.
    public static String validateNameInput(int type)
    {
    	if(type == 1)
    		System.out.println("Enter the name of product: ");
    	else if(type == 2)
        	System.out.println("Please enter a new name for the product: ");
    	String name = console.next().toLowerCase();
    	while(name.length() < 3 || name.length() > 10)  //Validate name length
    	{ 
    		System.out.println("Name must be between 3-10 characters. Please enter again: ");
    		name = console.next().toLowerCase();
    	}
    	return name;
    }
    
	//A method to get, validate and return integer input from the user to the caller.
	public static int validateIntegerInput(int type)
	{
		int integerInput = console.nextInt();
		if(type == 1)
			while (integerInput != 1 && integerInput != 2 && integerInput != 3 && integerInput != 4 && integerInput != 5) 
			{	
			    System.out.println("Invalid option. Please select one of the above options: ");    
			    integerInput = console.nextInt();
			}
		if(type == 2)
	    	while(integerInput < 1)
	    	{
		    	System.out.println("Number of weeks must be 1 or more. Please enter again: ");
		    	integerInput = console.nextInt();
	    	}
		return integerInput;
	}
	
	//A method to get, validate and return string input from the user to the caller.
	public static String validateStringInput(int type)
	{
		String userInput = console.next().toLowerCase();
		if(type == 1)
			while(userInput.equals("lambton") == false && userInput.equals("callaghan") == false)
			{
				System.out.println("Invalid input. Please select one of the above store: ");
				userInput = console.next().toLowerCase();
			}
		if(type == 2)
			while(userInput.equals("yes") == false && userInput.equals("no") == false)
			{
				System.out.println("Invalid input. Please enter yes or no: ");
				userInput = console.next().toLowerCase();
			}
		if(type == 3)
			while(userInput.equals("n") == false && userInput.equals("d") == false)
			{
				System.out.println("Invalid input. Please enter N or D: ");
				userInput = console.next().toLowerCase();
			}
		return userInput;
	}
    
    //A method to ask the user to press Enter to return to the Main or Store menu.
    public static void returnToMenu(int storeNumber)
    {
    	if(storeNumber == 0)
    	{
        	System.out.println("Press Enter to return to the Main menu.");
        	try
            {
        		System.in.read();
        		main(null);
            }  
            catch(Exception e)
            {}  
            main(null);
    	}
    	else
    	{
        	System.out.println("Press Enter to return to the Store menu.");
        	try
            {
        		System.in.read();
        		displayStoreMenu(storeNumber);
            }  
            catch(Exception e)
            {}  
        	displayStoreMenu(storeNumber);
    	}
    }
}
