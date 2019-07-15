package com.example.downdown_first_app_1;

public class person_data implements Comparable<person_data>{
	
	private int score;
	private int eaten_coin;
	
	public person_data(int score, int eaten_coin){
		this.score = score;
		this.eaten_coin = eaten_coin;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getEaten_coin(){
		return eaten_coin;
	}
	
	public void setScore(int new_score){
		this.score = new_score;
	}
	
	public void setEaten_coin(int new_eaten_coin){
		this.eaten_coin = new_eaten_coin;
	}
	
	@Override
	public int compareTo(person_data p){
		int compare_score=((person_data)p).getScore();
		return compare_score-this.score;
		
	}
}
