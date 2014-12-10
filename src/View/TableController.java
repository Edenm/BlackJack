package View;


import java.net.URL;
import java.util.ResourceBundle;
import Model.Card;
import Utils.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class TableController implements Initializable {
	/**
	 * varible save to place of the last card for player
	 */
	private Double playerx;
	/**
	 * varible save to place of the last card for player
	 */
	private Double dealerx;
	
	/**
	 * the button that start the dealing action
	 */
	@FXML
	Button btnDeal;
	
	/**
	 * 
	 */
	@FXML
	Button btnHit;
	/**
	 * 
	 */
	@FXML
	Button btnStand;
	
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
	ImageView backgroudTable;
	
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
	Pane panelNewButtons;
	
	@FXML
	Label lblOptions;
	////////////////////////////////////////////load method//////////////////////////////////////////////////////////////
	/**
	 * method intalize table form ( load)- intalize the total chips show to player
	 */
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
	}
/////////////////////////////////////////fxml method//////////////////////////////////////////////////////////	
	@FXML
	public void init(){
		totalPoints.setText("Total score: "+ViewLogic.getChips());
		
		lblBet.setText("Bet: "+0);
		playerx=new Double(secondCardPlayer.getLayoutX());
		dealerx=new Double(secondCardDealer.getLayoutX());
	}
	
	
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	@FXML
	public void Deal()
	{
		if(ViewLogic.getBets()>0)
		{
		// button deal will disappear after dealing the cards.
		btnDeal.setVisible(false);
		HideChips();
		
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
		
		// show buttons hot and stands
		btnHit.setVisible(true);
		btnStand.setVisible(true);
		}
		else
			SetMeg(true, "bet before deal");
		
	}
	
	
	
	public void HideChips()
	{
		chip1.setVisible(false);
		chip5.setVisible(false);
		chip25.setVisible(false);
		chip50.setVisible(false);
		chip100.setVisible(false);
	}
	/**
	 
	 * method add one card to player every push
	 * method use global parmter playerx to put the card in the right place
	 */
	
	@FXML
	public void hitCard()
	{
		Card c=ViewLogic.getCardFromDeck(User.Player);
		ImageView pic=new ImageView(new Image(c.getPic()));
		pic.setFitHeight(firstCardPlayer.getFitHeight());
		pic.setFitWidth(secondCardPlayer.getFitWidth()-28);
		pic.setLayoutX(playerx+32);
		pic.setLayoutY(firstCardPlayer.getLayoutY());
		ViewLogic.getPage().getChildren().add(pic);
		playerx=pic.getLayoutX();
		SetPlayerCradsValue(ViewLogic.playerValueCards());
		
		
	}
	/**
	 * method set buttons hit and stand unvisble
	 * method add card to dealer until 17
	 * method use global parmter dealerx to put the card in the right place
	 */
	@FXML
	public void StandCard()
	{
		btnStand.setVisible(false);
		btnHit.setVisible(false);
		Card c=ViewLogic.getCardFromDeck(User.Dealer);
		ImageView pic=new ImageView(new Image(c.getPic()));
		pic.setFitHeight(firstCardDealer.getFitHeight());
		pic.setFitWidth(firstCardDealer.getFitWidth()-28);
		pic.setLayoutX(dealerx+32);
		pic.setLayoutY(firstCardDealer.getLayoutY());
		ViewLogic.getPage().getChildren().add(pic);
		dealerx=pic.getLayoutX();
		
		
		
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
		SetTotalChips(ViewLogic.getChips()-ViewLogic.getBets());
		
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
@FXML
  public void DisapearMsg() {
	msgToUser.setVisible(false);
	HideNewButtonPanel();
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
@FXML
public void HideNewButtonPanel()
{
	panelNewButtons.setVisible(false);
}

@FXML
public void ShowNewButtonPanel()
{
	panelNewButtons.setVisible(true);
}




	
		
		
}
