package edu.wit.cs.comp2000.group24.adt1.grocery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grocery {

	public static void main(String[] args) {

		LinkedBag<GroceryItem> bagPerishable = new LinkedBag<>();
		LinkedBag<GroceryItem> bagNonPerishable = new LinkedBag<>();

		ArrayList<GroceryItem> items = fillGroceryList("groceries-1.txt");

		sortItems(items, bagPerishable, bagNonPerishable);

	}

	public static ArrayList<GroceryItem> fillGroceryList(String filename) {
		ArrayList<GroceryItem> items = new ArrayList<>();

		try {
			Scanner fin = new Scanner(new File(filename));
			while (fin.hasNextLine()) {

				String[] line = fin.nextLine().split("	");

				String n = line[0];
				String s = line[1];
				String w = line[2];
				String h = line[3];
				String r = line[4];
				String b = line[5];
				String p = line[6];

				GroceryItem item = parseLine(n, s, w, h, r, b, p);
				items.add(item);
			}
			
			/*for(GroceryItem item : items){
				System.out.println(item.getWeight().toString());
			}*/

			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static GroceryItem parseLine(String n, String s, String w, String h, String r, String b, String p) {
		SIZE size = SIZE.valueOf(s.toUpperCase());
		WEIGHT weight = WEIGHT.valueOf(w.toUpperCase());
		HARDNESS hardness = HARDNESS.valueOf(h.toUpperCase());
		RIGIDITY rigidity = RIGIDITY.valueOf(r.toUpperCase());
		BREAKABILITY breakability = BREAKABILITY.valueOf(b.toUpperCase());
		PERISHABILITY perishability = PERISHABILITY.valueOf(p.toUpperCase());

		System.out.println("New Grocery: " + n + " - " + s + " " + w + " " + h + " " + r + " " + b + " " + p);

		return new GroceryItem(n, size, weight, hardness, rigidity, breakability, perishability);
	}

	public static void sortItems(ArrayList<GroceryItem> items, LinkedBag<GroceryItem> bagPerishable, LinkedBag<GroceryItem> bagNonPerishable) {
		ArrayList<GroceryItem> lightItems = getLightItems(items);
		ArrayList<GroceryItem> mediumItems = getMediumItems(items);
		ArrayList<GroceryItem> heavyItems = getHeavyItems(items);
		
		//System.out.println("LIGHT: " + lightItems.size() + " MEDIUM: " + mediumItems.size() + " HEAVY: " + heavyItems.size());
		
		//WEIGHT -> breakability
		
		System.out.println("=========================================================================");
		System.out.println("Sorting");
		
		fillBag(bagPerishable, bagNonPerishable, lightItems, mediumItems, heavyItems);
		
		System.out.println("=========================================================================");
		System.out.println("Non Perishables Bag");
		System.out.println("=========================================================================");
		GroceryItem[] nonPerishableArray = (GroceryItem[])bagNonPerishable.toArray(new GroceryItem[0]);
		for(GroceryItem i : nonPerishableArray){
			System.out.println(i.getName().toString());
		}
		
		System.out.println("=========================================================================");
		System.out.println("Perishables Bag");
		System.out.println("=========================================================================");
		GroceryItem[] perishableArray = (GroceryItem[])bagPerishable.toArray(new GroceryItem[0]);
		for(GroceryItem i : perishableArray){
			System.out.println(i.getName().toString());
		}
	}

	private static void fillBag(LinkedBag<GroceryItem> bagPerishable, LinkedBag<GroceryItem> bagNonPerishable, ArrayList<GroceryItem> lightItems,
			ArrayList<GroceryItem> mediumItems, ArrayList<GroceryItem> heavyItems) {
		
		for(GroceryItem i : heavyItems){
			if(i.getPerishability() == PERISHABILITY.PERISHABLE){
					bagPerishable.add(i);
			}else{
					bagNonPerishable.add(i);
			}
		}
		
		for(GroceryItem i : mediumItems){
			if(i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE){
				bagPerishable.add(i);
			}
		}
		
		for(GroceryItem i : mediumItems){
			if(i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE){
				bagPerishable.add(i);
			}
		}
		
		for(GroceryItem i : mediumItems){
			if(i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE){
				bagNonPerishable.add(i);
			}
		}
		
		for(GroceryItem i : mediumItems){
			if(i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE){
				bagNonPerishable.add(i);
			}
		}
		
		for(GroceryItem i : lightItems){
			if(i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE){
				bagPerishable.add(i);
			}
		}
		
		for(GroceryItem i : lightItems){
			if(i.getPerishability() == PERISHABILITY.PERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE){
				bagPerishable.add(i);
			}
		}
		
		for(GroceryItem i : lightItems){
			if(i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.NONBREAKABLE){
				bagNonPerishable.add(i);
			}
		}
		
		for(GroceryItem i : lightItems){
			if(i.getPerishability() == PERISHABILITY.NONPERISHABLE && i.getBreakability() == BREAKABILITY.BREAKABLE){
				bagNonPerishable.add(i);
			}
		}
		
		
	}

	public static ArrayList<GroceryItem> getLightItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> lightItems = new ArrayList<>();
		
		for(GroceryItem item : items){
			if(item.getWeight() == WEIGHT.LIGHT){
				lightItems.add(item);
			}
		}
		
		return lightItems;
	}
	
	public static ArrayList<GroceryItem> getMediumItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> mediumItems = new ArrayList<>();
		
		for(GroceryItem item : items){
			if(item.getWeight() == WEIGHT.MEDIUM){
				mediumItems.add(item);
			}
		}
		
		return mediumItems;
	}
	
	public static ArrayList<GroceryItem> getHeavyItems(ArrayList<GroceryItem> items) {
		ArrayList<GroceryItem> heavyItems = new ArrayList<>();
		
		for(GroceryItem item : items){
			if(item.getWeight() == WEIGHT.HEAVY){
				heavyItems.add(item);
			}
		}
		
		return heavyItems;
	}

}
