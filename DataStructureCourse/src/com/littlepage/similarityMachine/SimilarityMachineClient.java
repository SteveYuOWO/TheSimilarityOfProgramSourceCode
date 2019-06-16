package com.littlepage.similarityMachine;

import java.util.Scanner;

public class SimilarityMachineClient {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一份源代码的绝对地址:");
		String fileName1=sc.nextLine();
		System.out.println("请输入第二份源代码的绝对地址:");
		String fileName2=sc.nextLine();
		double analysis=SimilarityJudgeUtils.detectionTwoFile(fileName1, fileName2);
		System.out.printf("分析结果:\n两份源代码的相似度为:   %.2f",analysis);
		sc.close();
		//D:\eclipse sts workspace\DataStructureCourse\BinaryNode.java
		//D:\eclipse sts workspace\DataStructureCourse\Person.java
	}
}
