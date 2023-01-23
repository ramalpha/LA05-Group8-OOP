package main;

import logic.CreateReceipt;
import logic.EditInventory;

public class MenuTemplate {
	
	public static void mainMenu() {
		System.out.println();
		System.out.println("Receipt Generator (Point of Sale)");
		System.out.println("=================================");
		System.out.println("1. Create Receipt");
		System.out.println("2. Edit Inventory");
		System.out.println("3. Exit");
	}
	
	public static void createReceiptMenu() {
		System.out.println();
		System.out.println("=================================");
		CreateReceipt.displayCart();
		System.out.println("=================================");
		System.out.println("Create Receipt");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("1. Add Item to Cart");
		System.out.println("2. Delete Item from Cart");
		System.out.println("3. Generate Receipt");
		System.out.println("4. Back");
	}
	
	public static void editInventoryMenu() {
		System.out.println();
		System.out.println("=================================");
		EditInventory.displayInventoryJDBC();
		System.out.println("=================================");
		System.out.println("Edit Inventory");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("1. Register Item");
		System.out.println("2. Remove Item");
		System.out.println("3. Back");
	}
	
}
