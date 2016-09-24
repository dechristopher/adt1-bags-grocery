package edu.wit.cs.comp2000.group24.adt1.grocery;

public class GroceryItem {
	private String name;
	private SIZE size;
	private WEIGHT weight;
	private HARDNESS hardness;
	private RIGIDITY rigidity;
	private BREAKABILITY breakability;
	private PERISHABILITY perishability;
	
	public GroceryItem(String n, SIZE s, WEIGHT w, HARDNESS h, RIGIDITY r, BREAKABILITY b, PERISHABILITY p){
		this.name = n;
		this.size = s;
		this.weight = w;
		this.hardness = h;
		this.rigidity = r;
		this.breakability = b;
		this.perishability = p;
	}
	
	public String getName(){
		return this.name;
	}
	
	public SIZE getSize(){
		return this.size;
	}
	
	public WEIGHT getWeight(){
		return this.weight;
	}
	
	public HARDNESS getHardness(){
		return this.hardness;
	}
	
	public RIGIDITY getRigidity(){
		return this.rigidity;
	}
	
	public BREAKABILITY getBreakability(){
		return this.breakability;
	}
	
	public PERISHABILITY getPerishability(){
		return this.perishability;
	}
}

