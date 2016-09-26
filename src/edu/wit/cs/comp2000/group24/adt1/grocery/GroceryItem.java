package edu.wit.cs.comp2000.group24.adt1.grocery;

public class GroceryItem {
	private String name;
	private SIZE size;
	private WEIGHT weight;
	private HARDNESS hardness;
	private RIGIDITY rigidity;
	private BREAKABILITY breakability;
	private PERISHABILITY perishability;
	
	public GroceryItem(){
		this.name = "";
		this.size = SIZE.NONE;
		this.weight = WEIGHT.NONE;
		this.hardness = HARDNESS.NONE;
		this.rigidity = RIGIDITY.NONE;
		this.breakability = BREAKABILITY.NONE;
		this.perishability = PERISHABILITY.NONE;
	}
	
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
	
	public void setName(String n){
		this.name = n;
	}
	
	public void setSize(SIZE s){
		this.size = s;
	}
	
	public void setWeight(WEIGHT w){
		this.weight = w;
	}
	
	public void setHardness(HARDNESS h){
		this.hardness = h;
	}
	
	public void setRigidity(RIGIDITY r){
		this.rigidity = r;
	}
	
	public void setBreakability(BREAKABILITY b){
		this.breakability = b;
	}
	
	public void setPerishability(PERISHABILITY p){
		this.perishability = p;
	}
}

