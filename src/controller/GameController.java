
package controller;
import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameController implements ActionListener{

	GameView view;
	GameModel model;
	public GameController(GameModel model){
		//riverGameView.setListener(this);
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = ((JButton)e.getSource());
			//Which button pressed? Name = farmer, or fox etc.  and  Text = < or >
			this.model.movePiece(btn.getName(), btn.getText());
			
			
		}
	}
	
}
