package Model;

import Utils.Suits;



public class Card {
   /**
    *  represent the value of each card(1-14- jack is 11, queen-12,king-13.... and so on)
    */
   private Integer value;

   private String color;
    
   private String url;
   
   private Suits suit;
    
   /**
    * constructor 
    * @param value
    * @param color
    * @param pic
    */
	public Card(Integer value, String color,String url,Suits suit) {
		this.value = value;
		this.color = color;
		this.suit=suit;
		this.url=url;
	}
	/**
	 * call by model logic
	 * @return imageview - pic of this card to be use in the gui part
	 */
	//////////////////////////////////////////////////////// getters and setters//////////////////////////////////////////
	public String getPic()
	{
		return this.url;
	}
	   public Integer getValue() {
			return value;
		}
	   
	
}


