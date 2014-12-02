package Model;

public class Player {
	////////////////////////////////////////////////////////////varibels/////////////////////////////////////////////////////////////
	// name of player- intalize when new player open app
	private String nickname;
	// the sum of all cardes in player hand
	private Integer value;
	// ask yair or eden what is this attribute
	private Integer points;
	// the sum of money player bets in this game;
	private Integer bets;
	
	
	
///////////////////////////////////////////////////////////////// constructor to player ///////////////////////////////////////
	// need only name all the other data intalize every game with new data

public   Player(){
		
}

public   Player(String nickname ){
	this.nickname=nickname;
}
//////////////////////////////////////////////////////geters and seters/////////////////////////////////////////////////////

public String getNickname() {
	return nickname;
}
public Integer getValue() {
	return value;
}
public void setValue(Integer value) {
	this.value =value;
}
public Integer getPoints() {
	return points;
}
public void setPoints(Integer points) {
	this.points = points;
}
public Integer getBets() {
	return bets;
}
public void setBets(Integer bets) {
	if (bets!=null&&bets>0)
		this.bets = bets;
}


}
