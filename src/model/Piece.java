package model;

public class Piece{
	private String name;
	private String eats;
	private String position;

	public Piece(String n, String e){
		this.name = n;
		this.eats = e;
	}

	public Piece(String n){
		this.name = n;
	}

	public boolean canMove(String direction){
		return true;
	}

	public void moveIt(String direction){
		
	}

	public boolean willEat(Piece p){
		if(p==null){
			return false;
		}
		if(this.eats.equals(p.name)){
			return true;
		}
		else{
			return false;
		}
	}

	public String getName(){
		return this.name;
	}

	public String getEats(){
		return this.eats;
	}

	public boolean equals(String name){
		return this.name.equals(name);
	}

	public void setPosition(String position){
		this.position = position;
	}

	public String getPosition() {

		return this.position;
	}
}
