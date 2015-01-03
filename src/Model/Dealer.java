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
	/** the sum of all cards in dealer hand*/
	private Integer value;
    /**  deck cards  */
	 private ArrayList<Card> cards;
	 /***/
	 private ArrayList<Card> mycards;
	 /** place to take the next card*/
	 private int number=-1;
	 /** boolean variable check if player already have an ace card* true- if there isnt card ( first card), false- if player have ace in the cards*/
	 private boolean isFirstAce=true;
	 
////////////////////////////////////////////////////////////////// constructor	//////////////////////////////////////// 
	 /**
	  * full ctu'r
	  */
	 public Dealer()
	 {
		 intalizeDeckCards();
		 initializeDealer();
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
	 	
	/////////////////////////////////////////////////////getters and setters////////////////////////////////////////////
	 	/**
	 	 * @return value of cards
	 	 */
	 	protected Integer getValue() {
			return value;
		}
	 	
	 	/**
		 * Push the first card like stack return only one card!
		 * @return card from deck
		 */
		protected Card getCard()
		{	
			number++;
			return cards.get(number);
		}	
	 	
	//////////////////////////////////////////methods of this class////////////////////////////////////////

	/**
	 * the method is initialize the dealer for new game or new round
	 */
	 public void initializeDealer(){
		 shuffle();
		 mycards=new ArrayList<Card>();
		 value= new Integer(0);
		 isFirstAce=true;
	 }
	 	
	 	
	 /**
     *  shuffle the cards array list	
     */
	public void shuffle()
	{
		Collections.shuffle(cards);
		number=-1;
	}
	
	/**
	 *  initialize the cards deck, happened only once 
	 *  the picture here is initialize by string name, the names of the cards pic should be 1.jpg,2.jpg....52.jpg
	 */	
	private void intalizeDeckCards()
	{
		cards= new ArrayList<Card>();
		for(int i=1,j=1,realIndex=1 ;i<=Constants.numberOfSuitCard ;i++,j++,realIndex++)
		{
			// jack, queen,king are equals 10 and not 11,12,13
			if(j>=11)
				j=10;
			cards.add(new Card(realIndex,j, "black","photos/"+i+"-Club.png",Suits.Club));
			cards.add(new Card(realIndex,j, "red","photos/"+i+"-Heart.png",Suits.Heart));
			cards.add(new Card(realIndex,j, "black","photos/"+i+"-Spade.png",Suits.Spade));
			cards.add(new Card(realIndex,j, "red","photos/"+i+"-Diamond.png",Suits.Daimond));
		}
	}
	
	/**
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
		   mycards.add(card);
	   }

	   /**
	    * @return true if Dealer can take one more card, else otherwise
	    */
	   public boolean isDealerNeedMoreCard(){
			if (value<Constants.limitOfDealer)
				return true;
			return false;
	    }
	   
	   /**
	    * The method return the second card of the Dealer
	    */
	   public Card getSecondCardOfDealer(){
			return mycards.get(1);
	   }
	   
	    /**
	     * @return true if the dealer got a blackJack
	     */
		public boolean isBlackJack() {
			 if (value==21 && mycards.size()==2){
				   return true;
			   }
			 return false;
		 }
		/**
		 * @return the dealer cards
		 */
		public ArrayList<Card> getCards(){
			return mycards;
		}
}





