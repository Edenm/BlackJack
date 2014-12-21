package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.PlayerEndOfGameException;
import Model.Card;
import Model.Player;
import Utils.*;

public class PlayerTests {

	@Test
	public void PlayerTestsSetBets_HappyFlow() {
		// arrange player has 500 chips in the start of the game 
		Player player=new Player("test");
		
		// action
		player.setBets(400);
		
		// assert
		int testBets= player.getBets();
		assertEquals(400, testBets);
		
	
		
		
	}
	
	@Test
	public void PlayerTestsSetBets_BetsTooHigh() {
		// arrange player has 500 chips in the start of the game 
		Player player=new Player("test");
		
		// action--> 501 > 500
		Boolean res=player.setBets(501);
		
		//Assert
		assertFalse(res);
		
		int bets=player.getBets();
		assertEquals("the bets should NOT change", 0, bets);	
	}
	
	
	@Test
	public void PlayerTestsNewGame() {
		
		String name =new String("TestNo2");
		// arrange player has 500 chips in the start of the game 
		Player player=new Player("test");
		
		Boolean res=player.setBets(100);
		
		//Assert to make sure that player doesn't have default values
		assertTrue(res);
		
		// action
		player.newGamePlayer(name);
		
		//assert chips should be Constants limitOfChips
		int testGets=player.getChips();
		assertEquals(Constants.limitOfChips$, testGets);
		
		// bets should be 0
		testGets= player.getBets();
		assertEquals(0, testGets);
		
		// no value should be yet before dealing
		testGets= player.getValue();
		assertEquals(0, testGets);
	}
	
	@Test
	public void TestsPlayerAddCardFirstAceAndSecondAce()
	{
	// arrange 
	 Player player= new Player("test");
	 Card card= new Card(1,1, "Red", "", Suits.Club);
	 
	 //action
	 player.addcard(card);
	 
	 int playerCardValue= player.getValue();
	 
	 // the first ace should cpint as 11;
	 assertEquals(11, playerCardValue);
	 
	 // arrange the second ace
	 Card card2= new Card(1,1, "Black", "", Suits.Spade);
	  
	 //action
	 player.addcard(card2);
	 
	 // assert the second ace should count as 1; 
	 playerCardValue= player.getValue();
	 assertEquals(12, playerCardValue);
	 
	  
	 //action
	 player.addcard(card2);
	 
	 // assert the second ace should count as 1; 
	 playerCardValue= player.getValue();
	 assertEquals(13, playerCardValue);
	 
	}
	
	@Test(expected= PlayerEndOfGameException.class) 
	public void TestsPlayerOver21() throws Exception
	{
	// arrange 
	 Player player= new Player("test");
	 
	 Card[] cardsOfPlayer= TestsHelper.getCardsOver21();
	 TestsHelper.AddCardsToPlayer(cardsOfPlayer, player);
	 
	 player.isOver21();
	 
	}
	
	@Test
	public void TestsPlayerunder21() throws Exception
	{
	// arrange 
	 Player player= new Player("test");
	 
	 Card[] cardsOfPlayer= TestsHelper.getCardsUnder21();
	 TestsHelper.AddCardsToPlayer(cardsOfPlayer, player);
	 
	 player.isOver21();
	 
	}
	
	@Test
	public void TestsPlayerBlackJack() throws Exception
	{
	// arrange 
	 int bets=200;
	 Player player= new Player("test");
	 player.setBets(bets);
	 // adding cards to player
	 Card[] cardsOfPlayer= TestsHelper.getCardsForBlackJackTwoCards();
	 TestsHelper.AddCardsToPlayer(cardsOfPlayer, player);
	 
	 // action
	 try{
	 player.isBlackJack();
	 }catch(PlayerEndOfGameException e)
	 {
	 // assert catch the right exception + check update of the total chips
	 int totalChips=player.getChips();
	 int expectTotalChips= bets*2+ Constants.limitOfChips$;
	 assertEquals(totalChips, expectTotalChips);
	 }
	 
	}
}
