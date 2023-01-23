package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import main.SubMenu;
import template.Connect;
import template.ItemInventory;

public class EditInventory {
	//EditInventory.java requires JDBC integration
	
	static Scanner scan = new Scanner(System.in);
	// XAMPP app must be LIVE (needs to be downloaded and .JAR file connected)
	static Connect con = Connect.getConnection(); // getConnection is a public static method

	// Methods
	private static String generateID() {
		// TODO Auto-generated method stub
		//ex: IT001
		String ID = "IT";
		// 0-9
		for (int i = 0; i < 3; i++) {
			Integer singleNum = ThreadLocalRandom.current().nextInt(0, 10);
			ID += singleNum.toString();
		}
		return ID;
	}
	
	public static void addItemToInventory() {
		String addItemID;
		String addItemName;
		double addItemPrice;
		System.out.println("============");
		
		do {
			System.out.println("Input Name: ");
			addItemName = scan.nextLine();
		}while(addItemName.length() > 50);
		
		addItemPrice = 0;
		boolean check = false;
		do {
			System.out.println("Input Price [< 1.000.000.000]: ");
			try {
				addItemPrice = scan.nextDouble();
				// if NO EXCEPTION after input -> it runs past this stage
				check = true; 
			}catch(Exception e){
				System.out.println("Please input Integer/Decimal!");
				scan.nextLine();
			}
		}while(addItemPrice > 1000000000 || check == false);
		
		// Item Addition (direct ItemID generation)
		//Storing generated ID
		addItemID = generateID();
		//ItemInventory addInventoryList = new ItemInventory(addItemID, addItemName, addItemPrice);
		//ItemInventory.inventoryList.add(addInventoryList);
		
		// INSERT INTO PHPMyAdmin Database
		// Connect (to database with iteminventory TABLE)
		String query = "INSERT INTO iteminventory VALUES ('"+addItemID+"', '"+addItemName+"', '"+addItemPrice+"')";
		// 'addItemID' -> '"+addItemID+"' (avoiding errors)
		con.executeUpdate(query);
		
		// Process Outro
		System.out.println("Item successfully added to inventory");
		System.out.println("Press ENTER to continue...");
		scan.nextLine();
		// back to "Edit Inventory" menu
		SubMenu.editInventory();
	}
	
	public static void removeItemFromInventory() {
		if(ItemInventory.inventoryList.isEmpty()) {
			System.out.println("NO DATA!");
		}else {
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
					System.out.println("Please input Integer!");
					scan.nextLine();
				}
			}while(selectItem < 1 || selectItem > ItemInventory.inventoryList.size() || check == false);
			
			
			// Remove item JDBC
			String tempItemID = ItemInventory.inventoryList.get(selectItem-1).getItemID();
			// Connect (to database with iteminventory TABLE)
			String query = String.format("DELETE FROM iteminventory WHERE ItemID='%s'", tempItemID);
			con.executeUpdate(query);
			
			// Item Removal
			//ItemInventory.inventoryList.remove(selectItem-1);
		}
		
		// Process Outro
		System.out.println("Item successfully removed from inventory");
		System.out.println("Press ENTER to continue...");
		scan.nextLine();
		// back to "Edit Inventory" menu
		SubMenu.editInventory();
	}
	
	// will ALWAYS be invoked upon going back with "Main.editInventory();"
//	public static void displayInventory() {
//		if(ItemInventory.inventoryList.isEmpty()) {
//			System.out.println("Inventory is empty!");
//		}else {
//			System.out.println("================================");
//			System.out.println("||          Inventory         ||");
//			System.out.println("================================");
//			System.out.println("Item ID | Item Name | Item Price");
//			System.out.println("================================");
//			System.out.println();
//			int counter = 0; // addIL = (counter-1) | Hence -> inventoryList.get(counter-1).getItemPrice()
//			for (ItemInventory addIL : ItemInventory.inventoryList) {
//				counter++;
//				System.out.print(counter + ". ");
//				System.out.print(addIL.getItemID());
//				System.out.print(" | ");
//				System.out.print(addIL.getItemName());
//				System.out.print(" | ");
//				System.out.print(addIL.getItemPrice());
//				System.out.println();
//			}
//		}
//	}
	
	//=========== JDBC SELECT function ==============	
	//JDBC INSERT and DELETE functions are integrated into addItemToInventory() and removeItemFromInventory()
	//displayInventory() and displayInventoryJDBC() provide similar functionality

	public static void displayInventoryJDBC() {
		EditInventory.syncDBtoAL();
		
		String query = "SELECT * FROM iteminventory";
		
		// Query untuk narik semua data user dari database dan disimpan di ResultSet
		// return type method "executeQuery()" nya ResultSet -> lihat di Connect.java
		// ResultSet is a pre-built Java Class to store SQL data in Java (returns result data from the database)
		// ResultSet Objects for getting/scanning (getInt etc) does not need a temp storage var at times
		ResultSet rs = con.executeQuery(query);
		
		System.out.println("================================");
		System.out.println("||          Inventory         ||");
		System.out.println("================================");
		System.out.println("Item ID | Item Name | Item Price");
		System.out.println("================================");
		System.out.println();
		
		int counter = 0;
		try {
			
			while(rs.next()) {
				counter++;
				System.out.println(counter + ". " + rs.getString("ItemID") + " | " + rs.getString("ItemName") + " | " + rs.getDouble("ItemPrice"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public static void syncDBtoAL() {
		String addItemID;
		String addItemName;
		double addItemPrice;
		
		ItemInventory.inventoryList.clear();
		
		String query = "SELECT * FROM iteminventory";
		ResultSet rs = con.executeQuery(query);

		try {
			
			while(rs.next()) {
				addItemID = rs.getString("ItemID");
				addItemName = rs.getString("ItemName");
				addItemPrice = rs.getDouble("ItemPrice");
				
				// Item Addition (direct ItemID generation)
				ItemInventory addInventoryList = new ItemInventory(addItemID, addItemName, addItemPrice);
				ItemInventory.inventoryList.add(addInventoryList);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
