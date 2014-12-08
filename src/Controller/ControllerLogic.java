package Controller;

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
	/**ViewLogic reference pointer*/
	private static ViewLogic view; //assuming we've only one.
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
	 		view = ViewLogic.getInstance(instance);// wait to create viewlogic
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
	 * 
	 * @param enum type of user
	 * @return the choosen card
	 */
	public Card getCard(User user) 
	{
		 return model.getCard(user);
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
	
	//----------------------------------- connect methods ---------------------------------------------
	
	//----------------------------------- disconnect methods ------------------------------------------
	
	//----------------------------------- update methods ----------------------------------------------
	
	
    //***************************************** other Methods *****************************************
      
}
