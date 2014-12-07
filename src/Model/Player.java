package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
	////////////////////////////////////////////////////////////Variables/////////////////////////////////////////////////////////////
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static Player instance ;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/** name of player- intalize when new player open app */
	private String nickname;
	/** the sum of all cardes in player hand*/
	private Integer value;
	/**  number of chips pkayer have (money)*/
	private Integer chips;
	/** the sum of money player bets in this game;*/
	private Integer bets;
	/** my cards*/
	private ArrayList<Card> mycards;
	 /** boolean varible check if player already have an ace card* true- if there isnt card ( first card), false- if player have ace in the cards*/
	 private boolean isFirstAce=true;
	
	
	
///////////////////////////////////////////////////////////////// constructor to player ///////////////////////////////////////
/**
 * c'tor
 *  need only name all the other data intalize every game with new data
 * @param nickname
 */
	public   Player(String nickname ){
		nickname=new String();
		this.mycards=new ArrayList<Card>();
		value=new Integer(0);
		bets=new Integer(0);
		chips=new Integer(0);
		// player atart with 500 chips every round;
		this.chips=500;
		this.nickname=nickname;
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
//////////////////////////////////////////////////////geters and seters/////////////////////////////////////////////////////

	public String getNickname() {
		return nickname;
	}
	public Integer getValue() {
		return value;
	}
	
	public Integer getChips() {
		return chips;
	}
	public void setPoints(Integer points) {
		this.chips = points;
	}
	public Integer getBets() {
		return bets;
	}


	//////////////////////////////////////////////////////////////// methods for player///////////////////////////

	public boolean setBets(Integer bet) {
		
		if (bets!=null&&chips-(bets+bet)>=0)
		{ 
			this.bets  += bet;
			return true;
		}
		return false;
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
				   this.value+=11;// if first ace, += 11 to th value
				   isFirstAce=false;
			   }
			   else
				   this.value+=1;// if its the second card ace add only 1
		   else
			   this.value +=card.getValue();
				mycards.add(card);
			}


}
