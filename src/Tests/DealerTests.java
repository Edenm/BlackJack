package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.Card;
import Model.Dealer;
import Utils.Suits;

public class DealerTests {

	// this test check if there duplicate card in the  deck.
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
		
		System.out.println(cards.size());		
		
	}
	
	@Test
	public void TestsDealerAddCardFirstAceAndSecondAce()
	{
	 Dealer dealer= new Dealer();
	 Card card= new Card(1,1, "Red", "", Suits.Club);
	 
	 dealer.addcard(card);
	 
	 int dealerCardValue= TestsHelper.getDealerCardsValue(dealer);
	 assertEquals(11, dealerCardValue);
	 
	 Card card2= new Card(1,1, "Black", "", Suits.Spade);
	 
	 dealer.addcard(card2);
	 
	 dealerCardValue= TestsHelper.getDealerCardsValue(dealer);
	 assertEquals(12, dealerCardValue);
	 
	}
	
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