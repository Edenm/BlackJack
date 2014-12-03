package Controller;

import java.util.*;

import Model.*;
import View.ViewLogic;


/**Controller Logic class*/
public final class ControllerLogic {// implements I_ControllerLogic {  <<<--- for 2nd HW !!
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
	 * @param user 1- to add card to  player. other numer to add to  dealer
	 * @return the choosen card
	 */
	public Card getCard(int user) 
	{
		 return model.getCard(user);
	}
	/**
	 * 		
	 * @return the number of chips left to player
	 */
		
	public int getPlayerChiaps()
	{
		return model.getPlayerChiaps();
	}
	/**
	 * 
	 * @param amount- the anount of bets player bet this game
	 */
	public void setbets(int amount)
	{
		model.setbets(amount);
	}
	
	//----------------------------------- remove methods ----------------------------------------------
	
	//----------------------------------- connect methods ---------------------------------------------
	
	//----------------------------------- disconnect methods ------------------------------------------
	
	//----------------------------------- update methods ----------------------------------------------
	
	
    //***************************************** other Methods *****************************************
      
}
