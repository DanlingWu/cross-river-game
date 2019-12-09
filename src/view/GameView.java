package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.GameController;
import model.GameModel;
import model.Piece;

public class GameView extends JFrame implements Observer{
	private BufferedImage waterImage;
	private BufferedImage grassImage;
	private BufferedImage boatImage;

	Sprite foxSprite = new Sprite(new ImageIcon("images/fox.png"));
	Sprite gooseSprite = new Sprite(new ImageIcon("images/goose.png"));
	Sprite beanSprite = new Sprite(new ImageIcon("images/beans.png"));
	Sprite farmerSprite = new Sprite(new ImageIcon("images/farmer.png"));
	Sprite boatSprite = new Sprite(new ImageIcon("images/boat.png"));
	
	JLayeredPane waterLayers = new JLayeredPane();
	ImagePanel eastBank = new ImagePanel("images/grass.png");
	ImagePanel westBank = new ImagePanel("images/grass.png");
	
	public GameView(GameController gameController) {
		this.setLayout(new GridLayout(1,1));
		this.setPreferredSize(new Dimension(800,600));
		this.setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel centrePanel = new JPanel();
		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centrePanel, BorderLayout.CENTER);
		this.add(mainPanel,0,0);


		centrePanel.setLayout(new BorderLayout());


		ImagePanel water = new ImagePanel("images/water.png");
		

		eastBank.setPreferredSize(new Dimension(150,HEIGHT));
		westBank.setPreferredSize(new Dimension(150,HEIGHT));


		centrePanel.add(water, BorderLayout.CENTER);
		centrePanel.add(eastBank, BorderLayout.EAST);
		centrePanel.add(westBank, BorderLayout.WEST);

		//
		


		eastBank.setLayout(new GridLayout(4,1));
		westBank.setLayout(new GridLayout(4,1));
		water.setLayout(new GridLayout(1,1));


		eastBank.add(farmerSprite,0,0);
		eastBank.add(gooseSprite,1,0);
		eastBank.add(beanSprite,2,0);
		eastBank.add(foxSprite,3,0);

		//waterImagePanel.setBounds(0, 0, 600,550);
		boatSprite.setBounds(290, 0, 200, 200);
		//farmerSprite.setBounds(10, 15, 200, 200);
		water.add(waterLayers,0,0);


		waterLayers.add(boatSprite,new Integer(1));
/*
		//farmer added to boat from east bank
		eastBank.remove(farmerSprite);
		farmerSprite.setBounds(290, 40, 150, 150);
		waterLayers.add(farmerSprite,new Integer(2));

		//fox added to boat from east bank
		eastBank.remove(foxSprite);
		foxSprite.setBounds(350, 78, 110, 110);
		waterLayers.add(foxSprite, new Integer(3));

		//goose added to boat from east bank
		eastBank.remove(gooseSprite);
		gooseSprite.setBounds(385, 88, 110, 110);
		waterLayers.add(gooseSprite, new Integer(4));

		//bean added to boat from east bank
		eastBank.remove(beanSprite);
		beanSprite.setBounds(410, 145, 100, 100);
		waterLayers.add(beanSprite, new Integer(5));

		

		//farmer sail to westbank
		farmerSprite.setBounds(0, 40, 150, 150);

		//Goose sail to westBank
		gooseSprite.setBounds(385-290, 88, 110, 110);

		// Beans sail  to westBank
		beanSprite.setBounds(410-290, 145, 100, 100);

		//fox sail to westbank
		foxSprite.setBounds(350-290,78,110,110);

		//fox into westBank
		waterLayers.remove(foxSprite);
		westBank.add(foxSprite, 0, 0);//gridlayout in order, need to have a function

*/


		southPanel.setLayout(new FlowLayout());

		String[] btnNames = {"Boat","Fox","Goose","Beans","Farmer"};
		ArrayList<GButton> buttons = new ArrayList<GButton>();
		for(int i=0; i< btnNames.length; i++){
			southPanel.add(new JLabel( btnNames[i] + ": "));
			GButton tempL = new GButton( btnNames[i] , "<");
		    GButton tempR = new GButton( btnNames[i] , ">");
		    tempL.addActionListener(gameController);
			tempR.addActionListener(gameController);
			buttons.add(tempL);
			buttons.add(tempR);
			southPanel.add(tempL);
			southPanel.add(tempR);
		}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);

	}
	


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//move pieces
		//this.setTitle(((GameModel) arg0).getGameStatus());
		GameModel model = (GameModel) arg0;
		this.setTitle(model.getGameStatus());
		Piece[] pp =  model.getPieces();
		for(int i=0; i < model.getPieces().length; i++){

			switch (pp[i].getName()){
			case "Boat":
				this.setBoatPosition(pp[i].getPosition());
				break;

			case "Farmer":
				this.setFarmerPosition(pp[i].getPosition());
				break;
			}
		}
		//this.repaint();
		
	}
	// eastBank, boatByEastBank, boatByWestBank, westBank
	public void setBoatPosition(String position){
		waterLayers.remove(boatSprite);
		waterLayers.repaint();
		System.out.println("Boat at " + position);
		switch (position) {
			case "eastBank":
				//boatSprite.setBounds(290, 0, 200, 200);
				break;
			case "boatByEastBank":
				boatSprite.setBounds(290, 0, 200, 200);
				break;
			case "boatByWestBank":
				//boat sail to westbank
				boatSprite.setBounds(0, 0, 200, 200);
				break;
			case "westBank":
				
				break;
		}
		waterLayers.add(boatSprite);
	}
	public void setFarmerPosition(String position){
		waterLayers.remove(farmerSprite);
		eastBank.remove(farmerSprite);
		westBank.remove(farmerSprite);
		waterLayers.repaint();
		eastBank.repaint();westBank.repaint();;
		System.out.println("Farmer " + position);
		switch (position) {
		case "eastBank":
			
			eastBank.add(farmerSprite,0,0);
			break;
		case "boatByEastBank":
			farmerSprite.setBounds(290, 20, 200, 200);
			waterLayers.add(farmerSprite, new Integer(2));
			break;
		case "boatByWestBank":
			//boat sail to westbank
			waterLayers.add(farmerSprite, new Integer(2));
			farmerSprite.setBounds(0, 20, 200, 200);
			break;
		case "westBank":
			westBank.add(farmerSprite,0,0);
			break;
		}
	}
	public void setFoxPosition(){
		
	}
	public void setGoosePosition(){
		
	}
	public void setBeanPosition(){
		
	}
}
