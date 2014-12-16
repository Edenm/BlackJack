package Tests;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Exceptions.WhoWinException;
import Model.ModelLogic;
import Model.Player;
import Model.Dealer;


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
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void PlayerWin() throws Throwable {
		
		 expectedEx.expect(WhoWinException.class);
		 expectedEx.expectMessage("Player is Win!");
		
		 // create new game
		model.newGame();
		
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards20Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards18Value(), dealer);
		
		
		System.out.println("p1: "+player.getValue());
		// action
		model.checkWin();
	}
	

	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void DealerrWin() throws Throwable {
		
		 expectedEx.expect(WhoWinException.class);
		 expectedEx.expectMessage("Dealer is Win!");
		
		 // create new game
		model.newGame();
		
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards18Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards20Value() , dealer);
		System.out.println("p:"+player.getValue());
		
		// action
		model.checkWin();
	}
	

	/**
	 * check that the right exception is throwing
	 * @throws Throwable
	 */
	@Test
	public void NoBodyWin() throws Throwable {
		
		 expectedEx.expect(WhoWinException.class);
		 expectedEx.expectMessage("The game end! Nobody won");
		
		 // create new game
		model.newGame();
		
		// set the value of cards for the dealer and player
		TestsHelper.AddCardsToPlayer(TestsHelper.getCards18Value() , player);
		TestsHelper.AddCardsToDealer(TestsHelper.getCards18Value() , dealer);
		
		// action
		model.checkWin();
	}

}
