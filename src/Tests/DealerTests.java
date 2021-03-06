package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.Card;
import Model.Dealer;
import Utils.Suits;

public class DealerTests {

	/**
	 * this test check if there duplicate card in the  deck.s
	 * @throws Throwable
	 */
	@Test
	public void TestsDealerDuplicateCardInDeck() throws Throwable {

		// arrange - getting the instance of the dealer from the model logic instance 
		Dealer dealer= new Dealer();
		
		// create a array list of the card to check if there is a duplicate card in the deck
		ArrayList<Card> cardsForCheck=  new ArrayList<Card>();
		 @SuppressWarnings("unchecked")
		ArrayList<Card> cards= (ArrayList<Card>) TestsHelper.getInstanceField(dealer, "cards");

		 // act
		 for (Card card : cards) {
			if(cardsForCheck.contains(card))
				fail("there is duplicate card in the deck\n The card: "+ card.getColor()+" "+card.getSuit()+" "+card.getValue());
			else
				cardsForCheck.add(card);
		}
		
		
	}
	
	/**
	 * test if the first ace will be value 11 and second ace will be value  1
	 */
	@Test
	public void TestsDealerAddCardFirstAceAndSecondAce()
	{
	 Dealer dealer= new Dealer();
	 Card card= new Card(6,6, "Red", "", Suits.Club);
	 
	 dealer.addcard(card);
	 
	 int dealerCardValue= TestsHelper.getDealerCardsValue(dealer);
	 assertEquals(6, dealerCardValue);
	 
	 Card card2= new Card(1,1, "Black", "", Suits.Spade);
	 
	 dealer.addcard(card2);
	 
	 dealerCardValue= TestsHelper.getDealerCardsValue(dealer);
	 assertEquals(17, dealerCardValue);
	 
     dealer.addcard(card2);
	 
	 dealerCardValue= TestsHelper.getDealerCardsValue(dealer);
	 assertEquals(18, dealerCardValue);
	 
	}
	
	/**
	 * check if the dealer need more card should return true in the first time and false on the second time
	 */
	@Test
	public void TestsDealerisDealerNeedMoreCard()
	{
	 Dealer dealer= new Dealer();
	 
	 // create cards and adding them to the dealer
	 Card[] cardsForCheck= {new Card(10,10, "Black", "", Suits.Club),new Card(6,6, "Black", "", Suits.Spade)};
	 TestsHelper.AddCardsToDealer(cardsForCheck, dealer);
	 
	 //Aseert
	 assertTrue(dealer.isDealerNeedMoreCard());
	 
	 dealer.addcard(new Card(2,2, "Red", "", Suits.Club));
	 
	 //Aseert
	 assertFalse(dealer.isDealerNeedMoreCard());
	 
	 
	}
	
	/**
	 *  check if the dealer need more card should return false on the first time
	 */
	@Test
	public void TestsDealerNotNeedMoreCard()
	{
	 Dealer dealer= new Dealer();
	 
	 // create cards and adding them to the dealer
	 Card[] cardsForCheck= {new Card(10,10, "Black", "", Suits.Club),new Card(10,10, "Black", "", Suits.Spade)};
	 TestsHelper.AddCardsToDealer(cardsForCheck, dealer);
	 
	 // assert
	 assertFalse(dealer.isDealerNeedMoreCard());
	}
	
	/**
	 * check if the method actual return the second card of the dealer
	 */
	@Test
	public void TestsDealerGetsecondCard()
	{
	 Dealer dealer= new Dealer();
	 
	 // create cards and adding them to the dealer
	 Card[] cardsForCheck= TestsHelper.getCardsUnder21();
	 TestsHelper.AddCardsToDealer(cardsForCheck, dealer);
	 
	 Card card= dealer.getSecondCardOfDealer();
	 
	 // assert
	 assertEquals(card, cardsForCheck[1]);
	}
	 
}