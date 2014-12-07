package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Timer;

import com.sun.prism.ResourceFactory;

import Model.Card;
import Utils.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	 * keep the total score off the player
	 */
	@FXML
	Label totalPoints;
	
	
	/**
	 * Label for display to user msg.
	 */
	@FXML
	Label msgToUser;
	

	/**
	 * Label for display the value of the cards of the player.
	 */
	@FXML
	Label playerCardsValue;
	
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
	
	@FXML
	ImageView chip25;
	
	@FXML
	ImageView chip5;
	
	@FXML
	ImageView chip1;
	
	@FXML
	public void init(){
		totalPoints.setText("Total score: "+ViewLogic.getChips());
		
		lblBet.setText("Bets: "+0);
	}
	
	
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	@FXML
	public void Deal()
	{
		// button deal will disappear after dealing the cards.
		btnDeal.setVisible(false);
		
		// Deal cards to the player//
		Card tempCard= ViewLogic.getCardFromDeck(User.Player);
		firstCardPlayer.setImage(new Image(tempCard.getPic()));

		tempCard= ViewLogic.getCardFromDeck(User.Player);
		secondCardPlayer.setImage(new Image(tempCard.getPic()));
		
		
		// Deal cards to the Dealer//
		tempCard= ViewLogic.getCardFromDeck(User.Dealer);
		firstCardDealer.setImage(new Image(tempCard.getPic()));	
		
		// the last card to the dealer in back card in the modelView.Dealer the card its save.
		tempCard= ViewLogic.getCardFromDeck(User.Dealer);
		secondCardDealer.setImage(new Image("/view/photos/BackCard.png"));	
		
		//update the value cards of the player after deal
		SetPlayerCradsValue(ViewLogic.playerValueCards());
		
	}
//-------------------------------------------chips Method Raise bets ----------------------------------------------------	
	@FXML
	public void RaiseBets100()
	{
		
		UpDatebets(100);
		
	}
	
	@FXML
	public void RaiseBets50()
	{
		
		UpDatebets(50);
		
	}
	
	@FXML
	public void RaiseBets25()
	{
		UpDatebets(25);
		
	}
	
	@FXML
	public void RaiseBets5()
	{
		UpDatebets(5);
		
	}
	
	@FXML
	public void RaiseBets1()
	{
		UpDatebets(1);
		
	}
	
//-------------------------------------------chips Method Raise bets The End ----------------------------------------------------	
	

	
//--------------------------- Update status bar method---------------------------------------------------------

	public void UpDatebets(int amount)
	{
		if(ViewLogic.setBets(amount))
		{
			
	   
		SetPlayerBetsIntheGame(ViewLogic.getBets());
		
		// get the total chips before raise the bets;
		SetTotalChips(ViewLogic.getChips());
		
		}
		else{
			SetMeg(true, "you are out of chips");
		}
	

}
//--------------------------- Update status bar method the End---------------------------------------------------------

	
//--------------------------- set Message method---------------------------------------------------------
	/**
	 * the message to user will disappear when the mouse is move on the background picture.
	 */
  public void DisapearMsg() {
	msgToUser.setVisible(false);
  }
	
	
  /**
   * make message will show when visible is True and set the text to arg in the msg parameter
   * @param visible
   * @param msg
   */
	private void SetMeg(Boolean visible, String msg ) {
		msgToUser.setVisible(visible);
		msgToUser.setText(msg);
	}
	
	
	 /**
	  * set player value on the status bar
	  * @param value
	  */
		private void SetPlayerCradsValue(int value)
     	 {
			playerCardsValue.setText("Value:"+ value);
		 }
		
		/**
		 * update the current bet in the game in status bar
		 * @param value
		 */
		private void SetPlayerBetsIntheGame(int value)
    	 {
			lblBet.setText("Bets: "+value);
		 }
		
		/**
		 * update total chips of the player on status bar
		 * @param value
		 */
		private void SetTotalChips(int value)
   	    {
			totalPoints.setText("Total chips: "+value);
		 }
		
		
	
	
//--------------------------- set Message method the End---------------------------------------------------------

}
