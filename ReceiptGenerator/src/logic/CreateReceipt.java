package logic;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import main.SubMenu;
import template.ItemCart;
import template.ItemInventory;

public class CreateReceipt {
	
	static Scanner scan = new Scanner(System.in);

	// Method
	public static void addItemToCart() {
		if(ItemInventory.inventoryList.isEmpty()) {
			System.out.println("NO DATA!");
		}else {
			// Display Inventory List (as to aid in adding to Shopping "Cart")
			EditInventory.displayInventoryJDBC();
			System.out.println();
			
			int selectItem = 0;
			boolean check = false;
			do {
				System.out.print("Select Item [1 - " + ItemInventory.inventoryList.size() + "]: ");
				try {
					selectItem = scan.nextInt();
					// if NO EXCEPTION after input -> it runs past this stage
					check = true;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please put Integer!");
					scan.nextLine();
				}
			}while(selectItem < 1 || selectItem > ItemInventory.inventoryList.size() || check == false);
			
			// Temporary Sync with ItemInventory List
			String syncItemID = ItemInventory.inventoryList.get(selectItem-1).getItemID();
			String syncItemName = ItemInventory.inventoryList.get(selectItem-1).getItemName();
			double syncItemPrice = ItemInventory.inventoryList.get(selectItem-1).getItemPrice();
			
			int inputQty = 0;
			boolean check2 = false;
			do {
				System.out.println("Input Quantity: ");
				try {
					inputQty = scan.nextInt();
					// if NO EXCEPTION after input -> it runs past this stage
					check2 = true;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please input Integer!");
					scan.nextLine();
				}
			}while(inputQty < 1 || inputQty > 999 || check2 == false);
			
			boolean duplicateChecker = false;
			if (ItemCart.cartList.isEmpty() == false) {
				for (int i = 0; i < ItemCart.cartList.size(); i++) {
					//Trying to find matching itemID
					if (ItemCart.cartList.get(i).getItemID().equals(syncItemID)) {
						duplicateChecker = true;
						// Direct Addition to "inputQty" (on top of existing values)
						ItemCart.cartList.get(i).setQty(ItemCart.cartList.get(i).getQty() + inputQty);
						break;
					}
				} 
			}
			// Item to Cart Addition
			if (duplicateChecker == false) {
				ItemCart addCartList = new ItemCart(syncItemID, syncItemName, syncItemPrice, inputQty);
				ItemCart.cartList.add(addCartList);
			}
		}
		// Process Outro
		System.out.println("Item successfully added to cart");
		System.out.println("Press ENTER to continue...");
		scan.nextLine();
		// back to "Create Receipt" menu
		SubMenu.createReceipt();
	}
	
	public static void removeItemFromCart() {
		if(ItemCart.cartList.isEmpty()) {
			System.out.println("NO DATA!");
		}else {
			int selectItem = 0;
			boolean check = false;
			do {
				System.out.print("Select Item [1 - " + ItemCart.cartList.size() + "]: ");
				try {
					selectItem = scan.nextInt();
					// if NO EXCEPTION after input -> it runs past this stage
					check = true;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please input Integer!");
					scan.nextLine();
				}
			}while(selectItem < 1 || selectItem > ItemCart.cartList.size() || check == false);
			
			// Item from Cart Removal
			ItemCart.cartList.remove(selectItem-1);
			System.out.print("Item successfully removed...");
		}
		// Process Outro
		System.out.println("Item successfully removed to cart");
		System.out.println("Press ENTER to continue...");
		scan.nextLine();
		// back to "Create Receipt" menu
		SubMenu.createReceipt();
	}
	
	// will ALWAYS be invoked upon going back with "Main.createReceipt();"
	public static void displayCart() {
		if(ItemCart.cartList.isEmpty()) {
			System.out.println("Cart is empty!");
		}else {
			System.out.println("======================================");
			System.out.println("||               Cart               ||");
			System.out.println("======================================");
			System.out.println("Item ID | Item Name | Item Price | Qty");
			System.out.println("======================================");
			System.out.println();
			int counter = 0; // addCL = counter-1 | Hence -> "cartList.get(counter-1).getItemID()"
			for (ItemCart addCL : ItemCart.cartList) {
				counter++;
				System.out.print(counter + ". ");
				System.out.print(addCL.getItemID());
				System.out.print(" | ");
				System.out.print(addCL.getItemName());
				System.out.print(" | ");
				System.out.print(addCL.getItemPrice());
				System.out.print(" | ");
				System.out.print(addCL.getQty());
				System.out.println();
			}
		}
	}
	
	// ===== RECEIPT GENERATOR =====
	public static void generateReceipt() {
		if(ItemCart.cartList.isEmpty()) {
			System.out.println("Cart is empty!");
		}else {
			// Generating Receipt ID (e.g. REC01234)
			String REC = "REC";		
			// 0-9
			for (int i = 0; i < 5; i++) {
				Integer singleNum = ThreadLocalRandom.current().nextInt(0, 10);
				REC += singleNum.toString();
			}
			LocalDateTime datetime = LocalDateTime.now();
			
			System.out.println("==================================================================");
			System.out.println("||                            Receipt                           ||");
			System.out.println("==================================================================");
			System.out.println(REC);
			System.out.println(datetime);
			System.out.println("==================================================================");
			System.out.printf("%-4s | %-20s | %-10s | %-4s | %-10s\n", "No.", "Item", "Price", "Qty", "Total");
			//System.out.println("Item	|Price		|Qty		|Total");
			System.out.println("==================================================================");
			// Printing Cart List
			int counter = 0;
			for (ItemCart addCL : ItemCart.cartList) {
				counter++;
				System.out.printf("%-4d | %-20s | %-10.2f | %-4d | %-10.2f\n", counter, addCL.getItemName(), addCL.getItemPrice(), addCL.getQty(), (double)(addCL.getItemPrice() * addCL.getQty()));
//				System.out.print(counter + ". ");
//				System.out.print(cartList.get(counter-1).getItemName());
//				System.out.print(" | ");
//				System.out.print(cartList.get(counter-1).getItemPrice());
//				System.out.print(" | ");
//				System.out.print(cartList.get(counter-1).getQty());
//				System.out.print(" | ");
//				System.out.println((cartList.get(counter-1).getItemPrice()) * (cartList.get(counter-1).getQty()));
				System.out.println();
			}
			// Printing Subtotal, tax, & Total
			//int countersub = 0;
			double subtotal = 0;
			for (ItemCart addCL : ItemCart.cartList) {
				//countersub++;
				subtotal += (addCL.getItemPrice()) * (addCL.getQty());
				//subtotal += (cartList.get(countersub-1).getItemPrice()) * (cartList.get(countersub-1).getQty());
			}
			
			System.out.println("==================================================================");
			System.out.println("SUBTOTAL: 					" + String.format("%.2f", subtotal));
			System.out.println("TAX 10% :  					" + String.format("%.2f", subtotal * 0.1));
			System.out.println("==================================================================");
			System.out.println("TOTAL   :					" + String.format("%.2f", subtotal * 1.1));
		}
		// Process Outro
		System.out.println("Press enter to continue...");
		scan.nextLine();
		// back to "Create Receipt" menu
		SubMenu.createReceipt();
	}
	
}
