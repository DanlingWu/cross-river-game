package model;

import java.util.Observable;

public class GameModel extends Observable{
	
	// Game Data
	int numberOfMoves = 0;
	Piece[] pieces = {new Piece("Boat"), new Piece("Farmer"),new Piece("Fox", "Goose"), new Piece("Goose","Beans"),new Piece("Beans") };
	Riverside eastBank = new Riverside("eastBank");
	Riverside westBank = new Riverside("westBank");
	
	Boat boat = new Boat("boatByEastBank");
	String gameStatusMessage = "Game - River Crossing Puzzle";
	
	//array of location names in specific order! Index 0 to 3 is West to East
	String[] locations= {"westBank","boatByWestBank","boatByEastBank","eastBank"};
	
	/**
	 * Game data setup with pieces added to eastBank river side at start
	 */
	public GameModel(){
		for(int i=0; i< pieces.length; i++){
			pieces[i].setPosition("eastBank");
			this.eastBank.addPiece(pieces[i]);
		}
		this.gameStatusMessage = "Game River Crossing Puzzle - ";
	}

	public String getGameStatus(){
		return this.gameStatusMessage + " Score: " + this.numberOfMoves;
	};

	public void setGameStatus(String msg){
		this.gameStatusMessage = msg;
		super.setChanged();
		super.notifyObservers(this);
	}

	// set the player score, -1 for every BOAT move
	public void boatMoves(String location, Piece boatPiece){
		this.boat.setCurrentLocation(location,boatPiece);
		this.numberOfMoves--;
		
	}

	/**
	 *  goes through all the pieces to find which button was pressed
	 * @param name
	 * @param direction
	 */
	
	public void movePiece(String name, String direction){
		for(int i=0; i<pieces.length; i++){
			if(pieces[i].getName().equals(name)){
				moveIfPossible(pieces[i], direction);
				
			}
		}
		super.setChanged();
		super.notifyObservers(this);
	}

	public boolean moveIfPossible(Piece p, String direction){
		System.out.println(p.getName() + " at " + p.getPosition()  + " " + direction);
		for(int i =0 ;i<this.locations.length;i++){
			if( locations[i].equals(p.getPosition()) ){
				//edge cases
				if(direction.equals("<") && i==0){
					return false;
				}
				if(direction.equals(">") && i==3){
					System.out.println("Edge case");
					return false;
				}
				
				//boat can't go into the bank!
				if(direction.equals("<") && p.getName().equals("Boat") && i==1){
					return false;
				}
				if(direction.equals(">") && p.getName().equals("Boat") && i==2){
					return false;
				}
				
				//are they on the boat? then ignore their button press into river
				if(!p.getName().equals("Boat") && (i==1) && direction.equals(">") ){
					System.out.print("No move allowed");
					return false;
				}
				if(!p.getName().equals("Boat") && (i==2) && direction.equals("<") ){
					System.out.print("No move allowed");
					return false;
				}
				
				//boarding the boat from East
				if( !p.getName().equals("Boat") && i==3 && direction.equals("<") && boatLocation(2)){
					//if I am farmer it's ok
					if(p.getName().equals("Farmer")){
		            	System.out.print("Farmer gets on board");
						this.boat.addPassengers(p);
						this.eastBank.removePiece(p);
						p.setPosition(this.locations[2]);
						return true;
					}
					else{
						//is farmer on boat?
						if(this.boat.isFarmerOnBoat() && boat.hasSpace() && boatLocation(2)){
							this.boat.addPassengers(p);
							this.eastBank.removePiece(p);
							p.setPosition(this.locations[2]);
							return true;
						}
						else{
							return false;
						}
					}
					
				}
				//boarding boat from West
				if( !p.getName().equals("Boat") && i==0 && direction.equals(">") && boatLocation(1)){
					//if I am farmer it's ok
					if(p.getName().equals("Farmer")){
						this.boat.addPassengers(p);
						this.westBank.removePiece(p);
						p.setPosition(this.locations[1]);
						return true;
					}
					else{
						//is farmer on boat?
						if(this.boat.isFarmerOnBoat() && boat.hasSpace() && boatLocation(1)){
							this.boat.addPassengers(p);
							this.westBank.removePiece(p);
							p.setPosition(this.locations[1]);
							return true;
						}
						else{
							return false;
						}
					}
					
				}
				//getting off boat on to Eastbank
				if( !p.getName().equals("Boat") && i==2 && direction.equals(">") && boatLocation(2)){
					System.out.println("Boat onto  eastbank");
					this.boat.removePassengers(p);
					p.setPosition(this.locations[3]);
					this.eastBank.addPiece(p);
					if(this.eastBank.isEaten() ){
						this.setGameStatus("GAME OVER - Predator eats prey");
					}
					return true;
				}
				//boat on to WestBank
				if( !p.getName().equals("Boat") && i==1 && direction.equals("<") && boatLocation(1)){
					this.boat.removePassengers(p);
					p.setPosition(this.locations[0]);
					if(this.westBank.isEaten()){
						this.setGameStatus("GAME OVER - Predator eats prey");
					}
					return true;
				}
				
				/** Moving the Boat**/
				//moving boat east to west (boat can only be 1 of 2 places)
				if( p.getName().equals("Boat") && direction.equals("<") && this.boat.isFarmerOnBoat() ){
					System.out.println("Moving Boat");
					
					this.boatMoves(this.locations[1],p);
					return true;
				}
				//moving boat west to east
				if( p.getName().equals("Boat")  && direction.equals(">")  && this.boat.isFarmerOnBoat() ){
					
					this.boatMoves(this.locations[2],p);
					return true;
				}
				
				
				
			}
		}//for

		return false;
	}
	
	/**
	 * Is the boat at this location?
	 * @param j
	 * @return
	 */
	public boolean boatLocation(int j){

		if(this.locations[j].equals(this.boat.getCurrentLocation())){
			return true;
		}

		return false;

	}

	public Piece[] getPieces(){
		return this.pieces;
	}
}
