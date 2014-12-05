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
	
	
	
///////////////////////////////////////////////////////////////// constructor to player ///////////////////////////////////////
/**
 * c'tor
 *  need only name all the other data intalize every game with new data
 * @param nickname
 */
	public   Player(String nickname ){
		this.nickname=nickname;
		// player atart with 500 chips every round;
		this.chips=500;
		this.mycards=new ArrayList<Card>();
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
	public void addcard(Card card) {
		//this.value +=card.getValue();
	//	mycards.add(card);
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
	public boolean setBets(Integer bets) {
		if (bets!=null&&chips-bets>0)
		{
			this.bets  = bets;
			return true;
		}
		return false;
	}


}
