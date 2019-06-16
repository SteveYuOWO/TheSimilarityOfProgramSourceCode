package com.littlepage.similarityMachine;


/**
 * Copyright (c) 2018 Shanghai University of Electric Power
 * 
 * The is a HashTable edit by Steve Yu, for only fit the Data Structure's HashTable
 * 
 * The HashTable initial capacity is 50.
 * 
 * @author Steve Yu
 * @param <K> the key of HashTable
 * @param <V> the value of HashTable
 */

public class HashTable<K,V> {
	
	private static final int INIT_CAPACITY=1000;
	
	private SeqList<Object>[] key;
	private SeqList<Object>[] value;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		key=new SeqList[INIT_CAPACITY];
		value=new SeqList[INIT_CAPACITY];
	}
	
	/**
	 * push an element into HashTable. 
	 * @param key the keyword
	 * @param value the value
	 */
	public void push(K key,V value) {
		int hash=Math.abs(key.hashCode()%1000);
		if(this.key[hash]==null) this.key[hash]=new SeqList<>();//the initialize key
		if(this.value[hash]==null) this.value[hash]=new SeqList<>();//the initialize value 
		if(this.key[hash].contains(key)) {//if contains
			this.value[hash].set(this.key[hash].indexOf(key), value);
		}
		//not contains
		this.key[hash].insert(key);
		this.value[hash].insert(value);
	}

	/**
	 * get the value by key
	 * @param key the keyword
	 * @return value if HashTable contains value, null if HashTable not contains anything. 
	 */
	@SuppressWarnings("unchecked")
	public V getValue(K key) {
		int hash=Math.abs(key.hashCode()%1000);
		if(this.key[hash]==null) return null;
		else {
			int index=this.key[hash].indexOf(key);
			return (V) this.value[hash].get(index);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(K key) {
		int hash=Math.abs(key.hashCode()%1000);
		if(this.key[hash]==null) return false;
		else {
			if(this.key[hash].contains(key)) return true;
			else return false;
		}
	}
	
	/**
	 * output all the key without order
	 */
	@Override
	public String toString() {
		StringBuilder str=new StringBuilder();
		str.append("[");
		for(int i=0;i<key.length;i++) {
			if(key[i]!=null) {
				for(int j=0;j<key[i].getSize();j++) {
					str.append(key[i].get(j));
				}
				str.append(",");
			}
		}
		str.replace(str.lastIndexOf(","), str.lastIndexOf(",")+1, "]");
		return str.toString();
	}
}
