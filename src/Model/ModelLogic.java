package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import Utils.User;

/**Model Logic class*/
public final class ModelLogic implements Serializable{
	//***************************************** Variables *********************************************
	/** ModelLogic's serialVersionUID*/
	private static final long serialVersionUID = 6141521813577450598L;
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static ModelLogic instance;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**Dealer reference pointer*/
	private Dealer dealer;
	/**Player reference pointer*/
	private Player player;
	/** static varibel save the number of games play in courrect round*/
	private static Integer numOfGame=0;
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
		return null;
	}													

    //----------------------------------- Dealer methods -------------------------------------------------
	
	/**
	 * 
	 * @param enum type of user
	 * @return the choosen card
	 */
	public Card getCard(User user) 
	{Card card=dealer.getCard();
	
		if (user.equals(User.Player))
			player.addcard(card);
		else
			dealer.addcard(card);
			
			
		return card;
	}
	/**
	 * 		
	 * @return the number of chips left to player
	 */
	public int getPlayerChiaps()
	{
		return player.getChips();
	}

	//----------------------------------- remove methods ----------------------------------------------
	/**
	 * 
	 * @param amount- the anount of bets player bet this game
	 */
	public boolean setbets(int amount)
	{
		 return player.setBets(amount);
	}
	/**
	 * 
	 * @return
	 */
	public Integer getChips() {
		return player.getChips();
	}
	
	/**
	 * 	
	 * @return
	 */
	public Integer getBets() {
		return player.getBets();
	}
	//----------------------------------- update methods ----------------------------------------------

    //***************************************** other Methods *****************************************
	/**
	 * do ++ to number of games
	 * todo: finished method iteration 2
	 */
	public void newGame()
	{
		numOfGame++;
	}
	/**
	 * intalize zero to number of games
	 * todo: finished method iteration 2
	 */
	public void newRound()
	{
		numOfGame=0;
	}
}