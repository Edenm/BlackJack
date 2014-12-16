package Tests;

import java.lang.reflect.Field;

import Model.Card;
import Model.Player;
import Model.Dealer;
import Utils.Suits;
import Model.ModelLogic;

public final class TestsHelper {
		
	public static Object getInstanceField(Object instance, String fieldName) throws Throwable {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

	//----------------------Create cards-----------------------------------------------------//
	public static Card[] getCardsOver21()
	{
		Card[] cards={
				new Card(1, "Red", "", Suits.Club),
				new Card(8, "Red", "", Suits.Club),
				new Card(9, "Red", "", Suits.Club),
				
		};
		return cards;
	}
	
	
	public static Card[] getCardsUnder21()
	{
		Card[] cards={
				new Card(2, "Red", "", Suits.Club),
				new Card(2, "Red", "", Suits.Club),
				
		};
		return cards;
	}
	
	public static Card[] getCards20Value()
	{
		Card[] cards={
				new Card(10, "Red", "", Suits.Club),
				new Card(10, "Red", "", Suits.Daimond),
				
		};
		return cards;
	}
	
	public static Card[] getCards18Value()
	{
		Card[] cards={
				new Card(9, "Red", "", Suits.Club),
				new Card(9, "Red", "", Suits.Daimond),
				
		};
		return cards;
	}
	
	public static Card[] getCardsForBlackJackTwoCards()
	{
		Card[] cards={
				new Card(1, "Red", "", Suits.Club),
				new Card(10, "Red", "", Suits.Club),
				
		};
		return cards;
	}
	//---------------------------------------------------------------------------------------//
	//------------------ Player Help Method ------------------------------------------------------------//

	public static Player getPlayer(ModelLogic instance) throws Throwable {
		 Field field=instance.getClass().getDeclaredField("player");
		 field.setAccessible(true);
	     return (Player)field.get(instance);
       
   }
	
	public static void AddCardsToPlayer(Card[] cards, Player player)
	{
		for (Card card : cards) {
			player.addcard(card);
		}
	}
	
	//------------------ End Player Help Method ------------------------------------------------------------//

	
	//------------------ Dealer Help Method ------------------------------------------------------------//
		
		public static Dealer getDelaer(ModelLogic instance) throws Throwable {
			 Field field=instance.getClass().getDeclaredField("dealer");
			 field.setAccessible(true);
		     return (Dealer)field.get(instance);
	        
	    }
		
		
		public static Integer getDealerCardsValue(Dealer dealer)
		{
			try {
				return (Integer)getInstanceField(dealer, "value");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
		}
		
		public static void AddCardsToDealer(Card[] cards, Dealer dealer)
		{
			for (Card card : cards) {
				dealer.addcard(card);
			}
		}

   //------------------ End Dealer Help Method ------------------------------------------------------------//

}
