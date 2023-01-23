package main;

import java.util.Scanner;

import logic.EditInventory;

public class Main{
	//TO RUN CMD, RIGHT-CLICK ON 'Main.java' AND SELECT 'Run As > Java Application' (Alt+Shift+X,J)
	
	static Scanner scan = new Scanner(System.in);
	
	/**
	 * 
	 */
	public static void menu() {
		//boolean onApp = true;
		//while(onApp) { // while Main(true) -> onApp TRUE = keep GOING
			// negative initial assumptions for menu's do-while loop
			int menu = -1;
			boolean check = false; // checking if input is INTEGER ONLY
			// Integer only (exception handling)
			do {
				// Display "template" MAIN menu
				MenuTemplate.mainMenu();
				try {
					System.out.print("Choose: ");
					menu = scan.nextInt();
					// if NO EXCEPTION after input -> it runs past this stage
					check = true;
				}catch(Exception e){
					System.out.println("Please input Integer!");
					scan.nextLine();
				}
			}while(menu < 1 || menu > 3 || !check); // while check = FALSE
			
			// MENU
			switch (menu) {
			case 1:
				SubMenu.createReceipt();
				break;
			case 2:
				SubMenu.editInventory();
				break;
			case 3:
				// Complete System Exit
				//onApp = false;
				scan.close();
				System.out.println("Thank you for using our app!");
				System.exit(0);
				break;
			}
		//}
	}
	
	public Main() { 
		// Initial "main menu" initiator
		EditInventory.syncDBtoAL();
		menu();
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
