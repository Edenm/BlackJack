package Model;

import Utils.Suits;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Card {
   /**
    *  represent the value of each card(1-14- jack is 11, queen-12,king-13.... and so on)
    */
   private Integer value;
   
   private String color;
    
   private ImageView pic;
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
		this.pic= new ImageView(new Image(url));
	}
   
}
