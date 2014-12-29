package View;

import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.PlayerEndOfGameException;
import Exceptions.WhoWinException;
import Model.Card;
import Utils.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TableController implements Initializable {
	/** variable save to place of the last card for player*/
	private Double playerx;
	/** variable save to place of the last card for player*/
	private Double dealerx;
	/** This variable is contain the card that come into the table */
	static ImageView pic;
	/** This variable is contain the the diff between x axle in deck card to x axle in last card on the table */
	static double mx;
	/** This variable is contain the the diff between y axle in deck card to y axle in last card on the table */
	static double my;
	
	/**
	 * the button that start the dealing action
	 */
	@FXML
	Button btnDeal;
	
	/**
	 * label present message to user
	 */
	@FXML
	ImageView msgToUserPic;
	
	/**
	 * this btn bring more card to player
	 */
	@FXML
	Button btnHit;
	/**
	 * this btn pass the turn to dealer
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
	 * keep the total wins off the player
	 */
	@FXML
	Label lblWins;
	
	/**
	 * keep the total Losses off the player
	 */
	@FXML
	Label lblLosses;
	
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
	 * first card of the Dealer
	 */
	@FXML
	ImageView firstCardDealer;
	
	/**
	 * second card of the Dealer
	 */
	@FXML
	ImageView secondCardDealer;
	
	/**
	 * card from deck
	 */
	@FXML
	ImageView deckCard;
	
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
	
	@FXML
	Button btnExit;
	
	@FXML
	Button btnResetBet;
	
	////////////////////////////////////////////load method//////////////////////////////////////////////////////////////
	/**
	 * method initialize table form ( load)- initialize the total chips show to player
	 */
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
	}
/////////////////////////////////////////fxml method//////////////////////////////////////////////////////////	
	@FXML
	public void init(){
		
		// layout of the btn
		btnNewGame.setVisible(false);
		btnNewRound.setVisible(false);
		EnabledDealMenuAndBtn(true);
		EnbledHitAndStandMenu(false);
		
		firstCardDealer.setVisible(false);
		secondCardDealer.setVisible(false);
		firstCardPlayer.setVisible(false);
		btnExit.setVisible(false);
		btnExit.setDisable(true);
		msgToUserPic.setVisible(false);
		
		// set status bar
		totalPoints.setText("Total score: "+ViewLogic.getChips());
		lblBet.setText("Bet: "+0);
		playerCardsValue.setText("Value: "+0);
	
		// init location of cards
		playerx=new Double(firstCardPlayer.getLayoutX());
		dealerx=new Double(firstCardDealer.getLayoutX());
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
			EnabledDealMenuAndBtn(false);
			
			// show buttons hot and stands
			EnbledHitAndStandMenu(true);
			dealCardsToGame();
			firstCardDealer.setVisible(true);
			secondCardDealer.setVisible(true);
			firstCardPlayer.setVisible(true);
			
		}
		else
			SetMeg(true, "bet before deal");
		
	}
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	
	 private void dealCardsToGame()
	{
			// Deal cards to the player//
			Card tempCard= ViewLogic.getCardFromDeck(User.Player);
			//SetCardOntheTable(User.Player, firstCardPlayer, playerx);
			firstCardPlayer.setImage(new Image(tempCard.getPic()));

			tempCard= ViewLogic.getCardFromDeck(User.Player);
			SetCardOntheTable(User.Player, firstCardPlayer, playerx);
			
			
			// Deal cards to the Dealer//
			tempCard= ViewLogic.getCardFromDeck(User.Dealer);
			firstCardDealer.setImage(new Image(tempCard.getPic()));	
			
			// the last card to the dealer in back card in the modelView.Dealer the card its save.
			tempCard= ViewLogic.getCardFromDeck(User.Dealer);
			//SetCardOntheTable(User.Dealer, firstCardDealer, dealerx);
			secondCardDealer.setImage(new Image("/view/photos/BackCard.png"));	
			//secondCardDealer.setImage(pic.getImage());	
			
			//update the value cards of the player after deal
			SetPlayerCradsValue(ViewLogic.playerValueCards());
			
			
			// if black jack
			try {
				ViewLogic.isBlackJack();
			} catch (PlayerEndOfGameException e) {
				flipDealerCard();
				endOfRoundLayOut(e.getMessage());
			}
	}
	 
	 /**
	  * create layout of a new table
	  */
	private void newTable()
	{
		
		EnabledDealMenuAndBtn(true);
		EnbledHitAndStandMenu(false);;
		
		// clear all the cards add to dealer and player
		for(Object c: wall.getChildren().toArray())
			if(c instanceof ImageView&&((ImageView)c).getId()==null)
				wall.getChildren().remove(c);
	
	}
	
	
	/**
	 
	 * method add one card to player every push
	 * method use global parameter playerx to put the card in the right place
	 */
	
	@FXML
	public void hitCard()
	{
		SetCardOntheTable(User.Player, firstCardPlayer, playerx);
		SetPlayerCradsValue(ViewLogic.playerValueCards());
		try {
			if(ViewLogic.isExactly21())
			{
				StandCard();
				return;
			}
			ViewLogic.isOver21();
		} catch (PlayerEndOfGameException e) {
			flipDealerCard();
			endOfRoundLayOut(e.getMessage());
		} 
	}
	/**
	 * method set buttons hit and stand invisible
	 * method add card to dealer until 17
	 * method use global parameter dealerx to put the card in the right place
	 */
	@FXML
	public void StandCard()
	{
		flipDealerCard();
		while(ViewLogic.isDealerNeedMoreCard())
		{
			SetCardOntheTable(User.Dealer, firstCardDealer, dealerx);
		}
		try {
			ViewLogic.checkWin();
		    } catch (WhoWinException e) {
			endOfRoundLayOut(e.getMessage());
		  }
	}
//-------------------------------------------layOut Method ----------------------------------------------------	
	
	/**
	 * create and setting new card on the table , player and dealer using in this method
	 * @param user
	 * @param firstCard
	 * @param cardPosition
	 * @return
	 */
	private void SetCardOntheTable(final User user, ImageView firstCard, Double cardPosition)
	{
		Card c=ViewLogic.getCardFromDeck(user);
		pic=new ImageView(new Image(c.getPic()));
		pic.setVisible(true);
		
		//set size of card
		pic.setFitHeight(firstCard.getFitHeight());
		pic.setFitWidth(firstCard.getFitWidth()-28);
		
		// Allocate place on screen
		double i=deckCard.getLayoutX();
		double j=deckCard.getLayoutY();
		
		//set first location in screen
		pic.setLayoutX(i);
		pic.setLayoutY(j);
		
		ViewLogic.getPage().getChildren().add(pic);
		
		double x;
		
		if (user.equals(User.Player)){
			playerx+=32;
			x=playerx;
		}
		else{
			dealerx+=32;
			x=dealerx;
		}
		//double layoutX=cardPosition+32;
		double layoutY=firstCard.getLayoutY();
	
		mx = x-i;
		my = layoutY-j;
		
		//System.out.println("playerx: "+playerx+"\ni: "+i);
		
		 final Timeline tl = new Timeline();
	        tl.setCycleCount(Animation.INDEFINITE);
	        KeyFrame moveCard = new KeyFrame(Duration.seconds(.0030),
	                new EventHandler<ActionEvent>() {

	                    public void handle(ActionEvent event) {
	                    		
		                        double xSrc = pic.getTranslateX();
		                        double ySrc = pic.getTranslateY();
		                        double xTarg = mx;
		                        double yTarg = my;
		                        
		                       // System.out.println("xSrc"+xSrc+"\nySrc"+ySrc);
		                        
		                        //System.out.println("xTarg"+xTarg+"\nyTarg"+yTarg);
		                        
		                        if (xSrc>xTarg) {
		                            pic.setTranslateX(pic.getTranslateX()-1);
		                        }
		                        if (user.equals(User.Player)){
		                        	if (ySrc<yTarg) {
		                        		pic.setTranslateY(pic.getTranslateY()+1);
		                        	}
		                        	if (xSrc<=xTarg && ySrc>=yTarg)
		                        		tl.stop();
		                        }
		                        else
		                        {
		                        	if (ySrc>yTarg) {
		                        		pic.setTranslateY(pic.getTranslateY()-1);
		                        	}
		                        	if (xSrc<=xTarg && ySrc<=yTarg)
		                        		tl.stop();
		                        }
	                    }
	                });

	        tl.getKeyFrames().add(moveCard);
	        tl.play();
	        
		//return x;
	}

	
	/**
	 * set table layout according to the end of round 
	 * @param msgToUser
	 */
	private void endOfRoundLayOut(String msgToUser)
	{
		EnbledHitAndStandMenu(false);
		msgToUserPic.setImage(new Image(msgToUser));
		msgToUserPic.setVisible(true);
		btnNewGame.setVisible(true);
		btnNewRound.setVisible(true);
		lblWins.setText("Wins: "+ViewLogic.getPlayerWins());
		lblLosses.setText("Losses: "+ViewLogic.getPlayerLosses());
		loseLayOut();
	}
	
	/**
	 *  set table layout according to the end of game  there only two option new Game or exit from the game
	 */
	private void loseLayOut()
	{
		if(ViewLogic.getChips() == 0)
		{
			btnNewRound.setVisible(false);
			btnNewGame.setVisible(true);
			btnNewGame.setDisable(false);
			btnExit.setVisible(true);
			btnExit.setDisable(false);
			EnbledHitAndStandMenu(false);
		}
	}
	
	/**
	 * Disable hit and stand btn from the menu bar and hide btn stand and hit forn table.
	 * @param value
	 */
	private void EnbledHitAndStandMenu(boolean value)
	{
		mnhit.setDisable(!value);
		mnstand.setDisable(!value);
		btnStand.setVisible(value);
		btnHit.setVisible(value);
	}
	
	/**
	 * Disable Deal btn from the menu bar and table.
	 * @param value
	 */
	private void EnabledDealMenuAndBtn(boolean value)
	{
		mndeal.setDisable(!value);
		btnDeal.setVisible(value);
	    ShowChips(value);
	}
	/**
	 * filp the card of the dealer
	 */
	private void flipDealerCard()
	{
		EnbledHitAndStandMenu(false);
		secondCardDealer.setImage(new Image(ViewLogic.getSecondCardOfDealer().getPic()));
	}
	
	/**
	 * hide or show all the chips
	 * @param value
	 */
	private void ShowChips(boolean value)
	{
		chip1.setVisible(value);
		chip5.setVisible(value);
		chip25.setVisible(value);
		chip50.setVisible(value);
		chip100.setVisible(value);
		btnResetBet.setVisible(value);
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

	
//--------------------------- Update status bar method---------------------------------------------------------
	/**
	 * set value of bets after chose chip
	 * @param amount
	 */
	public void UpDatebets(int amount)
	{
		if(ViewLogic.setBets(amount))
		{
			updateBetValueOnTable();
		}
		else{
			SetMeg(true, "you are out of chips");
		}
	}
	
	/**
	 * set the value of bets on the screen
	 */
	public void updateBetValueOnTable(){
		SetPlayerBetsIntheGame(ViewLogic.getBets());
		// get the total chips before raise the bets;
		SetTotalChips(ViewLogic.getChips()-ViewLogic.getBets());
	}
//--------------------------- other methods---------------------------------------------------------
	/**
	 * when button new game clicking initialize new game with all new parameter
	 */
	@FXML
	public void clickNewGame()
	{
		btnExit.setVisible(false);
		btnExit.setDisable(true);
		ViewLogic.newGame();
		init();
		newTable();
		lblWins.setText("Wins: "+0);
		lblLosses.setText("Losses: "+0);
		
	}
	/**
	 * when button new game clicking initialize new game with all new parameter
	 */
	@FXML
	public void clickNewRound()
	{
		ViewLogic.newRound();
		init();
		newTable();
	}
	
	/**
	 * when button Reset Bet clicking initialize bets Of Player with zero
	 */
	@FXML
	public void clickResetBet(){
		ViewLogic.resetBet();
		updateBetValueOnTable();
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
	 `  */
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
	try{
	    AnchorPane  page  = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Rules.fxml"));
	    Scene scene = new Scene(page);
        scene.getStylesheets().add("/view/TableCss.css");
        Stage  primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("BlackJack Enjoy!");
        primaryStage.getIcons().add(new Image("/view/photos/icon.png"));
        setWindowSize(primaryStage, 573, 510);
        primaryStage.show();
       
} catch(Exception e) {
	e.printStackTrace();
}
}


public void setWindowSize(Stage primaryStage,int width, int height)
{
	// max
	primaryStage.setMaxWidth(width);
    primaryStage.setMaxHeight(height);
    
    // min
    primaryStage.setMinHeight(height);
    primaryStage.setMinWidth(width);
}

 @FXML
public void CloseWindow()
{
  if(btnExit.isVisible())
	 ViewLogic.close();
}

			
			


	
		
		
}
