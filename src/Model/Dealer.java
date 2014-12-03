package Model;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.image.Image;
import Utils.Suits;

import com.sun.javafx.scene.control.skin.Utils;


public class Dealer {
	// the sum of all cardes in player hand
		private Integer value;
	// deck cards 
	 private ArrayList<Card> cards;
	 //
	 private ArrayList<Card> mycards;
	 // place to take the next card
	 private int number=0;
	 
////////////////////////////////////////////////////////////////// constructor	//////////////////////////////////////// 
	 public Dealer()
	 {
		 cards= new ArrayList<Card>();
		 mycards=new ArrayList<Card>();
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
	protected Card getCard()
	{
		return cards.get(++number);
	}
	// intalize the cards deck, happend only once 
	// the pic here is intalize by string name, the names of the cards pic should be 1.jpg,2.jpg....52.jpg
	private void intalizeDeckCards()
	{
		for(int i=1;i<2;i++)
		{
			cards.add(new Card(i, "black","photos/"+i+".png",Suits.Club));
			cards.add(new Card(i, "red","photos/"+i+1+".png",Suits.Heart));
			cards.add(new Card(i, "black","photos/"+i+2+".png",Suits.Spade));
			cards.add(new Card(i, "red","photos/"+i+3+".png",Suits.Daimond));
			
		}
	}
		
	/////////////////////////////////////////////////////	 getters and setters///////////////////////////////////
	
	   public void addcard(Card card) {
				this.value +=card.getValue();
				mycards.add(card);
			}
	   
	
}





