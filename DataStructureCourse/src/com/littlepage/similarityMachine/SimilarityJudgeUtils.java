package com.littlepage.similarityMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Copyright (c) 2018 Shanghai University of Electric Power
 * 
 * The utils of judge similarity
 * 
 * @author Steve Yu
 * @param <K> the key of HashTable
 * @param <V> the value of HashTable
 */

public class SimilarityJudgeUtils {
	
	/**
	 * the phrase of 50 Java identified phrase
	 */
	public static final String[] IDENTIFIED_PHRASE= {
		"abstract","assert","boolean","break","byte",
		"case","catch","char","class","const",
		"continue","default","do","double","else",
		"enum","extends","final","finally","float",
		"for","goto","if","implements","import",
		"instanceof","int","interface","long","native",
		"new","package","private","protected","public",
		"return","short","static","strictfp","super",
		"switch","synchronized","throw","throws","transient",
		"try","void","volatile","while","void"
	};
	
	/**
	 * get the file
	 * @param fileName The relative location url.
	 * @return the String from the file
	 */
	public static String getStringFromFile(String fileName) {
		BufferedReader fr=null;
		String strFile="";
		try {
			fr=new BufferedReader(new FileReader(fileName));
			String temp;
			while((temp=fr.readLine())!=null){
				strFile+=temp;
			}
			if(fr!=null) fr.close();
		} catch (IOException e) {
			System.err.println("找不到该文件");
			System.exit(-1);
		}
		return strFile;
	}
	
	/**
	 * use HashTable count the keywords 
	 * @param str the coding string number
	 * @return the keywords count array
	 */
	public static HashTable<String, Integer> countKeywords(String[] str) {
		HashTable<String, Integer> hashtable=new HashTable<>();
		for (String string : IDENTIFIED_PHRASE) {
			hashtable.push(string, 0);
		}
		for (String string : str) {
			if(hashtable.contains(string)) {
				int newValue=hashtable.getValue(string);
				newValue++;
				hashtable.push(string, newValue);
			}
		}
		return hashtable;
	}
	
	/**
	 * get the value of similarity
	 * @param fileName1 the first file name
	 * @param fileName2 the second file name
	 * @return
	 */
	public static double detectionTwoFile(String fileName1,String fileName2) {
		String str1=getStringFromFile(fileName1);
		String str2=getStringFromFile(fileName2);
		String[] strArr1=str1.split(" ");
		String[] strArr2=str2.split(" ");
		HashTable<String, Integer> hashArr1=countKeywords(strArr1);
		HashTable<String, Integer> hashArr2=countKeywords(strArr2);
		double analysis=0;
		for (String string : IDENTIFIED_PHRASE) {
			double temp=(hashArr1.getValue(string)-hashArr2.getValue(string));
			temp*=temp;
			analysis+=temp;
		}
		analysis=Math.sqrt(analysis);
		return analysis;
	}
}
