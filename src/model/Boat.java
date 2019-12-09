package model;

import java.util.ArrayList;

public class Boat {
	private int numOfPieces = 0;
	private ArrayList<Piece> passengers = new ArrayList<Piece>();
	private int maxPassengers = 2;
	private String currentLocation;
	
	public Boat(String c){
		this.currentLocation = c;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * sets location of boat and its passengers
	 * @param currentLocation, boatPiece
	 */
	public void setCurrentLocation(String currentLocation, Piece boatPiece) {
		boatPiece.setPosition(currentLocation);
		for(Piece x : this.getPassengers()){
			x.setPosition(currentLocation);
		}
		this.currentLocation = currentLocation;
	}

	public boolean isFarmerOnBoat() {
        for (Piece p : passengers) {
            if (p.equals("Farmer")) {
            	System.out.print("Farmer on board");
                return true;
            }
        }
    	System.out.print("Farmer NOT on board");
        return false;
    }

	public void addPassengers(Piece p){
		if(this.numOfPieces < this.maxPassengers){
			this.numOfPieces++;
			this.passengers.add(p);
		}	
	}

	public void removePassengers(Piece p){
		this.passengers.remove(p);
	}

	public boolean hasSpace() {
		if(this.numOfPieces < this.maxPassengers){
			return true;
		}

		return false;
	}

	public ArrayList<Piece> getPassengers(){
		return this.passengers;
	}
}
