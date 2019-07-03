package com.littlepage.similarityMachine;

/**
 * Copyright (c) 2018 Shanghai University of Electric Power
 * 
 * The is a SeqList edit by Steve Yu, for only fit the Data Structure's SeqList
 * 
 * The SeqList initial capacity is 10. And the capacity is 1.5 times as large as initial capacity
 * 
 * @author Steve Yu
 * 
 */

public class SeqList<T>{
	
	private final int INITIAL_CAPACITY=10;
	
	private Object[] element;
	private int size;
	private int capacity;
	
	
	public SeqList() {
		size=0;
		capacity=INITIAL_CAPACITY;
		element=new Object[capacity];
	}
	
	public SeqList(int length){
		size=length;
		capacity=(int) (length*1.5+1);
		element=new Object[capacity];
	}
	
	public SeqList(T[] array){
		this();
		for(int i=0;i<array.length;i++){
			insert(array[i]);
		}
	}
	/**
	 * get the count of element
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * get the capacity
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * toString method
	 */
	public String toString() {
		StringBuilder str=new StringBuilder();
		str.append("[");
		for (int i=0;i<size;i++) {
			if(element[i]==null) str.append("null"+",");
			else str.append(element[i].toString()+",");
		}
		str.replace(str.lastIndexOf(","),str.lastIndexOf(",")+1, "]");
		return str.toString();
	}

	/**
	 * judge the SeqList is or not empty
	 * @return true if it is empty. 
	 */
	public boolean isEmpty(){
		return size==0;
	}

	/**
	 * insert a value on index position
	 * @param index the index of SeqList
	 * @param value the value on index position
	 * @return the index of inserted  value
	 */
	public int insert(int index,T value){
		if(value==null) throw new RuntimeException("x==null");
		if(index<0) index=0;
		if(index>size) index=size;
		//enlarge capacity
		size++;
		if(size==capacity) capacity*=2;
		//copy
		Object[] temp=new Object[capacity];
		for(int j=0;j<index;j++){
			if(element[j]!=null) temp[j]=element[j];
		}
		temp[index]=value;
		for(int j=index+1;j<size;j++){
			if(element[j]!=null) temp[j]=element[j-1];
		}
		element=temp;
		return index;
	}

	/**
	 * insert a value on the rear of SeqList
	 * @param value the insert value
	 * @return the index of inserted value
	 */
	public int insert(T value){
		return insert(size,value);
	}

	/**
	 * remove the element on index of the SeqList
	 * remove element if exist, else remove the edge of the SeqList
	 * @param index
	 * @return the value of removed element
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index){
		if(index<0) index=0;
		if(index>=size) index=size-1;
		Object retu=element[index];
		for(int j=index;j<size;j++){
			if(element[j+1]!=null) element[j]=element[j+1];
			else element[j]=null;
		}
		size--;
		return (T)retu;
	}

	/**
	 * remove the value element
	 * @param value the removed element
	 * @return the value of removed element
	 */
	public T remove(T value){
		for(int i=0;i<size;i++){
			if(value.equals(element[i])) {
				remove(i);
				return value;
			}
		}
		return null;
	}
	
	/**
	 * remove all the element and reset the capacity to INITIAL_CAPACITY
	 */
	public void removeAll(){
		size=0;
		capacity=INITIAL_CAPACITY;
	}
	
	/**
	 * set the value on index of SeqList
	 * @param index the index
	 * @param value the set value
	 */
	public void set(int index,T value){
		if(index>=size) index=size-1;
		if(index<0) index=0;
		element[index]=value;
	}

	/**
	 * get the value on index of SeqList
	 * @param index the index of element
	 * @return the value of element
	 */
	@SuppressWarnings("unchecked")
	public T get(int index){
		return (T)element[index];
	}

	/**
	 * judge the element if or not exist in SeqList
	 * @param obj searched the element
	 * @return true if contains and false if not contains
	 */
	public boolean contains(Object obj) {
		for(int i=0;i<size;i++) {
			if(obj.equals(element[i])) return true;
		}
		return false;
	}

	/**
	 * find the index of element
	 * @param obj the value of the element
	 * @return index if exists and -1 if not exists
	 */
	public int indexOf(Object obj) {
		for(int i=0;i<size;i++) {
			if(obj.equals(element[i])) return i;
		}
		return -1;
	}

}
