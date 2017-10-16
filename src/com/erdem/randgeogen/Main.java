package com.erdem.randgeogen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {

	public static List<LatLng> filterArray;
	
	public static void main(String[] args) {
		
		filterArray = loadFilterPoints();
		generateNoisePollutionData();
		
		//generateO2LevelData();
	}
	
	private static void generateNoisePollutionData() {
		FileOutputStream noisePollutionFile;
		try {
			noisePollutionFile = new FileOutputStream("istanbul_noise_pollution.json");
			PrintWriter noisePollutionWriter = new PrintWriter(noisePollutionFile);
			Random rand = new Random();
			//noisePollutionWriter.println("lng,lat");
			noisePollutionWriter.println("[");
			int len = 5000000;
			Random kaderRandomer = new Random();
			for(int i=0;i < len;i++){
				boolean doer = false;
				double lat = rand.nextDouble() + 40.8d;
				while(lat > 41.5842){
					rand.setSeed(rand.nextLong());
					lat = rand.nextDouble() + 40.8d;
				}
				double lng = rand.nextDouble()*3 + 27.99d;
				while(lng > 29.7026){
					rand.setSeed(rand.nextLong());
					lng = rand.nextDouble()*3 + 27.99d;
				}
				rand.setSeed(rand.nextLong());
				int closeLevel = 5;
				ArrayList<Double> distList = new ArrayList<Double>();
				for(LatLng item : filterArray){
					distList.add(haversine(lat,lng,item.lat,item.lng));
					
				}
				distList.sort(new Comparator<Double>() {

					@Override
					public int compare(Double o1, Double o2) {
						if(o1 < o2){
							return -1;
						}else if(o1 > o2){
							return 1;
						}
						return 0;
					}
				});
				double shortestDist= distList.get(0);
				double afterShortestDist = distList.get(1); 
				if(shortestDist < 3.0d || shortestDist + afterShortestDist < 5.0d){
					closeLevel = 1;
				}else if(shortestDist < 5.0d || shortestDist + afterShortestDist < 7.0d){
					closeLevel = 2;
				}else if(shortestDist < 8.0d || shortestDist + afterShortestDist < 10.0d){
					closeLevel = 3;
				}else if(shortestDist < 8.0d || shortestDist + afterShortestDist < 11.0d){
					closeLevel = 4;
				}
				switch(closeLevel){
					case 1:
						if(kaderRandomer.nextInt(10) > 1){
							doer = true;
						}
						break;
					case 2:
						if(kaderRandomer.nextInt(10) > 6){
							doer = true;
						}
						break;
					case 3:
						if(kaderRandomer.nextInt(10) > 7){
							doer = true;
						}
						break;
					case 4:
						if(kaderRandomer.nextInt(10) > 8){
							doer = true;
						}
						break;
					case 5:
						if(kaderRandomer.nextInt(10) > 9){
							doer = true;
						}
				}
				if(doer){
					kaderRandomer.setSeed(kaderRandomer.nextLong());
					int level = rand.nextInt(5) + 1;
					NoisePollutionData data = new NoisePollutionData(level,new Date(),lat,lng);
					noisePollutionWriter.print(data.toJSONStringWithoutLevel());
					if(i != len - 1){
						noisePollutionWriter.print(",");
					}
					noisePollutionWriter.println();
				}
			}
			noisePollutionWriter.println("]");
			noisePollutionWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateO2LevelData() {
		FileOutputStream o2LevelFile;
		try {
			o2LevelFile = new FileOutputStream("istanbul_o2_level.txt");
			PrintWriter o2LevelWriter = new PrintWriter(o2LevelFile);
			Random rand = new Random();
			o2LevelWriter.println("lng,lat");
			for(int i=0;i < 250000;i++){
				double lat = rand.nextDouble() + 40.5d;
				rand.setSeed(rand.nextLong());
				int level = rand.nextInt(5) + 1;
				double lng = rand.nextDouble() + 29.0d;
				o2LevelWriter.printf("%.6f,%.6f\n",lng,lat);
			}
			o2LevelWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static double haversine(double lat1, double lon1, double lat2, double lon2) {
		double R = 6372.8; // In kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
	
	public static List<LatLng> loadFilterPoints(){
		List<LatLng> array = new ArrayList<LatLng>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("istanbul_filter_points.txt")));
			String line;
			while((line =reader.readLine()) != null){
				String[] a = line.split(",");
				array.add(new LatLng(Double.valueOf(a[0]),Double.valueOf(a[1])));
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(array.size());
		return array;
		
	}

}
