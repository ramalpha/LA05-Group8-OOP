package main;

import java.util.Scanner;

import logic.CreateReceipt;
import logic.EditInventory;
import template.ItemCart;

public class SubMenu {

	static Scanner scan = new Scanner(System.in);
	
	public static void editInventory() {
		// TODO Auto-generated method stub
		//Logic at ItemInventory.java
		int menuEI = -1;
		boolean check = false; // checking if input is INTEGER ONLY
		
		do {
			// Display "template" inventory menu
			MenuTemplate.editInventoryMenu();
			// Integer only (exception handling)
			try {
				System.out.print("Choose: ");
				menuEI = scan.nextInt();
				scan.nextLine();
				// if NO EXCEPTION after input -> it runs past this stage
				check = true; 
			}catch(Exception e){
				System.out.println("Please input Integer!");
			}
		}while(menuEI < 1 || menuEI > 3 || !check); // while check = FALSE
		
		// MENU
		switch (menuEI) {
		case 1:
			EditInventory.addItemToInventory();
			break;
		case 2:
			EditInventory.removeItemFromInventory();
			break;
		case 3:
			// back to main menu
			Main.menu();
			break;
		}
	}

	public static void createReceipt() {
		// TODO Auto-generated method stub
		//Logic at ItemCart.java
		int menuCR = -1;
		boolean check = false; // checking if input is INTEGER ONLY
		
		do {
			// Display "template" Receipt menu
			MenuTemplate.createReceiptMenu();
			// Integer only (exception handling)
			try {
				System.out.print("Choose: ");
				menuCR = scan.nextInt();
				scan.nextLine();
				// if NO EXCEPTION after input -> it runs past this stage
				check = true; 
			}catch(Exception e){
				System.out.println("Please input Integer!");
			}
		}while(menuCR < 1 || menuCR > 4 || !check); // while check = FALSE
		
		// MENU
		switch (menuCR) {
		case 1:
			CreateReceipt.addItemToCart();
			break;
		case 2:
			CreateReceipt.removeItemFromCart();
			break;
		case 3:
			CreateReceipt.generateReceipt();
			break;
		case 4:
			// back to main menu (clearing temporary "shopping cart")
			ItemCart.cartList.clear();
			Main.menu();
			break;
		}
	}
	
}
