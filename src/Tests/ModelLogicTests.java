package Tests;



import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
		 model.login("test");
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
		// action
		try{
		model.checkWin();
		}
		catch (Exception e) {
			 assertEquals(e.getMessage(), "/photos/Player_win.png");
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
		model.setbets(betsGame);
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards18Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards20Value() , dealer);
		
		// action
		try{
			model.checkWin();
			}
			catch (Exception e) {
				 assertEquals(e.getMessage(), "/photos/Dealer_win.png");
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
				 assertEquals(e.getMessage(), "/photos/Its_a_Tie.png");
				 int playerChipAfterWiningGame= player.getChips();
				 assertEquals(chips, playerChipAfterWiningGame); 
			}
	}

}
