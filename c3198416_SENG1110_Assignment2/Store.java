
/** 
File name: Store.java
Author: Nguyen Quang Trung
Student number: c3198416
E-mail Address: quangtrung.nguyen@uon.edu.au
Programming Assignment 2
Last Changed: May 27, 2016
Description: The file contains Store class which acts as a bridge between
the Product class and the StarbekrsInterface class. It has methods to use
Product class's methods to access and modify the data in a product object. 
*/ 

public class Store {
	
	//Create a Product array  as an instance variable
	private final int DEFAULT_SIZE = 3;
	private Product[] product = new Product[DEFAULT_SIZE];;
	
	//Constructor
	public Store () 
	{
		for(int i=0;i<DEFAULT_SIZE;i++)
			product[i] = new Product();
	}
	
	//A method to get the length of current product array
    public int getProductArrayLength()
    {
    	int productArrayLength = product.length;
    	return productArrayLength;
    }
    
	/*
	 * Name: resizeArray()
	 * Description: A method to resize the current product array.
	 * Pre-conditions: if the current product array is full.
	 * Post-conditions: a new product array with +1 position is created with existing data.
	 */
	public void resizeArray()
	{
		int currentSize = getProductArrayLength();
		//Create a temporary array, copy current product array into it
		Product[] temp = new Product[currentSize];
		for(int i=0;i<currentSize;i++)
		{
			temp[i] = new Product();
			temp[i] = product[i];
		}
		//Create a new product array with +1 position
		product = new Product[currentSize+1];		
		for(int i=0;i<currentSize+1;i++)
			product[i] = new Product();
		for(int i=0;i<currentSize;i++)
			product[i] = temp[i];
	}
	
	/*
	 * Name: countStoreProducts()
	 * Return: number of products in a store.
	 * Description: A method to count and return the number of products in a store.
	 * Pre-conditions: none
	 * Post-conditions: number of products is returned to the caller.
	 */
    public int countStoreProducts()  
    {
    	int productCount = 0;
    	for(int i=0; i<product.length; i++)
    	{
    		if(product[i].getName().equals("null") == false)
    			productCount += 1;
    	}
    	return productCount;
    }
    
	/*
	 * Name: checkProduct(String name)
	 * Parameters: name of a product
	 * Return: boolean - true if the product exists, false if it doesn't exist. 
	 * Description: A method to check if a product exists in the store
	 * Pre-conditions: The user must input a name and it is passed into the method's parameter.
	 * Post-conditions: Returns an boolean value indicating whether the product exists  in the store.
	 */
    public boolean checkProduct(String name)
    {
    	boolean productExist = false;
    	for(int i=0; i<product.length; i++)
    	{
    		if(name.equals(product[i].getName()))
    			productExist = true;
    	}
    	return productExist;
    }
    
	/*
	 * Name: addNewProduct (String name, int demand, double setupCost, 
    		                   double inventoryCost, double unitCost, double sellingPrice) 
	 * Parameters: name, demand rate, setup cost, inventory cost, unit cost, selling price of a product
	 * Description: A method to create a new product 
	 * Pre-conditions: The user must input a name, demand rate, setup cost, inventory cost, unit cost, 
	 *                 selling price of the product. They must be passed into the method's parameters.
	 * Post-conditions: A "null" product object data is modified with new values.
	 */
    public void addNewProduct (String name, int demand, double setupCost, 
    		                   double inventoryCost, double unitCost, double sellingPrice) 
    {
    	int next = 0,
    	    productCount = countStoreProducts();
    	//If product array is full
    	if (productCount == product.length)
    		resizeArray();
    	//Obtain index of next available position
    	for(int i=0; i<product.length; i++)
    	{
    		if(product[i].getName().equals("null")) 
    		{
    			next = i;
    			i = product.length;
    		}
    	}
    	product[next].setName(name);
    	product[next].setDemand(demand);
    	product[next].setSetupCost(setupCost);
    	product[next].setInventoryCost(inventoryCost);
    	product[next].setUnitCost(unitCost);
    	product[next].setPrice(sellingPrice);
    }
    
	/*
	 * Name:  updateProductData (String name, int demand, double setupCost, 
    		                       double inventoryCost, double unitCost, double sellingPrice) 
	 * Parameters: valid existing name, demand rate, setup cost, inventory cost, 
	 *             unit cost, selling price of a product
	 * Description: A method to modify values of an existing product.
	 * Pre-conditions: The user must input a name, demand rate, setup cost, inventory cost, unit cost, 
	 *                 selling price of the product. They must be passed into the method's parameters.
	 * Post-conditions: An existing product object, with the name = name the user provides,
	 *                  is modified with new data values.
	 */
    public void updateProductData (String name, int demand, double setupCost, 
    		                       double inventoryCost, double unitCost, double sellingPrice) 
    {
       	for(int i=0; i<product.length; i++)
       	{
            if(product[i].getName().equals(name)) 
            {
            	product[i].setName(name);
            	product[i].setDemand(demand);
            	product[i].setSetupCost(setupCost);
            	product[i].setInventoryCost(inventoryCost);
            	product[i].setUnitCost(unitCost);
            	product[i].setPrice(sellingPrice);
            }
       	}
    }    
    
	/*
	 * Name:  changeName(String name, String newName)
	 * Parameters: valid existing name, new name of a product
	 * Description: A method to change name of an existing product.
	 * Pre-conditions: The user must input a existing name and a new name for the product. The existing name and
	 *                 new name must be validated before being passed into the method's parameters.
	 * Post-conditions: The name of an existing product will be changed to the new name provided.
	 */
    public void changeName(String name, String newName)
    {
    	for(int i=0; i<product.length; i++)
    	{
            if(product[i].getName().equals(name))
            	product[i].setName(newName);
    	}
    }
    
	/*
	 * Name: removeProduct(int productIndex)
	 * Parameters: Index of a product
	 * Description: A method to remove an existing product by simply setting its name to "null".
	 * Pre-conditions: The user must choose a product index (1, 2 or 3).
	 *                 The product index is then passed into the method's parameters.
	 * Post-conditions: The name of an existing product will be changed to "null".
	 */
    public void removeProduct(String name) 
    {
    	for(int i=0; i<product.length; i++)
    	{
    		if(product[i].getName().equals(name))
    			product[i].setName("null");
    	}
    }
    
	/*
	 * Name: getProductDetails(String name)
	 * Parameters: Product name
	 * Return: String contains details of a product
	 * Description: A method to return details of a product to caller.
	 * Pre-conditions: A valid name of existing product must be provided.
	 * Post-conditions: Details of this product are returned to caller.
	 */
	public String getProductDetails(String name)
	{
		String productDetails = null;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
			{
				//Use of toString method in Product Class
				productDetails = product[i].toString();
			}
		}
		return productDetails;
	}
	
	/*
	 * Name: sortProducts(String sortOption)
	 * Parameters: sorting option by user (demand or name)
	 * Description: A method to sort products by name or demand in a store.
	 * Pre-conditions: The user must input a valid option (name or demand). 
	 *                 Store must have at least 1 product.
	 * Post-conditions: Products are sorted accordingly
	 */
	public void sortProducts(String sortOption)
	{
		int currentSize = product.length,
			sort,
			index;
		//Bubble sorting
		Product[] temp = new Product[1];
		temp[0] = new Product();	
		//Sort by demand, highest first
		if(sortOption == "demand")
		{
			for(sort=currentSize-1; sort>=0; sort--) 
			{
				for(index=0; index<sort; index++)
					if(product[index].getDemand() < product[index+1].getDemand())
					{
						temp[0] = product[index];
						product[index] = product[index+1];
						product[index+1] = temp[0];
					}
			}
		}
		//Sort by names alphabetically
		if(sortOption == "name")
		{
			for(sort=currentSize-1; sort>=0; sort--) 
			{
				for(index=0; index<sort; index++)
					if(product[index].getName().compareTo(product[index+1].getName()) > 0)
					{
						temp[0] = product[index];
						product[index] = product[index+1];
						product[index+1] = temp[0];
					}
			}
		}
		showAllProducts();
	}
	
	/*
	 * Name: showAllProducts()
	 * Description: A method to display details of all products in a store.
	 * Pre-conditions: A store must have at least 1 product.
	 * Post-conditions: Details of a products are displayed
	 */ 	
	public void showAllProducts()
	{
		//Display headings
		System.out.printf("%-12s %-7s %-11s %-10s %-15s %-14s %n",
				          "Name","Demand","Setup Cost","Unit Cost","Inventory Cost","Selling Price");
		//Details of each product
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals("null") == false)
			{
				String name = product[i].getName();
				int    demand = product[i].getDemand();
				double setupCost = product[i].getSetupCost(),
					   unitCost = product[i].getUnitCost(),
					   inventoryCost = product[i].getInventoryCost(),
					   price = product[i].getPrice();
				System.out.printf("%-12s %-7d %-11.2f %-10.2f %-15.2f %-14.2f %n",
						          name,demand,setupCost,unitCost,inventoryCost,price);
			}
		}
		System.out.println();
	}
	
    /*Methods to access data in a product object*/ 
    public String getName(int index)
    {
    	String name = product[index].getName();
    	return name;
    	
    }
	public int getDemand(String name) 
	{
		int demand = 0;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
				demand = product[i].getDemand();
		}
		return demand;
	}	
	public double getSetupCost(String name) 
	{
		double setupCost = 0; 
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
				setupCost = product[i].getSetupCost();
		}
		return setupCost;
	}
	public double getInventoryCost(String name) 
	{
		double inventoryCost = 0;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
				inventoryCost = product[i].getInventoryCost();			
		}
		return inventoryCost;
	}
	public double getUnitCost(String name) 
	{
		double unitCost = 0;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
	        	unitCost = product[i].getUnitCost();
		}
		return unitCost;
	}
	public double getPrice(String name) 
	{
		double sellingPrice = 0;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals(name))
				sellingPrice = product[i].getPrice();			
		}
		return sellingPrice;
	}
	
	//toString() method to return all products names in a store to the caller
	public String toString()
	{
		String nameList = "";
		int count = 1;
		for(int i=0; i<product.length; i++)
		{
			if(product[i].getName().equals("null") == false)
			{
				nameList = nameList.concat("\tProduct " +count+ ": " +product[i].getName() +"\n");
				count++;
			}
		}
		return nameList;
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
	 * Name:  calculateCost(String name, int lastOrderQuantity, int totalInventoryAmount, int orderCount) 
	 * Parameters: Name, last order quantity, total inventory amount, order count of a product
	 * Return: Double - cost of a product
	 * Description: A method to calculate the cost of a product.
	 * Pre-conditions: The user must input a valid product name.
	 * Post-conditions: The cost of a product is calculated and returned.
	 */ 	
    public double calculateCost(String name, int lastOrderQuantity, 
                                int totalInventoryAmount, int orderCount)
    {
       	double cost = 0,
     	       setupCost = getSetupCost(name),
     	       unitCost = getUnitCost(name),
     	       inventoryCost = getInventoryCost(name),
     	       EOQuantity = calculateEOQuantity(name);
     	cost = (setupCost * orderCount)    
     		 + ((EOQuantity * (orderCount - 1) + lastOrderQuantity) * unitCost) 
     		 + (totalInventoryAmount * inventoryCost);	
     	return cost;
    }
    
	/*
	 * Name:  calculateProfit(String name, double cost, int week) 
	 * Parameters: Name, cost, running week of a product
	 * Return: Double - profit of a product
	 * Description: A method to calculate the profit of a product.
	 * Pre-conditions: The user must input a valid product name and number of week
	 * Post-conditions: The profit of a product is calculated and returned.
	 */
    public double calculateProfit(String name, double cost, int week) 
    {
    	double profit = 0,
    	       sellingPrice = getPrice(name);
    	int    demand = getDemand(name);
        profit = (demand * week * sellingPrice) - cost; //Profit is calculated based on cost
    	return profit;
    }
    
}

