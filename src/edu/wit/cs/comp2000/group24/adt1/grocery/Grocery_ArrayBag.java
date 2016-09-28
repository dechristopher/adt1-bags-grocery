package edu.wit.cs.comp2000.group24.adt1.grocery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grocery_ArrayBag {

	public static void main(String[] args) {
		ArrayBag<GroceryItem> bagPerishable = new ArrayBag<>();
		ArrayBag<GroceryItem> bagNonPerishable = new ArrayBag<>();

		Scanner input = new Scanner(System.in);

		System.out.print("Grocery list filename: ");
		String file = input.nextLine();

		input.close();

		ArrayList<GroceryItem> items = fillGroceryList(file);

		sortItems(items, bagPerishable, bagNonPerishable);
	}

	/**
	 * Takes the grocery list file and parses all entries into an ArrayList of
	 * GroceryItem objects.
	 * 
	 * @param filename
	 * @return ArrayList<GroceryItem> containing all the grocery items from the
	 *         list
	 */
	public static ArrayList<GroceryItem> fillGroceryList(String filename) {
		ArrayList<GroceryItem> items = new ArrayList<>();

		try {
			Scanner fin = new Scanner(new File(filename));
			while (fin.hasNextLine()) {

				String[] line = fin.nextLine().split("	");

				GroceryItem item = new GroceryItem();

				for (String property : line) {
					parseProperty(item, property);
				}
				
				System.out.println("NEW ITEM: \"" + item.getName() + "\" : " + item.getSize() + " : " + item.getWeight() + " : " + item.getHardness() + " : " + item.getRigidity() + " : " + item.getBreakability() + " : " + item.getPerishability());

				items.add(item);
			}

			fin.close();
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println(filename + " does not exist! Program exiting.");
			System.exit(1);
		}

		return items;
	}

	/**
	 * Changes properties of a given grocery based on the actual property value
	 * given
	 * 
	 * @param item
	 *            GriveryItem to change property of
	 * @param property
	 *            String property
	 */
	private static void parseProperty(GroceryItem item, String property) {
		if (property.equals("small") || property.equals("medium") || property.equals("large")) {
			item.setSize(SIZE.valueOf(property.toUpperCase()));
		} else if (property.equals("light") || property.equals("moderate") || property.equals("heavy")) {
			item.setWeight(WEIGHT.valueOf(property.toUpperCase()));
		} else if (property.equals("soft") || property.equals("firm") || property.equals("hard")) {
			item.setHardness(HARDNESS.valueOf(property.toUpperCase()));
		} else if (property.equals("flexible") || property.equals("rigid")) {
			item.setRigidity(RIGIDITY.valueOf(property.toUpperCase()));
		} else if (property.equals("breakable") || property.equals("nonbreakable")) {
			item.setBreakability(BREAKABILITY.valueOf(property.toUpperCase()));
		} else if (property.equals("perishable") || property.equals("nonperishable")) {
			item.setPerishability(PERISHABILITY.valueOf(property.toUpperCase()));
		} else {
			item.setName(property);
		}
	}

	/**
	 * Sorts items from items ArrayList into separate weight classes and then
	 * into their respective bags based on breakabiltiy and perishability from
	 * there
	 * 
	 * @param items
	 *            ArrayList<GroceryItem> all of the groceries in the list
	 * @param bagPerishable
	 *            ArrayBag<GroceryItem> the bag that will contain all of the
	 *            perishable items
	 * @param bagNonPerishable
	 *            ArrayBag<GroceryItem> the bag that will contain all of the
	 *            perishable items
	 */
	public static void sortItems(ArrayList<GroceryItem> items, ArrayBag<GroceryItem> bagPerishable,
			ArrayBag<GroceryItem> bagNonPerishable) {
		ArrayList<GroceryItem> lightItems = getLightItems(items);
		ArrayList<GroceryItem> mediumItems = getMediumItems(items);
		ArrayList<GroceryItem> heavyItems = getHeavyItems(items);

		// System.out.println("LIGHT: " + lightItems.size() + " MEDIUM: " +
		// mediumItems.size() + " HEAVY: " + heavyItems.size());

		// WEIGHT -> breakability

		System.out.println("=========================================================================");
		System.out.println("Sorting");

		fillBag(bagPerishable, bagNonPerishable, lightItems, mediumItems, heavyItems);

		System.out.println("=========================================================================");
		System.out.println("Non Perishables Bag");
		System.out.println("=========================================================================");
		GroceryItem[] nonPerishableArray = (GroceryItem[]) bagNonPerishable.toArray(new GroceryItem[0]);
		for (GroceryItem i : nonPerishableArray) {
			System.out.println(i.getName().toString());
		}

		System.out.println("=========================================================================");
		System.out.println("Perishables Bag");
		System.out.println("=========================================================================");
		GroceryItem[] perishableArray = (GroceryItem[]) bagPerishable.toArray(new GroceryItem[0]);
		for (GroceryItem i : perishableArray) {
			System.out.println(i.getName().toString());
		}
	}

	/**
	 * Actually fill the bags with the separated item arrayLists: heavies first,
	 * then mediums, then lights and breakables
	 * 
	 * @param bagPerishable
	 *            ArrayBag<GroceryItem> that will contain perishable items
	 * @param bagNonPerishable
	 *            ArrayBag<GroceryItem> that will contain nonperishable items
	 * @param lightItems
	 *            ArrayList<GroceryItem> all light weight groceries
	 * @param mediumItems
	 *            ArrayList<GroceryItem> all medium weight groceries
	 * @param heavyItems
	 *            ArrayList<GroceryItem> all heavy weight groceries
	 */
	private static void fillBag(ArrayBag<GroceryItem> bagPerishable, ArrayBag<GroceryItem> bagNonPerishable,
			ArrayList<GroceryItem> lightItems, ArrayList<GroceryItem> mediumItems, ArrayList<GroceryItem> heavyItems) {

		for (GroceryItem i : heavyItems) {
			if (i.getPerishability() == PERISHABILITY.PERISHABLE) {
				bagPerishable.add(i);
			} else {
				bagNonPerishable.add(i);
			}
		}

		for (GroceryItem i : mediumItems) {
			if (i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE) {
				bagPerishable.add(i);
			}
		}

		for (GroceryItem i : mediumItems) {
			if (i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE) {
				bagPerishable.add(i);
			}
		}

		for (GroceryItem i : mediumItems) {
			if (i.getPerishability() == PERISHABILITY.NONPERISHABLE
					&& i.getBreakability() == BREAKABILITY.NONBREAKABLE) {
				bagNonPerishable.add(i);
			}
		}

		for (GroceryItem i : mediumItems) {
			if (i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE) {
				bagNonPerishable.add(i);
			}
		}

		for (GroceryItem i : lightItems) {
			if (i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE) {
				bagPerishable.add(i);
			}
		}

		for (GroceryItem i : lightItems) {
			if (i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE) {
				bagPerishable.add(i);
			}
		}

		for (GroceryItem i : lightItems) {
			if (i.getPerishability() == PERISHABILITY.NONPERISHABLE
					&& i.getBreakability() == BREAKABILITY.NONBREAKABLE) {
				bagNonPerishable.add(i);
			}
		}

		for (GroceryItem i : lightItems) {
			if (i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE) {
				bagNonPerishable.add(i);
			}
		}

	}

	/**
	 * Separate light weight items from all items into their own ArrayList
	 * 
	 * @param items
	 *            ArrayList<GroceryItem> all grocery items
	 * @return ArrayList<GroceryItem> all light weight items
	 */
	public static ArrayList<GroceryItem> getLightItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> lightItems = new ArrayList<>();

		for (GroceryItem item : items) {
			if (item.getWeight() == WEIGHT.LIGHT) {
				lightItems.add(item);
			}
		}

		return lightItems;
	}

	/**
	 * Separate medium weight items from all items into their own ArrayList
	 * 
	 * @param items
	 *            ArrayList<GroceryItem> all grocery items
	 * @return ArrayList<GroceryItem> all medium weight items
	 */
	public static ArrayList<GroceryItem> getMediumItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> mediumItems = new ArrayList<>();

		for (GroceryItem item : items) {
			if (item.getWeight() == WEIGHT.MODERATE) {
				mediumItems.add(item);
			}
		}

		return mediumItems;
	}

	/**
	 * Separate heavy weight items from all items into their own ArrayList
	 * 
	 * @param items
	 *            ArrayList<GroceryItem> all grocery items
	 * @return ArrayList<GroceryItem> all heavy weight items
	 */
	public static ArrayList<GroceryItem> getHeavyItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> heavyItems = new ArrayList<>();

		for (GroceryItem item : items) {
			if (item.getWeight() == WEIGHT.HEAVY) {
				heavyItems.add(item);
			}
		}

		return heavyItems;
	}

}
