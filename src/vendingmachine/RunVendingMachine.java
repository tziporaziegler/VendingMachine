package vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		Scanner scanner = new Scanner(System.in);

		Inventory inventory = new Inventory();
		try {
			inventory.load("inventory.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		VendingMachine food = new VendingMachine(inventory, new Money(10, 10, 10, 10));
		System.out.println(inventory.toString()); // print the inventory

		// Ask the user to add money or make a selection
		System.out.println("Add Money/Make Selection?\n1. Dollar\n2. Quarter\n3. Dime\n"
				+ "4. Nickel\nor enter in the Item Code\n");
		System.out.println("Balance " + formatter.format(food.getPaid().getTotal()));

		String code = null;

		// Process the transaction and dispense change
		while (true) {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				System.out.println("Balance " + formatter.format(food.pay(new Money(1, 0, 0, 0))));
				break;
			case "2":
				System.out.println("Balance " + formatter.format(food.pay(new Money(0, 1, 0, 0))));
				break;
			case "3":
				System.out.println("Balance " + formatter.format(food.pay(new Money(0, 0, 1, 0))));
				break;
			case "4":
				System.out.println("Balance " + formatter.format(food.pay(new Money(0, 0, 0, 1))));
				break;
			default:
				code = choice;
				try {
					double change = food.buy(code).getTotal();
					System.out.println("Dispensing " + inventory.get(code).getName());
					System.out.println("Change " + formatter.format(change));
					scanner.close();
					System.exit(0);
				} catch (CodeNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (NotEnoughPaidException e) {
					System.out.println(e.getMessage());
				} catch (NotEnoughChangeException e) {
					System.out.println(e.getMessage());
					try {
						food.getBank().remove(food.getPaid().getTotal());
						System.out.println("returning money---" + formatter.format(food.getPaid().getTotal()));
					} catch (NotEnoughChangeException e1) {
						System.out.println(e1.getMessage());
					}
					System.exit(0);
				}
			}// end switch
		} // end while
	}// end main
}