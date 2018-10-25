/** 
File name: StarberksInterface.java
Author: Nguyen Quang Trung
Student number: c3198416
E-mail Address: quangtrung.nguyen@uon.edu.au
Programming Assignment 1
Last Changed: April 22, 2016
Description: The file contains StarberksInterface class which acts as the interface
for the program. It will receive input from the user, display all output, check
invalid inputs and display all error messages. It uses Store class's methods to
access and modify the data in a product object of the Product class.
*/ 

import java.util.*;
public class StarberksInterface 
{
	
    static Scanner console = new Scanner(System.in);  
    //Create a Store object as instance variables
    static Store s1 = new Store();
    
    /*Declare variables to hold the name and profit 
      of up to 3 products with the highest profit.*/
    private static String nameBest1 = "N/A",
    		              nameBest2 = "N/A",
    		              nameBest3 = "N/A";
    private static double highestProfit1 = 0,
    		              highestProfit2 = 0,
    			          highestProfit3 = 0;
	
    //Main method to call the run() method
	public static void main(String[] args) 
	{    
		StarberksInterface intFace = new StarberksInterface();
        intFace.run();
    }
	
	/*
	 * Name:  run()
	 * Parameters: none
	 * Return: none
	 * Description: A method to display the menu and ask for user option.
	 * Pre-conditions: The method is called from the main method when the file is run.
	 * Post-conditions: The method will call other methods according to the user option.
	 */
	private void run() 
	{
		System.out.println("Welcome to Starberk's Coffee! \n\n"
		    	+ "(1) Add a new product \n"
		    	+ "(2) Display information of a product \n"
		    	+ "(3) Display replenishment strategy for a product \n"
		    	+ "(4) Exit the program \n\n"
		    	+ "Please select one of the above options: ");    
	    int option = console.nextInt();
		while (option != 1 && option != 2 && option != 3 && option != 4) 
		{	
		    System.out.println("Invalid option. Please select one of the above options: ");    
		    option = console.nextInt();
		}
		switch (option)
		{
		    case 1: addNewProduct();
		                break;
		    case 2: showProduct();
		        	    break;
		    case 3: showReplenishmentStrategy();
		        	    break;
		    case 4: exitProgram();
		        	    break;              
		}
	}
	
	/*
	 * Name:  addNewProduct() 
	 * Parameters: none
	 * Return: none
	 * Description: A method to ask the user to input the name of a product to add a new /remove a product
	 * Pre-conditions: The method is called from the run() method.
	 * Post-conditions: The method will call other methods according to the user inputs
	 */
	public static void addNewProduct() 
	{
	    int systemAvailable = s1.checkSystemFull();
	    if (systemAvailable == 0) 
	    {
	    	System.out.println("The system is full. New product cannot be added. \n"
	    			         + "(1) Remove a product \n"
		    		         + "(2) Return to the main menu \n"
		    		         + "Please select an option: ");
		    int option = console.nextInt();
		    while (option != 1 && option != 2) 
		    {	
		        System.out.println("Invalid option. Please select one of the above options: ");    
		        option = console.nextInt();
		    }
		    switch (option)
		    {
		        case 1: removeProduct();
		                break;
		        case 2: main(null); 
		        	    break;             
		    }
	    }
	    else 
	    {	    	
	    	System.out.println("Enter the name of product: ");
	    	String name = console.next().toLowerCase();
	    	while (name.length() < 3 || name.length() > 8)   //Validate name length
	    	{ 
	    		System.out.println("Name must be between 3-8 characters. Please enter again: ");
	    		name = console.next().toLowerCase();
	    	}
	    	boolean productAvailable = s1.checkProduct(name); //Check if product is available
	    	if(productAvailable == true) 
	    	{
	    		System.out.println("This product already existed. \n"
	    			  + "Do you want to change its name (N) or its data (D)? (Enter N or D): ");
	    		String answer = console.next();
	    		if(answer.equals("N")) changeName(name);
	    		else if (answer.equals("D")) inputProductData(name,0); // 0 indicates modifying existing product
	    	}
	    	else
	    		inputProductData(name,1); //1 indicates creating a new product
	    }	    
    }
	
	/*
	 * Name:  inputProductData(String name, int option)
	 * Parameters: product name, and option (as explained in addNewProduct() method)
	 * Return: none
	 * Description: A method to ask the user to input the data of a product: demand rate, inventory cost,
	 *              unit cost, setup cost and selling price.
	 * Pre-conditions: The method is called from the addNewProduct() method with valid name and option 
	 *                 passed to its parameters.
	 * Post-conditions: The method will update data of a product
	 */
	public static void inputProductData(String name, int option)
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
        	s1.addNewProduct(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);  
    		System.out.println("Product " + name + " was added successfully! \n");
    	}
    	else if(option == 0) //Data of an existing product will be modified
    	{
    		s1.updateProductData(name,demand,setupCost,inventoryCost,unitCost,sellingPrice);
    		System.out.println("Product " + name + " has been updated successfully! \n");
    	}
    	returnToMainMenu();
	}
	
	/*
	 * Name:  changeName(String name) 
	 * Parameters: A valid existing product name.
	 * Return: none
	 * Description: A method to change name of an existing product.
	 * Pre-conditions: The method is called from the addNewProduct() method with valid name of an existing product.
	 * Post-conditions: The method will update name of a product
	 */
    public static void changeName(String name) 
    {
    	System.out.println("Please enter a new name for the product: ");
    	String newName = console.next().toLowerCase();
    	while (newName.length() < 3 || newName.length() > 8) 
    	{
    		System.out.println("Name must be between 3-8 characters. Please enter again: ");
    		newName = console.next().toLowerCase();
    	}
    	s1.changeName(name,newName);
		System.out.println("Product " + name + " has been changed to " + newName +".\n");
		returnToMainMenu();
    }
    
	/*
	 * Name:  removeProduct() 
	 * Parameters: none
	 * Return: none
	 * Description: A method to remove an existing product. User input option 1,2 or 3.
	 * Pre-conditions: The method is called from the addNewProduct() method.
	 * Post-conditions: The method will remove an existing product according to the user input of the product index
	 */
    public static void removeProduct()
    {
		System.out.println("Please select which product you want to remove (Enter 1,2 or 3): ");
		int productIndex = console.nextInt();
		s1.removeProduct(productIndex);
		System.out.println("Product " + productIndex 
				         + " has been removed. Now you can add a new product. \n");
		addNewProduct();
    }
    
	/*
	 * Name:  showProduct() 
	 * Parameters: none
	 * Return: none
	 * Description: A method to display data of an existing product. User input a valid name.
	 * Pre-conditions: The method is called from the main menu from run() method.
	 * Post-conditions: The method will display data of an existing product according to the user input of the 
	 *                   product name.
	 */
    public static void showProduct() 
    {
	    int systemAvailable = s1.checkSystemFull();
	    if (systemAvailable == 3) 
	    {
	    	System.out.println("There is no product data. \n");
	    	returnToMainMenu();
	    }
	    else
	    {
	    	System.out.println("Please enter the name of the product: ");
	    	String name = console.next().toLowerCase();
	    	name = checkNameInput(name);
		    int demand = s1.getDemand(name);
		    double setupCost = s1.getSetupCost(name),
		    	   inventoryCost = s1.getInventoryCost(name),
		    	   unitCost = s1.getUnitCost(name),
		    	   sellingPrice = s1.getPrice(name);
		    System.out.println("Information about product " + name + ":\n" 
		    	             + "Demand rate: \t" + demand + "\n" 
		    	    	     + "Setup cost: \t$" + setupCost + " (per order)\n" 
		    	             + "Inventory cost: $" + inventoryCost + " (per unit, per week)\n" 
		    	    	     + "Unit cost: \t$" + unitCost + "\n" 
		    	             + "Selling price:  $" + sellingPrice + " (per item)\n");
	    	returnToMainMenu();
	    }	    	
    }

	/*
	 * Name:  showReplenishmentStrategy()
	 * Parameters: none
	 * Return: none
	 * Description: A method to display Replenishmen tStrategy of an existing product. User input a valid name and
	 *             number of week.
	 * Pre-conditions: The method is called from the main menu from run() method.
	 * Post-conditions: The method will display Replenishmen tStrategy of an existing product according to the user input of the 
	 *                   product name and week
	 */
    public static void showReplenishmentStrategy()
    {
	    int systemAvailable = s1.checkSystemFull();
	    if (systemAvailable == 3) 
	    {
	    	System.out.println("There is no product data. \n");
	    	returnToMainMenu(); 
	    }
	    else
	    {
	    	System.out.println("Enter the name of product: ");
	    	String name = console.next().toLowerCase();
            name = checkNameInput(name);

	    	System.out.println("Please enter the number of weeks: ");  //Ask user for number of weeks
	    	int week = console.nextInt();
	    	while(week < 1)
	    	{
		    	System.out.println("Number of weeks must be 1 or more. Please enter again: ");
		    	week = console.nextInt();
	    	}
	    	
	    	//Declare variables
	    	int EOQuantity = s1.calculateEOQuantity(name), // Get the EO Quantity
	    	    demand = s1.getDemand(name),
	    	    inventoryAmount = EOQuantity,
	    	    totalInventoryAmount = 0,  //total inventory amount of a product in a run
	    	    orderQuantity = EOQuantity,//total inventory order quantity of a product in a run
	    	    orderCount = 0,            //total inventory oder count
	    	    lastOrderQuantity = 0,     
	    	    lastOrderWeek = 0,
	    	    lastInventory = 0;        //last week inventory amount
	    	double profit;
	    	    
	    	if(EOQuantity < demand)
	    	{
		    	System.out.println("Error: It is not possible to have a replenishment strategy "
		    			         + "with the inputs given for this product! \n" 
		    				     + "Do you want to change the data for this product? (Y/N): ");
		    	String answer = console.next();
		    	if(answer.equals("N") || answer.equals("n") )
		    		main(null);
		    	else if(answer.equals("Y") || answer.equals("y"))
		    		inputProductData(name,0);
	    	}
	    	    
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
	    	    
	    	System.out.println("Week \t" + "Quantity Order \t" 
	    	                 + "Demand \t" + "Inventory \t\n" );
	    	for(int n=1; n<=week; n++)
	    	{
		    	totalInventoryAmount += inventoryAmount;
		    	System.out.println(n + "\t" + orderQuantity + "\t\t" + demand 
		    				         + "\t" + inventoryAmount + "\t\n");		    		
		    	if(inventoryAmount >= demand) 
		    	{
		    		orderQuantity = 0;        //Non-order week
		    		inventoryAmount -= demand; 
		    	}
		    	else
		    	{
		    		if(n == lastOrderWeek - 1)   //Modify the order quantity of the last order week
		    		{                            //To have the last week inventory to be 0.
		    			                         //This works for all user input of number of weeks.
			    		orderQuantity = EOQuantity - lastInventory;
			    		orderCount++;
			    		inventoryAmount += orderQuantity - demand;
			    		lastOrderQuantity = orderQuantity;
		    		}
		    		else
		    		{                                 //Normal order week
			    		orderQuantity = EOQuantity;
			    		orderCount++;
			    		inventoryAmount += orderQuantity - demand;
		    		}
		    	}
	    	}
	    	    
	    	profit = s1.calculateProfit(name,lastOrderQuantity,totalInventoryAmount,orderCount,week);
	    	if(profit > highestProfit1)
	    	{
	    	    highestProfit1 = profit;
	    	    nameBest1 = name;
	    	}
	    	else if(profit == highestProfit1 && profit == highestProfit2)
	    	{
	    	    highestProfit3 = profit;
	    	    nameBest3 = name;
	    	}
	    	else if(profit == highestProfit1)
	    	{
	    	    highestProfit2 = profit;
	    	    nameBest2 = name;
	    	}
	    }
	    returnToMainMenu();
	}
    
	/*
	 * Name:  exitProgram()
	 * Parameters: none
	 * Return: none
	 * Description: A method to exit the program or display name and profit of up to 3 products with the highest profit
	 *               if they are available. 
	 * Pre-conditions: The method is called from the main menu from run() method.
	 * Post-conditions: The method will exit the program or display name and profit of up to 3 products with the highest profit
	 *                  if they are available then exit the program. 
	 */
    public static void exitProgram()
    {
    	boolean productExist1 = s1.checkProduct(nameBest1),
    	     	productExist2 = s1.checkProduct(nameBest2),
    		    productExist3 = s1.checkProduct(nameBest3);
    	if(productExist1 == false && productExist2 == false && productExist3 == false 
    			|| highestProfit1 == 0 && highestProfit2 == 0 && highestProfit3 == 0)
    		System.exit(0);
    	else
    	{
    		if(productExist1 == true 
    				&& highestProfit1 > highestProfit2 && highestProfit1 > highestProfit3)
    		{
    			System.out.println("Product " + nameBest1 + " has the best replenishment strategy. "
    				             + "The projected profit of the product is $" + highestProfit1 +".\n");		    
    		}
    		else if(productExist2 == true 
    				&& highestProfit2 > highestProfit1 && highestProfit2 > highestProfit3)
    		{
    			System.out.println("Product " + nameBest2 + " has the best replenishment strategy. "
    				             + "The projected profit of the product is $" + highestProfit2 +".\n");		    
    		}
    		else if(productExist3 == true 
    				&& highestProfit3 > highestProfit2 && highestProfit3 > highestProfit1)
    		{
    			System.out.println("Product " + nameBest3 + " has the best replenishment strategy. "
    				             + "The projected profit of the product is $" + highestProfit3 +".\n");		    
    		}
    		else if(productExist1 == true && productExist2 == true && productExist3 == true 
        	        && highestProfit1 == highestProfit2 && highestProfit1 == highestProfit3)
        	{
        		System.out.println("Product " + nameBest1 + ", " + nameBest2 + " and " + nameBest3
        				         + " have the best replenishment strategy. "
        				         + "The projected profit of the 3 products is $" + highestProfit1 +".\n");		    
        	}
    		else if(productExist1 == true && productExist2 == true 
    				&& highestProfit1 == highestProfit2)
    		{
    			System.out.println("Product " + nameBest1 + " and " + nameBest2 
    				             + " have the best replenishment strategy. "
    				             + "The projected profit of the 2 products is $" + highestProfit1 +".\n");		    
    		}
    		else if(productExist1 == true && productExist3 == true 
    				&& highestProfit1 == highestProfit3)
    		{
    			System.out.println("Product " + nameBest1 + " and " + nameBest3 
    				             + " have the best replenishment strategy. "
    				             + "The projected profit of the 2 products is $" + highestProfit1 +".\n");		    
    		}
    		else if(productExist2 == true && productExist3 == true 
    				&& highestProfit2 == highestProfit3)
    		{
    			System.out.println("Product " + nameBest2 + " and " + nameBest3 
    				             + " have the best replenishment strategy. "
    				             + "The projected profit of the 2 products is $" + highestProfit2 +".\n");		    
    		}
    		System.exit(0);
    	}
    }
    
    // Method to validate name input of a product from the user.
    public static String checkNameInput(String name)
    {
    	while(name.length() < 3 || name.length() > 8) 
    	{ 
    		System.out.println("Name must be between 3-8 characters. Please enter again: ");
    		name = console.next().toLowerCase();
    	}
    	boolean productExist = s1.checkProduct(name);
    	while(productExist == false)
    	{
    		System.out.println("This product does not exist. Please enter the product name again: ");
    	    name = console.next().toLowerCase();
    	    productExist = s1.checkProduct(name);
    	}
    	return name;
    }
    
    // Method to ask the user to press Enter to return to the main menu.
    public static void returnToMainMenu()
    {
        System.out.println("Press Enter to return to the main menu.");
        try
        {
            System.in.read();
            main(null);
        }  
        catch(Exception e)
        {}  
        main(null);
    }
}
