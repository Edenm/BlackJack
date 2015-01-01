package Model;

import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.PlayerEndOfGameException;
import Exceptions.WhoWinException;
import Utils.User;

/**Model Logic class*/
public final class ModelLogic {
	//***************************************** Variables *********************************************

	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static ModelLogic instance;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**Dealer reference pointer*/
	private Dealer dealer;
	/**Player reference pointer*/
	private Player player;
	/** static variable save the number of games play in correct round*/
	private static Integer numOfRounds=0;
	//***************************************** Constructors ******************************************
    
	/**
	 * Full C'tor, for singleton support. 
	 * @throws IOException 
	 */
	private ModelLogic() throws IOException {
		
		dealer= Dealer.getInstance();
		player= Player.getInstance("moshe");
	}
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	public static ModelLogic getInstance() throws IOException, FileNotFoundException, ClassNotFoundException {
		if(! exists)
		{
				exists = true;
				instance  = new ModelLogic();
				return instance;
		}
		return instance;
	}													

    //----------------------------------- Dealer methods -------------------------------------------------
	
	/**
	 * 
	 * @param enum type of user
	 * @return the choosen card
	 * @throws PlayerEndOfGameException 
	 */
	public Card getCard(User user)
	{
		Card card=dealer.getCard();
	
		if (user.equals(User.Player))
			player.addcard(card);
		else
			dealer.addcard(card);
			
		return card;
	}
	
	/**
	 * @return true if Dealer can take one more card, else otherwise
	 */
	public Boolean isDealerNeedMoreCard(){
		return dealer.isDealerNeedMoreCard();
	}
	
	/**
	 * The method return the second card of the Dealer
	*/
	public Card getSecondCardOfDealer(){
		return dealer.getSecondCardOfDealer();
	}

	//----------------------------------- player methods ----------------------------------------------
	/**
	 * @param amount- the anount of bets player bet this game
	 */
	public boolean setbets(int amount)
	{
		 return player.setBets(amount);
	}
	/**
	 * @return the number of chips left to player
	 */
	public Integer getChips() {
		return player.getChips();
	}
	/**
	 * @return bets
	 */
	public Integer getBets() {
		return player.getBets();
	}
	/**
	 * @return the value of all cards player have
	 */
	public Integer playerValueCards() {
		return player.getValue();
	}
	
	 /**
	  * @throws PlayerEndOfGameException if value of player is over 21
	  */
	  public void isOver21() throws PlayerEndOfGameException{
		   player.isOver21();
	  }
	  
	  /**
	   * @throws PlayerEndOfGameException if value of player is exactly 21 by 2 cards
	   */
	  public void isBlackJack() throws PlayerEndOfGameException{
		  if (player.isBlackJack() && !dealer.isBlackJack()){
			  player.playerBlackJack();
			  throw new PlayerEndOfGameException("/photos/blackJack.gif");
		  }
	  }
	  
	  /**
	   * @return true if the player have 21 in his value cards
	   */
	  public boolean isExactly21(){
		  return player.isExactly21();
	  }
	  
	  /**
	   * @return number of wins of player
	   */
	  public int getNumberOfWins(){
		  return player.getNumberOfWins();
	  }
	  
	  /**
	   * @return number of loses of player
	   */
	  public int getNumberOfLoses(){
		  return player.getNumberOfLoses();
	  }

    //***************************************** other Methods *****************************************
	/**
	 * initialize player and dealer for new game
	 * do ++ to number of games
	 */
	public void newGame()
	{
		dealer.initializeDealer();
		player.newGamePlayer("moshe");
		numOfRounds=0;
	}
	
	/**
	 * initialize player and dealer for new round
	 * initialize zero to number of games
	 */
	public void newRound()
	{
		dealer.initializeDealer();
		player.newRoundPlayer();
		numOfRounds++;
	}
	
	/**
	 * reset bet to zero
	 */
	public void resetBet(){
		player.resetBet();
	}
	
	/**
	 * @throws WhoWinException with message of who win!?
	 */
	public void checkWin() throws WhoWinException{
		if (dealer.getValue()>21){
			player.playerWin();
			throw new WhoWinException("/photos/player_win.png");
		}
		if (dealer.getValue()>player.getValue()){
			player.playerLose();
			throw new WhoWinException("/photos/Dealer_win.png");
		}
		else if (dealer.getValue()<player.getValue()){
			player.playerWin();
			throw new WhoWinException("/photos/Player_win.png");
		}
		else{
			throw new WhoWinException("/photos/Its_a_Tie.png");
		}
	}
}