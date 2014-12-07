package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Utils.Constants;
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
	 /** boolean varible check if player already have an ace card* true- if there isnt card ( first card), false- if player have ace in the cards*/
	 private boolean isFirstAce=true;
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
   
	 /**
     *  shuffle the cards array list	
     */
	public void shuffle()
	{
		Collections.shuffle(cards);
	}
	
	/**
	 *  pushe the first card like stack return only one card!
	 * @return
	 */
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
	
	/**
	 *  intalize the cards deck, happend only once 
	 *  the pic here is intalize by string name, the names of the cards pic should be 1.jpg,2.jpg....52.jpg
	 */	
	private void intalizeDeckCards()
	{


		for(int i=1,j=1;i<=Constants.numberOfSuitCard ;i++,j++)
		{


			// jack, queen,king are equals 10 and not 11,12,13
			if(j>=11)
				j=10;
			
			cards.add(new Card(j, "black","photos/"+i+"-Club.png",Suits.Club));
			cards.add(new Card(j, "red","photos/"+i+"-Heart.png",Suits.Heart));
			cards.add(new Card(j, "black","photos/"+i+"-Spade.png",Suits.Spade));
			cards.add(new Card(j, "red","photos/"+i+"-Diamond.png",Suits.Daimond));
		}

		
	}
		
	/////////////////////////////////////////////////////	 getters and setters///////////////////////////////////
	/**
<<<<<<< HEAD
	 * add card to player value and array of card, check if the player have ace or its first card of ace and do += to the value for 11 or 1
	 * @param card
	 */
	
	   public void addcard(Card card) {
		   // if the card is ace, check if its the first ace
		   if(card.getValue()==1)
			   if(isFirstAce)
			   {
				   this.value+=11;// if first ace, += 11 to th value
				   isFirstAce=false;
			   }
			   else
				   this.value+=1;// if its the second card ace add only 1
		   else
			   this.value +=card.getValue();
	   }


	   
	
}





