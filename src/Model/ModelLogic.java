 package Model;

import java.awt.Window.Type;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

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
	//***************************************** Constructors ******************************************
	/**
	 * Full C'tor, for singleton support. 
	 */
	private ModelLogic() {
		dealer= new Dealer();
		player= new Player();
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
	 * @param user 1- to add card to  player. other numer to add to  dealer
	 * @return the choosen card
	 */
	public Card getCard(int user) 
	{
		Card card=dealer.getCard();
			if (user==1)
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
	/**
	 * 
	 * @param amount- the anount of bets player bet this game
	 */
	public void setbets(int amount)
	{
		player.setBets(amount);
	}
	
	//----------------------------------- remove methods ----------------------------------------------
		
	
	//----------------------------------- update methods ----------------------------------------------

    //***************************************** other Methods *****************************************
	
	

}
