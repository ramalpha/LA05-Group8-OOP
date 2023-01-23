package template;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemInventory {
	static Scanner scan = new Scanner(System.in);
	
	// Attribute
	private String itemID; 
	private String itemName;
	private double itemPrice;
	public static ArrayList<ItemInventory> inventoryList = new ArrayList<>();
	
	// Constructors
	public ItemInventory(String itemID, String itemName, double itemPrice) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}


	// Getter and Setter
	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
