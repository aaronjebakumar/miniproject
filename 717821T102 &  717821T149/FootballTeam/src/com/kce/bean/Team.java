package com.kce.bean;

public class Team {
	private int playerNumber;
	private String playerName;
	private String clubName;
	private int playerAge;
	private int playerGoals;
public Team() {
	
}
public Team(String playerName,int playerNumber, int playerAge, int playerGoals,  String clubName) {
	super();
	this.playerNumber = playerNumber;
	this.playerName = playerName;
	this.clubName = clubName;
	this.playerAge = playerAge;
	this.playerGoals = playerGoals;
}
public int getPlayerNumber() {
	return playerNumber;
}
public void setPlayerNumber(int playerNumber) {
	this.playerNumber = playerNumber;
}
public String getPlayerName() {
	return playerName;
}
public void setPlayerName(String playerName) {
	this.playerName = playerName;
}
public String getClubName() {
	return clubName;
}
public void setClubName(String clubName) {
	this.clubName = clubName;
}
public int getPlayerAge() {
	return playerAge;
}
public void setPlayerAge(int playerAge) {
	this.playerAge = playerAge;
}
public int getPlayerGoals() {
	return playerGoals;
}
public void setPlayerGoals(int playerGoals) {
	this.playerGoals = playerGoals;
}

}
