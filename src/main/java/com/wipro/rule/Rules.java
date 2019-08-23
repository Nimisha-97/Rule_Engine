package com.wipro.rule;

public class Rules {
	
	private String name;
	private String toolName;
	private float threshold;
	private String mName;
	private String operator;
	private int reward;
	
	public Rules() {
		
	}
	public Rules(String name, String toolName, float threshold, String mName, String operator,int reward) {
		super();
		this.name = name;
		this.toolName = toolName;
		this.threshold = threshold;
		this.mName = mName;
		this.operator = operator;
		this.reward = reward;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String gettoolName() {
		return toolName;
	}
	public void settoolName(String toolName) {
		this.toolName = toolName;
	}
	public float getThreshold() {
		return threshold;
	}
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	

}
