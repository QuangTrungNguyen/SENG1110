/** 
File name: Store.java
Author: Nguyen Quang Trung
Student number: c3198416
E-mail Address: quangtrung.nguyen@uon.edu.au
Programming Assignment 1
Last Changed: April 22, 2016
Description: The file contains Store class which acts as a bridge between
the Product class and the StarbekrsInterface class. It has methods to use
Product class's methods to access and modify the data in a product object. 
*/ 

public class Store {
	
	//Create 3 Product objects as instance variables
    private  Product product1 = new Product();
    private  Product product2 = new Product();
    private  Product product3 = new Product();
    
	public Store () 
	{
	}
	
	/*
	 * Name: checkSystemFull() 
	 * Parameters: none
	 * Return: Integer - 3 if 3 product slots are available, 1 if any product 
	 *         slot is/are available, 0 if the system is full.
	 * Description: A method to check if the system is full by checking whether the 
	 *              name of a product is "null".
	 * Pre-conditions: none.
	 * Post-conditions: Returns an integer indicating whether the system is full 
	 *                  or how many spaces are available.
	 */
    public int checkSystemFull() 
    {
    	if(product1.getName().equals("null") && product2.getName().equals("null") 
    	                                     && product3.getName().equals("null")) 	
    		return 3; //all available
    	else if(product1.getName().equals("null") || product2.getName().equals("null") 
    	                                          || product3.getName().equals("null"))   
    		return 1; //any available
		else
		    return 0; //unavailable
    }
	   
	/*
	 * Name: checkProduct(String name)
	 * Parameters: name of a product
	 * Return: boolean - true if the product exists, false if it doesn't exist. 
	 * Description: A method to check if a product exists in the system
	 * Pre-conditions: The user must input a name and it is passed into the method's parameter.
	 * Post-conditions: Returns an boolean value indicating whether the product exists
	 *                  in the system.
	 */
    public boolean checkProduct(String name)
    {  	
    	if(name.equals(product1.getName()) || name.equals(product2.getName()) 
    			                           || name.equals(product3.getName()))
    		return true;
    	else
    		return false;
    }
    
	/*
	 * Name: addNewProduct (String name, int demand, double setupCost, 
    		                   double inventoryCost, double unitCost, double sellingPrice) 
	 * Parameters: name, demand rate, setup cost, inventory cost, unit cost, selling price of a product
	 * Return: none
	 * Description: A method to create a new product 
	 * Pre-conditions: The user must input a name, demand rate, setup cost, inventory cost, unit cost, 
	 *                 selling price of the product. They must be passed into the method's parameters.
	 * Post-conditions: A "null" product object data is modified with new values.
	 */
    public void addNewProduct (String name, int demand, double setupCost, 
    		                   double inventoryCost, double unitCost, double sellingPrice) 
    {
        if(product1.getName().equals("null")) 
        {
        	product1.setName(name);
        	product1.setDemand(demand);
        	product1.setSetupCost(setupCost);
        	product1.setInventoryCost(inventoryCost);
        	product1.setUnitCost(unitCost);
        	product1.setPrice(sellingPrice);
        }
        else if(product2.getName().equals("null")) 
        {
        	product2.setName(name);
        	product2.setDemand(demand);
        	product2.setSetupCost(setupCost);
        	product2.setInventoryCost(inventoryCost);
        	product2.setUnitCost(unitCost);
        	product2.setPrice(sellingPrice);
        }
        else if(product3.getName().equals("null")) 
        {
        	product3.setName(name);
        	product3.setDemand(demand);
        	product3.setSetupCost(setupCost);
        	product3.setInventoryCost(inventoryCost);
        	product3.setUnitCost(unitCost);
        	product3.setPrice(sellingPrice);
        } 
    }
    
	/*
	 * Name:  updateProductData (String name, int demand, double setupCost, 
    		                       double inventoryCost, double unitCost, double sellingPrice) 
	 * Parameters: valid existing name, demand rate, setup cost, inventory cost, unit cost, selling price of a product
	 * Return: none
	 * Description: A method to modify values of an existing product.
	 * Pre-conditions: The user must input a name, demand rate, setup cost, inventory cost, unit cost, 
	 *                 selling price of the product. They must be passed into the method's parameters.
	 * Post-conditions: An existing product object, with the name = name the user provides,
	 *                  is modified with new data values.
	 */
    public void updateProductData (String name, int demand, double setupCost, 
    		                       double inventoryCost, double unitCost, double sellingPrice) 
    {
        if(product1.getName().equals(name)) 
        {
        	product1.setName(name);
        	product1.setDemand(demand);
        	product1.setSetupCost(setupCost);
        	product1.setInventoryCost(inventoryCost);
        	product1.setUnitCost(unitCost);
        	product1.setPrice(sellingPrice);
        }
        else if(product2.getName().equals(name)) 
        {
        	product2.setName(name);
        	product2.setDemand(demand);
        	product2.setSetupCost(setupCost);
        	product2.setInventoryCost(inventoryCost);
        	product2.setUnitCost(unitCost);
        	product2.setPrice(sellingPrice);
        }
        else if(product3.getName().equals(name)) 
        {
        	product3.setName(name);
        	product3.setDemand(demand);
        	product3.setSetupCost(setupCost);
        	product3.setInventoryCost(inventoryCost);
        	product3.setUnitCost(unitCost);
        	product3.setPrice(sellingPrice);
        } 
    }
    
	/*
	 * Name:  changeName(String name, String newName)
	 * Parameters: valid existing name, new name of a product
	 * Return: none
	 * Description: A method to change name of an existing product.
	 * Pre-conditions: The user must input a existing name and a new name for the product. The existing name and
	 *                 new name must be validated before being passed into the method's parameters.
	 * Post-conditions: The name of an existing product will be changed to the new name provided.
	 */
    public void changeName(String name, String newName)
    {
        if(product1.getName().equals(name))
        	product1.setName(newName);
        else if (product2.getName().equals(name))
        	product2.setName(newName);
        else if (product3.getName().equals(name))
        	product3.setName(newName);
    }
    
	/*
	 * Name:  removeProduct(int productIndex)
	 * Parameters: Index of a product
	 * Return: none
	 * Description: A method to remove an existing product by simply setting its name to "null".
	 * Pre-conditions: The user must choose a product index (1, 2 or 3).
	 *                 The product index is then passed into the method's parameters.
	 * Post-conditions: The name of an existing product will be changed to "null".
	 */
    public void removeProduct(int productIndex) 
    {
    	if(productIndex == 1)
        	product1.setName("null");
    	else if(productIndex == 2)
        	product2.setName("null");
    	else if(productIndex == 3)
        	product3.setName("null");
    }
    
	/*
	 * Name:  calculateEOQuantity (String name)
	 * Parameters: Name a product
	 * Return: Integer - EO Quantity of a product
	 * Description: A method to calculate the EO Quantity of a product.
	 * Pre-conditions: The user must input a valid product name.
	 *                 The name is then passed into the method's parameters.
	 * Post-conditions: The EOQuantity of a product is calculated and returned.
	 */
    public int calculateEOQuantity (String name)
    {
    	int    demand = getDemand(name),
               EOQuantity;
        double setupCost = getSetupCost(name),
    	       inventoryCost = getInventoryCost(name);
    	EOQuantity = (int) Math.round(Math.sqrt((2*setupCost*demand) / inventoryCost));  	
    	return EOQuantity;
    }
    
	/*
	 * Name:  calculateProfit(String name, int lastOrderQuantity, 
    		                      int totalInventoryAmount, int orderCount, int week) 
	 * Parameters: Name, last order quantity, total inventory amount, order count, week of a product
	 * Return: Double - profit of a product
	 * Description: A method to calculate the profit of a product.
	 * Pre-conditions: The user must input a valid product name and number of week
	 * Post-conditions: The profit of a product is calculated and returned.
	 */
    public double calculateProfit(String name, int lastOrderQuantity, 
    		                      int totalInventoryAmount, int orderCount, int week) 
    {
    	double cost = 0,
    	       profit = 0,
    	       setupCost = getSetupCost(name),
    	       unitCost = getUnitCost(name),
    	       inventoryCost = getInventoryCost(name),
    	       sellingPrice = getPrice(name);
    	int    demand = getDemand(name),
    	       EOQuantity = calculateEOQuantity(name);
    	cost = (setupCost * orderCount)  //Total product cost is calculated
    		 + ((EOQuantity * (orderCount - 1) + lastOrderQuantity) * unitCost) 
    		 + (totalInventoryAmount * inventoryCost);
        profit = (demand * week * sellingPrice) - cost; //Profit is calculated based on cost
    	return profit;
    }
    

    /*Methods to access data in a product object*/
    
	public int getDemand(String name) 
	{
		int demand = 0;
        if(product1.getName().equals(name))
    	    demand = product1.getDemand();
        else if (product2.getName().equals(name))
        	demand = product2.getDemand();
        else if (product3.getName().equals(name))
        	demand = product3.getDemand();
		return demand;
	}
	public double getSetupCost(String name) 
	{
		double setupCost = 0; 
        if(product1.getName().equals(name))
        	setupCost = product1.getSetupCost();
        else if (product2.getName().equals(name))
        	setupCost = product2.getSetupCost();
        else if (product3.getName().equals(name))
        	setupCost = product3.getSetupCost();
		return setupCost;
	}
	public double getInventoryCost(String name) 
	{
		double inventoryCost = 0;
        if(product1.getName().equals(name))
        	inventoryCost = product1.getInventoryCost();
        else if (product2.getName().equals(name))
        	inventoryCost = product2.getInventoryCost();
        else if (product3.getName().equals(name))
        	inventoryCost = product3.getInventoryCost();
		return inventoryCost;
	}
	public double getUnitCost(String name) 
	{
		double unitCost = 0;
        if(product1.getName().equals(name))
        	unitCost = product1.getUnitCost();
        else if (product2.getName().equals(name))
        	unitCost = product2.getUnitCost();
        else if (product3.getName().equals(name))
        	unitCost = product3.getUnitCost();
		return unitCost;
	}
	public double getPrice(String name) 
	{
		double sellingPrice = 0;
        if(product1.getName().equals(name))
        	sellingPrice = product1.getPrice();
        else if (product2.getName().equals(name))
        	sellingPrice = product2.getPrice();
        else if (product3.getName().equals(name))
        	sellingPrice = product3.getPrice();
		return sellingPrice;
	}
}
