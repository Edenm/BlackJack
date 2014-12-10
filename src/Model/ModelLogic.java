package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


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
	/** static varibel save the number of games play in courrect round*/
	private static Integer numOfGame=0;
	//***************************************** Constructors ******************************************
    /**
     * create txt file for the tests
     */
       //File file = new File("example.txt");
       //BufferedWriter output = new BufferedWriter(new FileWriter(file));
    
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
	 */
	public Card getCard(User user) 
	{Card card=dealer.getCard();
	
		if (user.equals(User.Player)){
			player.addcard(card);
			/*
			try {
				output.newLine();
				output.write("the player get:"+card.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		else{
			dealer.addcard(card);
			/*
			try {
				output.newLine();
				output.write("the dealer get:"+card.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			}
			
			
		return card;
	}
	

	//----------------------------------- player methods ----------------------------------------------
	/**
	 * 
	 * @param amount- the anount of bets player bet this game
	 */
	public boolean setbets(int amount)
	{
		/*
		try {
			output.newLine();
			output.write("the player bet:"+amount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		 return player.setBets(amount);
		
	}
	/**
	 * 
	 * @return the number of chips left to player
	 */
	public Integer getChips() {
		/*
		try {
			output.newLine();
			output.write("the player have "+player.getChips()+" chips");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		*/
		return player.getChips();
	}
	/**
	 * 	
	 * @return
	 */
	public Integer getBets() {
		return player.getBets();
	}
	/**
	 * 	
	 * @return the value of all cards player have
	 */
	public Integer playerValueCards() {
		/*
		try {
			output.newLine();
			output.write("the player have"+player.getValue());
			output.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		return player.getValue();
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