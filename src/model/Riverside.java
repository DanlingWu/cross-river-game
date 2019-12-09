package model;

import java.util.ArrayList;

public class Riverside {
	private String name;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	// east or west bank is set
	public Riverside(String n){
		this.setName(n);
	}

	public void addPiece(Piece p){
		pieces.add(p);
	}

	public void removePiece(Piece p){
		pieces.remove(p);
	}

	public ArrayList<Piece> getPieces(){
		return pieces;
		
	}

	//gameover check
	public boolean isEaten(){
		if(pieces.size() >= 2){
			for(int i=0; i< pieces.size(); i++){
				for(int j=0; j < pieces.size(); j++){
					if (pieces.get(i).willEat(pieces.get(j))) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
