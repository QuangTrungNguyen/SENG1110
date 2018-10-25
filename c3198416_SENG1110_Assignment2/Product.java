/** 
File name: Product.java
Author: Nguyen Quang Trung
Student number: c3198416
E-mail Address: quangtrung.nguyen@uon.edu.au
Programming Assignment 2
Last Changed: May 27, 2016
Description: The file contains Product class to hold the required
instance data for a product, methods to access and modify its value. 
*/ 

public class Product 
{
    //Declare instance variables - required data of a product
	private String name;              
	private int    demand;        //per product          
	private double setupCost,     //($) per inventory order
	               inventoryCost, //($) per unit per week
	               unitCost,      //($)
	               sellingPrice;  //($) per item

	//Paramatized Constructor
	public Product (String name, int demand, int week, double setupCost, double inventoryCost, 
			        double unitCost, double sellingPrice, double cost, double profit) 
	{
		this.name = name;
		this.demand = demand;
		this.setupCost = setupCost;
		this.inventoryCost = inventoryCost;
		this.unitCost = unitCost;
		this.sellingPrice = sellingPrice;
	}
	
	//Default Constructor 
	public Product () 
	{
		this.name = "null";
		this.demand = 0;
		this.setupCost = 0;
		this.inventoryCost = 0;
		this.unitCost = 0;
		this.sellingPrice = 0;
	}
	
	/*
      Accessors and Mutators
      Methods to access and modify each data of a product.
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName()  
	{
		return this.name;
	}
	public void setDemand (int demand) 
	{
		this.demand = demand;
	}
	public int getDemand() 
	{
		return this.demand;
	}
	public void setSetupCost(double setupCost) 
	{
		this.setupCost = setupCost;
	}
	public double getSetupCost() 
	{
		return this.setupCost;
	}
	public void setInventoryCost(double inventoryCost) 
	{
		this.inventoryCost = inventoryCost;
	}
	public double getInventoryCost() 
	{
		return this.inventoryCost;
	}
	public void setUnitCost(double unitCost) 
	{
		this.unitCost = unitCost;
	}
	public double getUnitCost() 
	{
		return this.unitCost;
	}
	public void setPrice(double sellingPrice) 
	{
		this.sellingPrice = sellingPrice;
	}
	public double getPrice()
	{
		return this.sellingPrice;
	}
	
	//toString() method to return details of a product in a store to the caller.
	public String toString()
	{
	    return("Demand rate: \t" + getDemand() + "\n" 
	    	 + "Setup cost: \t$" + getSetupCost() + " (per order)\n" 
	         + "Inventory cost: $" + getInventoryCost() + " (per unit, per week)\n" 
	    	 + "Unit cost: \t$" + getUnitCost() + "\n" 
	         + "Selling price:  $" + getPrice() + " (per item)\n");
	}
}
