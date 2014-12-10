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
		this.setColor(color);
		this.setSuit(suit);
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Suits getSuit() {
		return suit;
	}
	public void setSuit(Suits suit) {
		this.suit = suit;
	}
	
	///////////////////////////////////////////////Override method///////////////////////////////////////////////////////////
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card)
			return ((suit.equals(((Card)obj).suit) && value.equals(((Card)obj).value)));
		return false;
	}
	
}



