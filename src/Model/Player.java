package Model;

import java.util.ArrayList;

public class Player {
	////////////////////////////////////////////////////////////varibels/////////////////////////////////////////////////////////////
	// name of player- intalize when new player open app
	private String nickname;
	// the sum of all cardes in player hand
	private Integer value;
	// number of chips pkayer have (money)
	private Integer chips;
	// the sum of money player bets in this game;
	private Integer bets;
	// my cards
	 private ArrayList<Card> mycards;
	
	
	
///////////////////////////////////////////////////////////////// constructor to player ///////////////////////////////////////
	// need only name all the other data intalize every game with new data

public   Player(){
		
}

public   Player(String nickname ){
	this.nickname=nickname;
	// player atart with 500 chips every round;
	this.chips=500;
	this.mycards=new ArrayList<Card>();
}
//////////////////////////////////////////////////////geters and seters/////////////////////////////////////////////////////

public String getNickname() {
	return nickname;
}
public Integer getValue() {
	return value;
}
public void addcard(Card card) {
	//this.value +=card.getValue();
//	mycards.add(card);
}
public Integer getChips() {
	return chips;
}
public void setPoints(Integer points) {
	this.chips = points;
}
public Integer getBets() {
	return bets;
}
public void setBets(Integer bets) {
	if (bets!=null&&bets>0)
		this.bets = bets;
}


}
