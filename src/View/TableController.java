package View;

import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.PlayerEndOfGameException;
import Exceptions.WhoWinException;
import Model.Card;
import Utils.Constants;
import Utils.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
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
	/** Contains true if the first card of Player didn't deal*/
	static boolean isFirstCardPlayer =true;
	/** Contains true if the first card of Player didn't deal*/
	static boolean isFirstCardDealer =true;
	/** number of cards for first deal*/ 
	static int numOfCards=0;
	/** Contains the next car move*/ 
	static User user=User.Player;
	/** Contains status of game*/ 
	static Utils.Status status;
	/** Current card*/
	static Card currentCard;
	/** This variable is contain the second card that come into the table for the dealer */
	static ImageView picSecondCardDealer;
	/** Second card dealer*/
	static Card secondCardDealer;
	/** Timeline variable*/ 
	final Timeline tl = new Timeline();
	
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
	 * Label for display the value of the cards of the dealer.
	 */
	@FXML
	Label dealerCardsValue;

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
	 * card from deck
	 */
	@FXML
	ImageView deckCard;
	/**
	 * Image background Table
	 */
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
	
	@FXML
	Pane p;
	
	LoginController login;
	
	
	////////////////////////////////////////////load method//////////////////////////////////////////////////////////////
	/**
	 * method initialize table form ( load)- initialize the total chips show to player
	 */
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
	}
	/////////////////////////////////////////fxml method//////////////////////////////////////////////////////////	
	/**
	 * the method initialize the table to state of start game
	 */
	@FXML
	public void init(){
		p.setTranslateY(-p.getPrefHeight());
		
		// layout of the btn
		btnNewGame.setVisible(false);
		btnNewRound.setVisible(false);
		EnabledDealMenuAndBtn(true);
		EnbledHitAndStandMenu(false);
		
		btnExit.setVisible(false);
		btnExit.setDisable(true);
		msgToUserPic.setVisible(false);
		
		// set status bar
		totalPoints.setText("Total score: "+ViewLogic.getChips());
		lblBet.setText("Bet: "+0);
		playerCardsValue.setText("Player: "+0);
		dealerCardsValue.setText("Dealer: "+0);
	
		// init location of cards
		playerx=new Double(Constants.cardXLayout);
		dealerx=new Double(Constants.cardXLayout);
	}
	
	
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	public void Deal()
	{
		// checking that the player has a bets on the table
		if(ViewLogic.getBets()>0)
		{
			status = Utils.Status.deal;
			
			// button deal will disappear after dealing the cards.
			EnabledDealMenuAndBtn(false);
			
			// show buttons hot and stands
			EnbledHitAndStandMenu(true);
		
			// start dealing 
			dealCardsToGame();
		}
		else
			SetMeg(true, "bet before deal");
		
	}
	/**
	 * this method will deal the cards (2 cards each) between the dealer and player 
	 */
	
	 private void dealCardsToGame()
	{
		 // change status game too deal
		status = Utils.Status.deal;
		
		// number of card that i want to deal
	    numOfCards =4;
	    
	    // the user that should get the next card
		user=User.Player;
		
		// start dealing cards
		SetCardOntheTable();
	}
	 
	 /**
	  * check if there is a black jack to the player 
	  */
	 private void isBlackJack(){
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
		// enabling buttons
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
		status = Utils.Status.hit;
		user=User.Player;
		SetCardOntheTable();
		SetPlayerCradsValue(ViewLogic.playerValueCards());
		if(ViewLogic.getCards().size()==2)
			SetDealerCradsValue(ViewLogic.dealerValueCards()-secondCardDealer.getValue());
		else
			SetDealerCradsValue(ViewLogic.dealerValueCards());
	}
	
	/**
	 * the method is Happens after hit
	 */
	private void CheckAfterHit() {
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
	 * method of deal button
	 */
	@FXML
	public void DealCard()
	{
		SetCardOntheTable();
		SetPlayerCradsValue(ViewLogic.playerValueCards());
		if(ViewLogic.getCards().size()==2)
			SetDealerCradsValue(ViewLogic.dealerValueCards()-secondCardDealer.getValue());
		else
			SetDealerCradsValue(ViewLogic.dealerValueCards());
	
		
	}
	
	/**
	 * method set buttons hit and stand invisible
	 * method add card to dealer until 17
	 * method use global parameter dealerx to put the card in the right place
	 */
	@FXML
	public void StandCard()
	{
		status = Utils.Status.stand;
		user=User.Dealer;
		flipDealerCard();
		
		EnbledHitAndStandMenu(false);
		if(ViewLogic.isDealerNeedMoreCard())
			SetCardOntheTable();
		else
			CheckWhoWin();
	}
	
	/**
	 * the method happens after dealer finish to play
	 */
	private void CheckWhoWin()
	{
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
	 */
	private void SetCardOntheTable()
	{
		setPicSizeAndLocation();
		
		double x;
		
		if (user.equals(User.Player)){
			x = setPlayerNewCard();
		  }
		else{
			x = setDealerNewCard();
			
		}
		// the distance that the card should move 
		mx = x-Constants.deckCardLayoutX;
		
	    tl.setCycleCount(Animation.INDEFINITE);
	    
	    KeyFrame moveCard = upDateLocationCard();
	    
	        tl.getKeyFrames().add(moveCard);
	        tl.play();
	     
	}
	
	/**
	 * Update the current location of the card
	 * @return KeyFrame
	 */
	private KeyFrame upDateLocationCard() {
		return new KeyFrame(Duration.seconds(.0020),
	            new EventHandler<ActionEvent>() {
		
	                public void handle(ActionEvent event) {
	                		
	                        double xSrc = pic.getTranslateX();
	                        double ySrc = pic.getTranslateY();
	                        double xTarg = mx;
	                        double yTarg = my;
	                     
	                        if (xSrc>xTarg) {
	                            pic.setTranslateX(pic.getTranslateX()-1);
	                        }
	                        if (user.equals(User.Player)){
	                        	if (ySrc<yTarg) {
	                        		pic.setTranslateY(pic.getTranslateY()+1);
	                        	}
	                        	if (xSrc<=xTarg && ySrc>=yTarg){
		                        		tl.stop();
		                        		flipCard();
		                        		if(--numOfCards> 0)
		                        		{
			                        		switchUser();
			                        		DealCard();
			                        		return;
		                        		}
		                        		else
		                        		{
		                        			numOfCards=0;
		                        			nextMove();
		                        			return;
		                        		}
	                        	}
	                        }
	                        else
	                        {
	                        	if (ySrc>yTarg) {
	                        		pic.setTranslateY(pic.getTranslateY()-1);
	                        	}
	                        	if (xSrc<=xTarg && ySrc<=yTarg){
	                        		tl.stop();
	                        		if(numOfCards!=1)
	                        			flipCard();
	                        		if(--numOfCards > 0)
	                        		{
		                        		switchUser();
		                        		DealCard();
		                        		return;
		                        	}
		                        	else
		                        	{
		                        		numOfCards=0;
		                        		nextMove();			
		                        		return;
		                        	} 
	                        		
	                        		
	                        		
	                        	}
	                        }
	                }
	            });
	}
	
	/**
	 * the method decide what is the status of game and change it
	 * additional move the game to next move
	 */
	private void nextMove() {
		
		switch (status) {
		case deal:
			  isBlackJack();
			  break;
			  
		case stand:
			StandCard();
			break;
	    
		case hit:
			CheckAfterHit();
			break;
			
		default:
			break;
		}
	}

	/**
	 * calculate and update the different between deck and dealer cards
	 * @return the results
	 */
	private double setDealerNewCard() {
		double x;
		if (!isFirstCardDealer){
			x=dealerx+Constants.diffXCard;
			isFirstCardDealer=false;
			
			
		}
		else{
			x=dealerx;
			
		}
		// the distance that the card should move 
		dealerx+=Constants.diffXCard;
		
		my = Constants.cardDealerLayoutY-Constants.deckCardLayoutY;
		return x;
	}

	/**
	 * calculate and update the different between deck and player cards
	 * @return the results
	 */
	private double setPlayerNewCard() {
		double x;
		if (!isFirstCardPlayer){
			x=playerx+Constants.diffXCard;
			isFirstCardPlayer=false;
		}
		else{
			x=playerx;
		}
		playerx+=Constants.diffXCard;
		my = Constants.cardPlayerLayoutY-Constants.deckCardLayoutY;
		return x;
	}
	
	/**
	 * initialize the new current card
	 */
	private void setPicSizeAndLocation() {
		currentCard=ViewLogic.getCardFromDeck(user);
		pic=new ImageView(new Image("/view/photos/BackCard.png"));
		
		if (numOfCards==1){
			secondCardDealer=currentCard;
			picSecondCardDealer=pic;
		}
		
		pic.setVisible(true);
		//set size of card
		pic.setFitHeight(Constants.cardHeight);
		pic.setFitWidth(Constants.cardWidth);
		
		
		//set first location in screen
		pic.setLayoutX(Constants.deckCardLayoutX);
		pic.setLayoutY(Constants.deckCardLayoutY);
		
		ViewLogic.getPage().getChildren().add(pic);
	}
	
	/**
	 * the method switch the current user
	 */
	private void switchUser() {
		if(user.equals(User.Player))
			user= User.Dealer;
		else
			user= User.Player;
	}
	
	/**
	 * flip the the current card
	 */
	public void flipCard(){
		pic.setImage(new Image(currentCard.getPic()));
		
	}
	
	/**
	 * flip the the second card of dealer
	 */
	private void flipDealerCard()
	{
		picSecondCardDealer.setImage(new Image(secondCardDealer.getPic()));
		SetDealerCradsValue(ViewLogic.dealerValueCards());
		
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
		if(ViewLogic.getChips() ==0)
		{
			btnNewRound.setVisible(false);
			btnNewGame.setVisible(true);
			btnNewGame.setDisable(false);
			btnExit.setVisible(true);
			btnExit.setDisable(false);
			EnbledHitAndStandMenu(false);
			SlideDownGameOverPanel();
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
	 * Disable Deal button from the menu bar and table.
	 * @param value
	 */
	private void EnabledDealMenuAndBtn(boolean value)
	{
		mndeal.setDisable(!value);
		btnDeal.setVisible(value);
	    ShowChips(value);
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
		playSoundChips();
		UpDatebets(100);
	}
	
	@FXML
	public void RaiseBets50()
	{
		playSoundChips();
		UpDatebets(50);
	}
	
	@FXML
	public void RaiseBets25()
	{
		playSoundChips();
		UpDatebets(25);
	}
	
	@FXML
	public void RaiseBets5()
	{
		playSoundChips();
		UpDatebets(5);
	}
	
	@FXML
	public void RaiseBets1()
	{
		playSoundChips();
		UpDatebets(1);
	}
	/**
	 * method play the sound on clicking the chips
	 */
	private void playSoundChips()
	{
		 URL thing = getClass().getResource("/photos/chips.wav");
		    Media audioFile = new Media( thing.toString() );     
		    try
		    {                                       
		        MediaPlayer player = new MediaPlayer(audioFile);
		        player.play();
		    }
		    catch (Exception e)
		    {
		        System.out.println( e.getMessage() );
		    } 
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
		p.toFront();
		ViewLogic.resetBet();
		updateBetValueOnTable();
	}
	/**
	 * when new game clicking display a message for the user
	 */
	@FXML
	public void newGameMsg(){
		showDialog();
	}
		
	

	
//----------------------------------- slide panel method ------------------------------------------------------------------//
	/**
	 * this method animate the game over panel from, top to center
	 */
	private void SlideDownGameOverPanel() {
		my=0;
			//----------------------------------
			 final Timeline tl = new Timeline();
		        tl.setCycleCount(Animation.INDEFINITE);
		        KeyFrame moveCard = new KeyFrame(Duration.seconds(.0020),
		                new EventHandler<ActionEvent>() {

		                    public void handle(ActionEvent event) {
			                        if (my<p.getPrefHeight()) {
			                        	my++;
			                            p.setTranslateY(-p.getPrefHeight()+my);
			                            p.toFront();
			                        }
			             
			                        else
			                        {
			                        		tl.stop();
			                        		p.toFront();
			                        }
		                    }
		                });

		        tl.getKeyFrames().add(moveCard);
		        tl.play();
	}
	
	
//-----------------------------message dialog------------------------------------------------------------//
/**
 * this method create a dialog box to the user while he click on new game	
 */
public void showDialog(){
		
		final Stage dialogStage = new Stage();
		dialogStage.setTitle("Attention!");
		Button yesBtn = new Button("Yes");
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setStyle("-fx-background-color: #08f252; -fx-text-fill:#ffffff ; -fx-font-size: 15");
		yesBtn.setStyle("-fx-background-color: #08f252; -fx-text-fill:#ffffff; -fx-font-size: 15");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		HBox hBox= new HBox();
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setSpacing(20.0);
		hBox.getChildren().addAll(yesBtn,cancelBtn);
		VBox dialogVbox = new VBox();
		dialogVbox.setSpacing(20.0);
		dialogVbox.setPadding(new Insets(20));
		Label newGameLabel = new Label("Are you sure you want to start a new game?\n"+"       "+"Youre score will reset to 500");
		newGameLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 18; -fx-text-fill:#2f4f4f");
		dialogVbox.getChildren().addAll(newGameLabel,hBox);
		newGameLabel.setAlignment(Pos.CENTER);
		Scene dialogScene = new Scene(dialogVbox,450,200);
		dialogStage.setScene(dialogScene);
		
		wall.setDisable(true);
		dialogStage.setAlwaysOnTop(true);
		dialogStage.show();
		
		
		
		
		
		yesBtn.setOnAction(new EventHandler<ActionEvent>() {
		//	@Override
			public void handle(ActionEvent event){
				dialogStage.setAlwaysOnTop(false);
				wall.setDisable(false);
				clickNewGame();
				dialogStage.close();
			}
		});
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			//@Override
			public void handle(ActionEvent event){
				wall.setDisable(false);
				dialogStage.setAlwaysOnTop(false);
				dialogStage.close();
			}
		});

	   
	}

//-----------------------------logout message------------------------------------------------------------
	/**
	 * this method create dialog box to the user while he click on logout
	 */
	@FXML
	public void createLogoutMsg(){
		final Stage dialogStage = new Stage();
		dialogStage.setTitle("Attention!");
		Button yesBtn = new Button("Yes");
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setStyle("-fx-background-color: #08f252; -fx-text-fill:#ffffff ; -fx-font-size: 15");
		yesBtn.setStyle("-fx-background-color: #08f252; -fx-text-fill:#ffffff; -fx-font-size: 15");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		HBox hBox= new HBox();
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setSpacing(20.0);
		hBox.getChildren().addAll(yesBtn,cancelBtn);
		VBox dialogVbox = new VBox();
		dialogVbox.setSpacing(20.0);
		dialogVbox.setPadding(new Insets(20));
		Label newGameLabel = new Label("      Are you sure you want to logout?\n"+"Please note that all your game scores will be deleted");
		newGameLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 18; -fx-text-fill:#2f4f4f");
		dialogVbox.getChildren().addAll(newGameLabel,hBox);
		newGameLabel.setAlignment(Pos.CENTER);
		Scene dialogScene = new Scene(dialogVbox,450,200);
		dialogStage.setScene(dialogScene);
		
		wall.setDisable(true);
		dialogStage.setAlwaysOnTop(true);
		dialogStage.show();
		
		
		
		
		
		yesBtn.setOnAction(new EventHandler<ActionEvent>() {
	//		@Override
			public void handle(ActionEvent event){
				dialogStage.setAlwaysOnTop(false);
				wall.setDisable(false);
				login = new LoginController();
				//login.btnLogin.setVisible(true);
				//login.txtnameLogin.setVisible(true);
				//login.playVideo();
				dialogStage.close();
				//ViewLogic.close();
			//	login.videosTOP();
				
			}
		});
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			//@Override
			public void handle(ActionEvent event){
				wall.setDisable(false);
				dialogStage.setAlwaysOnTop(false);
				dialogStage.close();
			}
		});

	   
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
			playerCardsValue.setText("Player:"+ value);
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
		
		/**
	    * set dealer value on the status bar
	    * @param value
	    */
		private void SetDealerCradsValue(int value)
		{
			dealerCardsValue.setText("Dealer:"+ value);
		}
	
	
//--------------------------- set Message method the End---------------------------------------------------------

/**
 * show new button panel
 */
@FXML
public void ShowNewButtonPanel()
{
	panelNewButtons.setVisible(true);
}

/**
 * loading the rule window and make the window visible
 */
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

/**
 * setting the  max window size
 * @param primaryStage
 * @param width
 * @param height
 */
public void setWindowSize(Stage primaryStage,int width, int height)
{
	// max
	primaryStage.setMaxWidth(width);
    primaryStage.setMaxHeight(height);
    
    // min
    primaryStage.setMinHeight(height);
    primaryStage.setMinWidth(width);
}

/**
 * close app
 */
 @FXML
public void CloseWindow()
{
  if(btnExit.isVisible())
	 ViewLogic.close();
}

			
			


	
		
		
}
