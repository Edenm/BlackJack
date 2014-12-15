package Model;

import Utils.Suits;



public class Card {
   /**
    *  represent the value of each card(1-14- jack is 11, queen-12,king-13.... and so on)
    */
   private Integer realValue;
	
   private Integer value;

   private String color;
    
   private String url;
   
   private Suits suit;
    
   /**
    * constructor 
    * @param realValue
    * @param value
    * @param color
    * @param pic
    */
	public Card(Integer realValue ,Integer value, String color,String url,Suits suit) {
		this.realValue = realValue;
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
	public Integer getRealValue() {
		return realValue;
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
		result = prime * result
				+ ((realValue == null) ? 0 : realValue.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card)
			return ((this.suit.equals(((Card)obj).getSuit()) && this.realValue.equals(((Card)obj).getRealValue())));
		return false;
	}
	
}



