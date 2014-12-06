package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import Utils.Suits;


public class Dealer {
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static Dealer instance ;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/** the sum of all cardes in player hand*/
	private Integer value;
    /**  deck cards  */
	 private ArrayList<Card> cards;
	 /***/
	 private ArrayList<Card> mycards;
	 /** place to take the next card*/
	 private int number=-1;
	 
////////////////////////////////////////////////////////////////// constructor	//////////////////////////////////////// 
	 public Dealer()
	 {
		 cards= new ArrayList<Card>();
		 mycards=new ArrayList<Card>();
		 intalizeDeckCards();
		 shuffle();
		 value= new Integer(0);
	 }
	 
	 /**
	  * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	  * @return reference to this class's only instance, or null if reference was already returned (singleton).
	  * @throws GeneralException 
	  */
	 	public static Dealer getInstance() throws IOException {
	 	            if(!exists)
	 	            {
	 					exists = true;
	 					instance  = new Dealer();
	 					return instance;
	 	            }
	 	            return instance;
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
		if(number>=51)
		{
			number=-1;
			shuffle();
		}
		number++;
		return cards.get(number);
		
	}
	// intalize the cards deck, happend only once 
	// the pic here is intalize by string name, the names of the cards pic should be 1.jpg,2.jpg....52.jpg
	private void intalizeDeckCards()
	{
		for(int i=1;i<14;i++)
		{
			cards.add(new Card(i, "black","photos/"+i+"-Club.png",Suits.Club));
			cards.add(new Card(i, "red","photos/"+i+"-Heart.png",Suits.Heart));
			cards.add(new Card(i, "black","photos/"+i+"-Spade.png",Suits.Spade));
			cards.add(new Card(i, "red","photos/"+i+"-Diamond.png",Suits.Daimond));
			
		}
	
	}
		
	/////////////////////////////////////////////////////	 getters and setters///////////////////////////////////
	
	   public void addcard(Card card) {
				this.value +=card.getValue();
				mycards.add(card);
			}
	   
	
}





