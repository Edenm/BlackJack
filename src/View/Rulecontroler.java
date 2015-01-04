package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class Rulecontroler implements Initializable {
	@FXML
	TextArea txtrules;

	public void initialize(URL location, ResourceBundle resources) {
		ruleIntalize();
		
	}
	private void ruleIntalize()
	{
		
		
		txtrules.setText("The object of Blackjack is for the total of your cards to be closer to 21 than the dealer’s cards, without exceeding 21. In Blackjack, aces count as either 1 or 11, face cards as 10, and number cards at their face value"+"\n"+
							"In the beginning of a new game you must choose your bet:"+"\n"+
							"•	white chips = $1"+"\n"+
							"•	red chips = $5"+"\n"+
							"•	blue chips= $50"+"\n"+
							"•	green chips = $25"+"\n"+
							"•	black chips = $100"+"\n"+
							"if you receive an ace and a ten-value card as your first two cards, you have Blackjack and win 2 times your bet (if your bet was 10, you receive 30). "+"\n"+
							"If the total value of your cards is closer to 21 than the dealer’s, you win your bet amount (if your bet was 10, you receive 20). "+"\n"+
							"If the total of your cards is more than 21, you “bust” and automatically lose your bet. "+"\n"+
							"If you and the dealer have the same card total (17 to 21) neither of you win and your bet is returned to you in a “push”. Blackjack beats a score of 21."+"\n"+
							"The buttons:"+"\n"+
							"Deal -shuffles the deck and deals two cards to both the dealer and the player"+"\n"+
							"Hit - adds an extra card to player's hand"+"\n"+
							"Stand - draw no more cards and end your turn."+"\n"+
							"New Game - reset the score"+"\n"+
							"New Round - 	continue the game with the total score ");
		
	}

}
