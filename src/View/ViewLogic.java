package View;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.*;
import Exceptions.PlayerEndOfGameException;
import Exceptions.WhoWinException;
import Model.Card;
import Utils.User;

/**
 * View Logic class
 * @author Eden
 */
public final class ViewLogic  extends Application {
	//***************************************** Variables *********************************************
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	@SuppressWarnings("unused")
	private static ViewLogic instance ;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**ControllerLogic reference pointer*/
	private static ControllerLogic controller;
	/** root to the sence of the game */
	private static  AnchorPane page;
	public static Stage primaryStage;
	
	//***************************************** Constructors ******************************************
	/**
	 * full C'tur
	 */
	public ViewLogic() {
	}
	
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	public static void getInstance(ControllerLogic instanceController) throws IOException {
                if(!exists)
                {
					exists = true;
					instance  = new ViewLogic();
					controller=instanceController;
					startview();
                }
               
	}
	
	@Override
	public   void  start(Stage primaryStage)
	{
		openLogin(primaryStage);
	}
	
	public static void openLogin(Stage primaryStage)
	{
		{
			try{
				    AnchorPane page  = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Login.fxml"));
				    Scene scene = new Scene(page);
			        primaryStage.setScene(scene);
			        primaryStage.setTitle("BlackJack Enjoy!");
			        primaryStage.getIcons().add(new Image("/view/photos/icon.png"));
			        setWindowSize(primaryStage, 950, 600);
			        ViewLogic.primaryStage=primaryStage;
			        primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void startview()
	{
		launch();
	}

	
	public static void startGame(Stage primaryStage) 	 
	{
		try{
			    page  = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Table.fxml"));
			    Scene scene = new Scene(page);
		        scene.getStylesheets().add("/view/TableCss.css");
		        primaryStage.setScene(scene);
		        primaryStage.setTitle("Hello "+controller.getNickname()+" Welcome to the BlackJack game!");
		        primaryStage.getIcons().add(new Image("/view/photos/icon.png"));
		        setWindowSize(primaryStage, 1000, 700);
		        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close() 	 
	{
            Platform.exit();
    }

	/**
	 * the method log in the user
	 * @param nickName
	 * @throws IOException
	 */
	public static void login(String nickName) throws IOException{
		ControllerLogic.login(nickName);
	}	
	
	//-----------------------------------Window method and reset games methods--------------------------------------------------
	
	public static void setWindowSize(Stage primaryStage,int width, int height)
	{
		// max
		primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        
        // min
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
	}
	
  	/**
		 * initialize player and dealer for new game
		 */
		public static void newGame()
		{
			controller.newGame();
		}
		
		/**
		 * initialize player and dealer for new round
		 */
		public static void newRound()
		{
			controller.newRound();
		}
		
		/**
		 * reset bet to zero
		 */
		public static void resetBet(){
			controller.resetBet();
		}
    
	//----------------------------------- cards methods -------------------------------------------------
 
	public static Card getCardFromDeck(User user)
	{
			return controller.getCard(user);
	}
	
	/**
	 * @return the value of all cards player have
	 */
	public static Integer playerValueCards() {
		return controller.playerValueCards();
	}
	 /**
	  * @throws PlayerEndOfGameException if value of player is over 21
	  */
	  public static void isOver21() throws PlayerEndOfGameException{
		   controller.isOver21();
	  }
	  
	  public static boolean isExactly21()
	  {
		  return controller.isExactly21();
	  }
		 /**
		 * @return true if Dealer can take one more card, else otherwise
		 */	
		public static Boolean isDealerNeedMoreCard(){
			return controller.isDealerNeedMoreCard();
		}
		
		/**
		 * The method return the second card of the Dealer
		*/
		public static Card getSecondCardOfDealer(){
			return controller.getSecondCardOfDealer();
		}
		  /**
		   * @throws PlayerEndOfGameException if value of player is exactly 21 by 2 cards
		   */
		  public static void isBlackJack() throws PlayerEndOfGameException{
			  controller.isBlackJack();
		  }
		 
		  /**
			* @return the value of all cards player have
			*/
		  public static Integer dealerValueCards() {
			  return controller.dealerValueCards();
		  }
	
     //----------------------------------- Chip's methods ----------------------------------------------
		
	
	public static int getChips()
	{
	  return controller.getChips();
	}
	
	
	public static Boolean setBets(int amount)
	{
	  return controller.setbets(amount);
	}
	
	public static Integer getBets() {
		return controller.getBets();
	}
	
   //***************************************** other Methods *****************************************
	public static AnchorPane getPage() {
		return page;
	}

	/**
	 * @throws WhoWinException with message of who win!?
	 */
	public static void checkWin() throws WhoWinException{
		controller.checkWin();
	}
	
	/**
	 * return number of wining in round of the table
	 * @return int
	 */
	public static Integer getPlayerWins()
	{
		return controller.getNumberOfWins();
	}
	
	/**
	 * return number of losses in round of the table
	 * @return int
	 */
	public static Integer getPlayerLosses()
	{
		return controller.getNumberOfLoses();
	}
	/**
	 * @return the dealer cards
	 */
	public static ArrayList<Card> getCards(){
		return controller.getCards();
	}


		
}
