package beta1;

import java.util.Scanner;

public class Test {

public static void main(String[] args){
		
	Scanner input = new Scanner(System.in);
	System.out.println("Enter some words:");
	String words = input.next();
	CountChar.countLetters(words);
	input.close();
	}
}
