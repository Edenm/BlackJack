package Tests;



import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import Exceptions.WhoWinException;
import Model.ModelLogic;
import Model.Player;
import Model.Dealer;

import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModelLogicTests {

	ModelLogic model;
	
	Player player;
	
	Dealer dealer;
	
	/**
	 *  create the game model+player+dealer
	 * @throws Throwable
	 */
	@Before public void setUp() throws Throwable
	{
		 model= ModelLogic.getInstance();
		 player= TestsHelper.getPlayer(model);
		 dealer = TestsHelper.getDelaer(model);
	}
	
	
	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void PlayerWin() throws Throwable {
		
		 // create new game
		model.newGame();
		int chips= player.getChips();
		int betsGame=2;
		model.setbets(betsGame);
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards20Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards18Value(), dealer);
		
		
		System.out.println("p1: "+player.getValue());
		// action
		try{
		model.checkWin();
		}
		catch (Exception e) {
			 assertEquals(e.getMessage(), "Player has Won!");
			 int playerChipAfterWiningGame= player.getChips();
			 assertEquals(chips+betsGame, playerChipAfterWiningGame);
			 
		}
	
	}
	

	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void DealerrWin() throws Throwable {
		
		
	     int betsGame=2;
	    
		 // create new game
		model.newGame();
		 int chips= player.getChips();
		System.out.println("ddd:"+chips);
		model.setbets(betsGame);
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards18Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards20Value() , dealer);
		
		// action
		try{
			model.checkWin();
			}
			catch (Exception e) {
				 assertEquals(e.getMessage(), "Dealer has Won!");
				 int playerChipAfterWiningGame= player.getChips();
				 assertEquals(chips-betsGame, playerChipAfterWiningGame);
				 
			}
	}
	

	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void NoBodyWin() throws Throwable {
		
		
	     int betsGame=2;
	     
		 // create new game
		model.newGame();
		 int chips= player.getChips();
		 model.setbets(betsGame);
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards18Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards18Value() , dealer);
		
		// action
		try{
			model.checkWin();
			}
			catch (Exception e) {
				 assertEquals(e.getMessage(), "The game end! Nobody won");
				 int playerChipAfterWiningGame= player.getChips();
				 assertEquals(chips, playerChipAfterWiningGame); 
			}
	}

}
