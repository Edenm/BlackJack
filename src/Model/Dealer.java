package Model;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Utils.Suits;

import com.sun.javafx.scene.control.skin.Utils;


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
	// the pic here is intalize by string name, the names of the cards pic should be 1.jpg,2.jpg....52.jpg
	private void intalizeDeckCards()
	{
		for(int i=0;i<14;i++)
		{
			cards.add(new Card(i, "black",""+i+".jpg",Suits.Club));
			cards.add(new Card(i, "red",""+i+1+".jpg",Suits.Heart));
			cards.add(new Card(i, "black",""+i+2+".jpg",Suits.Spade));
			cards.add(new Card(i, "red",""+i+3+".jpg",Suits.Daimond));
			
		}
	}
		
		
	
	
	
}





