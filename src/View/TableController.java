package View;

import java.awt.Color;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

public class TableController {
	
	// will be delete when we connected MVC
	int bet=0;
	/**
	 * the button that start the dealing action
	 */
	@FXML
	Button btnDeal;
	
	/**
	 * showing what is the current bet in the game
	 */
	@FXML
	Label lblBet;
	
	/**
	 *  first card of the player
	 */
	@FXML
	ImageView firstCardPlayer;
	
	/**
	 * second card of the player
	 */
	@FXML
	ImageView secondCardPlayer;
	
	/**
	 * first card of the Dealer
	 */
	@FXML
	ImageView firstCardDealer;
	
	/**
	 * second card of the Dealer
	 */
	@FXML
	ImageView secondCardDealer;
	
	@FXML
	ImageView chip100;
	
	@FXML
	ImageView chip50;
	
	
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	@FXML
	public void Deal()
	{
		
	}
	
	@FXML
	public void RaiseBets100()
	{
		bet+=100;
		lblBet.setText("Bet: "+bet);
		chip100.setVisible(false);
		chip100.setDisable(false);
		
	}
	
	@FXML
	public void RaiseBets50()
	{
		
		bet+=50;
		lblBet.setText("Bet: "+bet);
		chip50.setVisible(false);
		chip50.setDisable(false);
		
	}
	
	
	
	

}
