package View;

import java.awt.Color;
import java.net.URL;
import java.util.Timer;

import com.sun.prism.ResourceFactory;

import Model.Card;
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
		// set bet to 0 --> new game
		lblBet.setText("Bets: "+0);
		
		Card tempCard= ViewLogic.getCardFromDeck(1);
		System.out.println(tempCard.getPic());
		firstCardPlayer.setImage(new Image(tempCard.getPic()));

		tempCard= ViewLogic.getCardFromDeck(1);
		System.out.println(tempCard.getPic());
		secondCardPlayer.setImage(new Image(tempCard.getPic()));
		System.out.println(tempCard.getPic());
		tempCard= ViewLogic.getCardFromDeck(1);
		firstCardDealer.setImage(new Image(tempCard.getPic()));	
		
		tempCard= ViewLogic.getCardFromDeck(1);
		secondCardDealer.setImage(new Image("/view/photos/BackCard.png"));	
		
	}
	
	
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
	
	
	public void UpDatebets(int amount)
	{
		if(ViewLogic.setBets(amount))
		{
	    int totalAmount= amount+ ViewLogic.getBets();
		lblBet.setText("Bets: "+totalAmount);
		
		// get the total chips before rasie the bets;
		int totalChips= ViewLogic.getChips();
		
		int chipsAfterTheRaise= totalChips-amount;
		
		totalPoints.setText("Total score: "+ chipsAfterTheRaise);
		
		checkChipMin();
	}
		else {
			msgToUser.setText("you are out of chips");
			
		}
	

}


	private void checkChipMin() {
		// TODO Auto-generated method stub
		
	}
}
