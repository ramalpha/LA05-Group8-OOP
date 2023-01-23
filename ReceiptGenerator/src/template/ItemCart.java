package template;

import java.util.ArrayList;

public class ItemCart extends ItemInventory {

	// Attribute
	private int qty;
	public static ArrayList<ItemCart> cartList = new ArrayList<>();
	
	// Constructors (for storing ONLY in shoping "cart" ArrayList)
	public ItemCart(String itemID, String itemName, double itemPrice, int qty) {
		super(itemID, itemName, itemPrice);
		// TODO Auto-generated constructor stub
		this.qty = qty;
	}

	// Getter and Setter
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
