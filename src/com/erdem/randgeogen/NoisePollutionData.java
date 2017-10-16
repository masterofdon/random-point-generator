package com.erdem.randgeogen;

import java.util.Date;

public class NoisePollutionData extends LocalizedData{
	
	private final int MIN_LEVEL = 0;
	private final int MAX_LEVEL = 5;
	
	private int level;
	
	public NoisePollutionData() {
		this.level = 0;
	}
	
	public NoisePollutionData(int level) {
		super();
		setLevel(level);
	}
	
	public NoisePollutionData(int level, Date date, double lat, double lng) {
		super(date,lat,lng);
		setLevel(level);
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if(level >= MIN_LEVEL && level <= MAX_LEVEL)
			this.level = level;
		else
			throw new IndexOutOfBoundsException();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%.6f",getLat()) + "," + String.format("%.6f",getLng()) + "," + level);
		return sb.toString();
	}
	
	public String toStringWitoutLevel(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%.6f",getLat()) + "," + String.format("%.6f",getLng()));
		return sb.toString();
	}
	
	public String toJSONStringWithoutLevel(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(String.format("%.6f",getLng()) + "," + String.format("%.6f",getLat()));
		sb.append("]");
		return sb.toString();
	}
	
	public String toJSONStringWithoutLevelV2(){
		StringBuilder sb = new StringBuilder();
		sb.append("{ position: [");
		sb.append(String.format("%.6f",getLng()) + "," + String.format("%.6f",getLat()));
		sb.append("]}");
		return sb.toString();
	}
	

}
