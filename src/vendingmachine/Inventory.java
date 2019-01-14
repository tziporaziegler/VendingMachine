package vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	ArrayList<Item> inventory;
	Map<String, Item> inventoryMap;

	public Inventory() {
		inventory = new ArrayList<Item>();
		inventoryMap = new HashMap<String, Item>();
	}

	// load inventory data into the ArrayList and HashMap
	public void load(String inventoryFilename) throws IOException {
		Scanner file = new Scanner(new File(inventoryFilename));
		file.useDelimiter(",|\n");
		while (file.hasNext()) {
			String code = file.next();
			String name = file.next();
			double amt = file.nextDouble();
			int qty = file.nextInt();
			Item item = new Item(code, name, amt, qty);
			inventory.add(item);
			inventoryMap.put(code, item);
		}
		file.close();
	}

	/**
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if (!inventoryMap.containsKey(code)) {
			return null;
		}
		return inventoryMap.get(code);
	}

	/**
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		if (inventoryMap.containsKey(item.getCode())) {
			// Not sure if referencing same item. Should I add to quantity or do nothing?
			Item alreadyExistingItem = inventoryMap.get(item.getCode());
			int oldQty = alreadyExistingItem.getQuantity();
			alreadyExistingItem.setQuantity(item.getQuantity() + oldQty);
		} else {
			inventory.add(item);
			inventoryMap.put(item.getCode(), item);
		}
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		Item item = get(code);
		int oldQuantity = item.getQuantity();
		item.setQuantity(--oldQuantity); // added setter
	}

	/**
	 * @param code
	 * @return false if the Item exists and there is at least one quantity,
	 *         otherwise true.
	 */
	public boolean isEmpty(String code) {
		Item item = get(code);
		if (item != null) {
			if (item.getQuantity() >= 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @ price
	 * x quantity\n
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		StringBuilder builder = new StringBuilder();
		// use stringBuilder if in loop, but don't if no loop, just do (code + " " +
		// name)
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			builder.append(item.getCode());
			builder.append(' ');
			builder.append(item.getName());
			builder.append(" @ ");
			builder.append(formatter.format(item.getPrice()));
			builder.append(" x ");
			builder.append(item.getQuantity());
			builder.append("\n");
		}
		return builder.toString();
	}
}