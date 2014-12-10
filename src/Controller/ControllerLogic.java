package Controller;

import Exceptions.PlayerEndOfGameException;
import Model.*;
import Utils.User;
import View.ViewLogic;


/**Controller Logic class*/
public final class ControllerLogic {
	//***************************************** Variables *********************************************
	/**Singleton instance of this class, loaded on the first execution of ControllerLogic.getInstance()*/
	private static ControllerLogic instance;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**ModelLogic reference pointer*/
	private static ModelLogic model; //assuming we've only one.
	//***************************************** Constructors ******************************************
	/**
	 * Full C'tor, for singleton support. 
	 */
	private ControllerLogic() {
		
	}
	
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 */
	public static ControllerLogic getInstance()  {
		try {
			if(! exists){
				exists = true;
				model = ModelLogic.getInstance();
				instance = new ControllerLogic();
				ViewLogic.getInstance(instance);// wait to create viewlogic
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: cannot initialize ModelLogic, please contact you administrator!");
        }
		return instance;
	}												
	
    //----------------------------------- Dealer methods -------------------------------------------------
	//////////// all the logic code of this method  is in modellogic class
	/**
	 * @param enum type of user
	 * @return the choosen card
	 * @throws PlayerEndOfGameException 
	 */
	public Card getCard(User user)
	{
		 return model.getCard(user);
	}

	 /**
	 * @return true if Dealer can take one more card, else otherwise
	 */	
	public Boolean isDealerNeedMoreCard(){
		return model.isDealerNeedMoreCard();
	}
	
	/**
	 * The method return the second card of the Dealer
	*/
	public Card getSecondCardOfDealer(){
		return model.getSecondCardOfDealer();
	}
	
	
	//----------------------------------- player methods ----------------------------------------------
	/**
	 * 
	 * @return the number of chips left to player
	 */
	public Integer getChips() {
		return model.getChips();
	}
	
	/**
	 * 
	 * @return the bets of this player
	 */
	public Integer getBets() {
		return model.getBets();
	}
	
	/**
	 * 
	 * @param amount- the anount of bets player bet this game
	 */
	public boolean setbets(int amount)
	{
		 return model.setbets(amount);
	}
	
	/**
	 * 	
	 * @return the value of all cards player have
	 */
	public Integer playerValueCards() {
		return model.playerValueCards();
	}
	
	 /**
	  * @throws PlayerEndOfGameException if value of player is over 21
	  */
	  public void isOver21() throws PlayerEndOfGameException{
		   model.isOver21();
	  }
	
	
	
    //***************************************** other Methods *****************************************
      
}
