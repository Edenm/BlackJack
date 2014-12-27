package Model;

import java.io.IOException;
import java.util.ArrayList;
import Utils.Constants;
import Exceptions.PlayerEndOfGameException;

public class Player {
////////////////////////////////////////////////////////////Variables/////////////////////////////////////////////////////////////
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static Player instance ;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/** name of player- initialize when new player open app */
	private String nickname;
	/** the sum of all cards in player hand*/
	private Integer value;
	/**  number of chips player have (money)*/
	private Integer chips;
	/** the sum of money player bets in this game;*/
	private Integer bets;
	/** my cards*/
	private ArrayList<Card> mycards;
	/** boolean variable check if player already have an ace card* true- if there isn't card ( first card), false- if player have ace in the cards*/
	private boolean isFirstAce=true;
	/** int variable that represent number wins of player*/
	private int numberOfWins=0;
	/** int variable that represent number loses of player*/
	private int numberOfLoses=0;
///////////////////////////////////////////////////////////////// constructor to player ///////////////////////////////////////
	/**
	 * c'tor
	 *  need only name all the other data initialize every game with new data
	 * @param nickname
	 */
	public   Player(String nickname ){
		newGamePlayer(nickname);
	}

	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	public static Player getInstance(String nick) throws IOException {
	            if(!exists)
	            {
					exists = true;
					instance  = new Player(nick);
					return instance;
	            }
	            return instance;
	}
//////////////////////////////////////////////////////Getters and setters/////////////////////////////////////////////////////
	/**
	 * @return Nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @return Value of cards
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @return Chips
	 */
	public Integer getChips() {
		return chips;
	}
	/**
	 * set Chips
	 * @param points
	 */
	public void setPoints(Integer points) {
		this.chips = points;
	}
	
	/**
	 * @return Bets
	 */
	public Integer getBets() {
		return bets;
	}
	
	/**
	 * @return NumberOfWins
	 */
	public int getNumberOfWins() {
		return numberOfWins;
	}
	
	/**
	 * @return NumberOfLoses
	 */
	public int getNumberOfLoses() {
		return numberOfLoses;
	}

	/**
	 * @param bet
	 * @return true if can set more bets, false otherwise
	 */
	public boolean setBets(Integer bet) {
		
		if (bets!=null && chips-(bets+bet)>=0)
		{ 
			this.bets+= bet;
			return true;
		}
		return false;
	}

//////////////////////////////////// methods for player//////////////////////////////////////////////////////////
	/**
	 * the method initialize the player for a new game
	 * @param nickname
	 */
	public void newGamePlayer(String nickname){
		nickname=new String();
		newRoundPlayer();
		chips=new Integer(0);
		// player start with 500 chips every round;
		this.chips=Constants.limitOfChips$;
		this.nickname=nickname;
	}
	
	/**
	 * the method initialize the player for a new round
	 */
	public void newRoundPlayer(){
		this.mycards=new ArrayList<Card>();
		value=new Integer(0);
		bets=new Integer(0);
	}
	
	/**
	 * reset bet to zero
	 */
	public void resetBet(){
		this.bets=0;
	}
	
	/**
	 * add card to player value and array of card, check if the player have ace or its first card of ace and do += to the value for 11 or 1
	 * @param card
	 */
	 public void addcard(Card card) {
		   // if the card is ace, check if its the first ace
		   if(card.getValue()==1)
			   if(isFirstAce)
			   {
				   this.value+=11;// if first ace, += 11 to the value
				   isFirstAce=false;
			   }
			   else
				   this.value+=1;// if its the second card ace add only 1
		   else
			   this.value +=card.getValue();
				mycards.add(card);
	 }
	   
		 /**
		  * @throws PlayerEndOfGameException if value of player is over 21
		 */
		 public void isOver21() throws PlayerEndOfGameException{
			   if (value>21){
				   playerLose();
				   throw new PlayerEndOfGameException("/photos/busted.png");
			   }
		 }
	 
	    /**
	    * @return true if the player got a blackJack
	    */
		public boolean isBlackJack() {
			 if (value==21 && mycards.size()==2){
				   return true;
			   }
			 return false;
		 }
		
		/**
		* @return true if the player have 21 in his value cards
		*/
	     public boolean isExactly21(){
			  if (value==21)
				  return true;
			  return false;
		  }
	 
///////////////////////////////////Calculate chips every round/////////////////////////////////////////////////////
	 /**
	  * update the chips for black jack case
	  */
	 public void playerBlackJack(){
		 this.chips+=this.bets*2;
		 numberOfWins++;
	 }
	 
	 /**
	  * update the chips for lose case
	  */
	 public void playerLose(){
		 this.chips-=this.bets;
		 numberOfLoses++;
	 }
	 
	 /**
	  * update the chips for win case
	  */
	 public void playerWin(){
		this.chips+=this.bets;
		numberOfWins++;
	 }
	 
	 /**
	  * update the chips for nobody win case
	  */
	// public void nobodyWin(){
		 
	// }
}
