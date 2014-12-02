package Model;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Dealer {
	// deck cards 
	 private ArrayList<Card> cards;
	 // place to take the next card
	 private int number=0;
////////////////////////////////////////////////////////////////// constructor	 
	 public Dealer()
	 {
		 intalizeDeckCards();
		 shuffle();
	 }
	/////////////////////////////////////////////////////////////methods of this class/////////////////////////////////
// shuffle the cards array list	
	public void shuffle()
	{
		Collections.shuffle(cards);
	}
	// pushe the first card like stack return only one card!
	public Card getCard()
	{
		return cards.get(++number);
	}
	// intalize the cards deck, happend only once 
	private void intalizeDeckCards()
	{
		for(int i=0;i<14;i++)
		{
			cards.add(new Card(i, "black",null));/// need to add suit enum not finish!
			cards.add(new Card(i, "red",null));
			cards.add(new Card(i, "black",null));
			cards.add(new Card(i, "red",null));
			
		}
	}
		
		
	
	
	
}





