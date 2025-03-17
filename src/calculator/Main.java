package calculator;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("> ");
			String input = sc.nextLine().replaceAll(" ", "").toLowerCase();
			if (input.equals("q"))
				break;
			
			try {
				System.out.println(new Calculator().calculate(input));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		sc.close();
	}
}
