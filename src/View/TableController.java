package View;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.sun.media.jfxmedia.Media;

import Controller.ControllerLogic;
import Exceptions.PlayerEndOfGameException;
import Exceptions.WhoWinException;
import Model.Card;
import Utils.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


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
	@FXML
	 Label playerMsg;
	
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
	
	@FXML
	AnchorPane wall;
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
	
	@FXML
	MenuItem mndeal;
	@FXML
	MenuItem mnstand;
	@FXML
	MenuItem mnhit;
	@FXML
	Button btnNewRound;
	@FXML
	Button btnNewGame;
	
	
	
	
	
	
	
	
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
		btnNewGame.setVisible(false);
		btnNewRound.setVisible(false);
		mnhit.setDisable(true);
		mnstand.setDisable(true);
		mndeal.setDisable(false);
		totalPoints.setText("Total score: "+ViewLogic.getChips());
		lblBet.setText("Bet: "+0);
		playerCardsValue.setText("Value:"+0);
		playerx=new Double(secondCardPlayer.getLayoutX());
		dealerx=new Double(secondCardDealer.getLayoutX());
		playerMsg.setVisible(false);
		playerMsg.setVisible(false);
		firstCardDealer.setVisible(false);
		secondCardDealer.setVisible(false);
		firstCardPlayer.setVisible(false);
		secondCardPlayer.setVisible(false);
			
	}
	
	
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	@FXML
	public void Deal()
	{
		if(ViewLogic.getBets()>0)
		{
			mnhit.setDisable(false);
			mnstand.setDisable(false);
			mndeal.setDisable(true);
			dealCardsToGame();
			firstCardDealer.setVisible(true);
			secondCardDealer.setVisible(true);
			firstCardPlayer.setVisible(true);
			secondCardPlayer.setVisible(true);
				
			
		}
		else
			SetMeg(true, "bet before deal");
		
	}
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	
	 private void dealCardsToGame()
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
			// if black jack
			try {
				ViewLogic.isBlackJack();
			} catch (PlayerEndOfGameException e) {
				playerMsg.setText(e.getMessage());
				playerMsg.setVisible(true);
			}
	}
	private void HideChips()
	{
		chip1.setVisible(false);
		chip5.setVisible(false);
		chip25.setVisible(false);
		chip50.setVisible(false);
		chip100.setVisible(false);
	}
	private void newTable()
	{
		playerMsg.setVisible(false);
		chip1.setVisible(true);
		chip5.setVisible(true);
		chip25.setVisible(true);
		chip50.setVisible(true);
		chip100.setVisible(true);
		btnDeal.setVisible(true);
		btnHit.setVisible(false);
		btnStand.setVisible(false);
		// clear all the crds add to dealear and player
		for(Object c: wall.getChildren().toArray())
			if(c instanceof ImageView&&((ImageView)c).getId()==null)
				wall.getChildren().remove(c);
	
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
		try {
			ViewLogic.isOver21();
		} catch (PlayerEndOfGameException e) {
			playerMsg.setText(e.getMessage());
			playerMsg.setVisible(true);
			flipDealerCard();
			btnNewGame.setVisible(true);
			btnNewRound.setVisible(true);
			loseLayOut();
		}
	}
	/**
	 * method set buttons hit and stand unvisble
	 * method add card to dealer until 17
	 * method use global parmter dealerx to put the card in the right place
	 */
	@FXML
	public void StandCard()
	{
		flipDealerCard();
		while(ViewLogic.isDealerNeedMoreCard())
		{
			Card c=ViewLogic.getCardFromDeck(User.Dealer);
			ImageView pic=new ImageView(new Image(c.getPic()));
			pic.setFitHeight(firstCardDealer.getFitHeight());
			pic.setFitWidth(firstCardDealer.getFitWidth()-28);
			pic.setLayoutX(dealerx+32);
			pic.setLayoutY(firstCardDealer.getLayoutY());
			ViewLogic.getPage().getChildren().add(pic);
			dealerx=pic.getLayoutX();
		}
		try {
			ViewLogic.checkWin();
		} catch (WhoWinException e) {
			playerMsg.setText(e.getMessage());
			playerMsg.setVisible(true);
			btnNewRound.setVisible(true);
			btnNewGame.setDisable(true);
			loseLayOut();
		}
	}
	
	private void loseLayOut()
	{
		if(ViewLogic.getChips() == 0)
		{
			btnNewRound.setVisible(false);
			btnNewGame.setVisible(true);
			btnNewGame.setDisable(false);
		}
	}
	private void flipDealerCard()
	{
		btnStand.setVisible(false);
		btnHit.setVisible(false);
		secondCardDealer.setImage(new Image(ViewLogic.getSecondCardOfDealer().getPic()));
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
//--------------------------- other methods---------------------------------------------------------
	/**
	 * wwhen button new game clicking intalize new game with all new parmeter
	 */
	@FXML
	public void clickNewGame()
	{
		ViewLogic.newGame();
		init();
		newTable();

	}
	/**
	 * wwhen button new game clicking intalize new game with all new parmeter
	 */
	@FXML
	public void clickNewRound()
	{
		ViewLogic.newRound();
		init();
		newTable();
		
	}
//--------------------------- set Message method---------------------------------------------------------
	/**
	 * the message to user will disappear when the mouse is move on the background picture.
	 */
@FXML
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


@FXML
public void ShowNewButtonPanel()
{
	panelNewButtons.setVisible(true);
}


////////////////////////////////////////////// open rules stage////////////////////////////
@FXML

public void clickRules()
{
	
	System.out.println("IN");
	try{
	  AnchorPane  page  = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Rules.fxml"));
	    Scene scene = new Scene(page);
        scene.getStylesheets().add("/view/TableCss.css");
        Stage  primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("BlackJack Enjoy!");
       primaryStage.getIcons().add(new Image("/view/photos/icon.png"));
       primaryStage.show();
       
} catch(Exception e) {
	e.printStackTrace();
}
}




			
			


	
		
		
}
