package Model;

import javafx.scene.image.ImageView;



public class Card {
   /**
    *  represent the value of each card(1-14- jack is 11, queen-12,king-13.... and so on)
    */
   private Integer value;
   
   private String color;
    
   private ImageView pic;
    
   /**
    * constructor 
    * @param value
    * @param color
    * @param pic
    */
	public Card(Integer value, String color,ImageView pic) {
		this.value = value;
		this.color = color;
		this.pic=pic;
	}
   
}
