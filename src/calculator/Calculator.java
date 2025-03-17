package calculator;

import java.util.*;

public class Calculator {
	public MyNum calculate(String input) throws Exception {
		Exception inputError = new Exception("잘못된 수식입니다.");

		int i = 0;
		boolean flag = false;
		List<MyNum> nums = new ArrayList<>();
		MyNum num = new MyNum();
		List<Integer> dig1 = new LinkedList<Integer>(), dig2 = new LinkedList<Integer>();

		// nums 만들기
		while (i < input.length()) {
			char now = input.charAt(i);
			if (flag) {
				if (now >= '0' && now <= '9') {
					if(num.isDouble()) {
						dig2.add(now - '0');
					} else {
						dig1.add(now - '0');
					}
				} else {
					switch (now) {
					case '+':
					case '-':
					case '*':
					case '/':
					case '%':
						num.setValue(create(dig1, dig2));
						num.setOper(now);
						nums.add(num);
						num = new MyNum();
						dig1.clear();
						dig2.clear();
						flag = false;
						break;
					default:
						throw inputError;
					}
				}
				i++;
			} else {
				if (now >= '0' && now <= '9') {
					dig1.add(now - '0');
					flag = true;
					i++;
				} else if (now == '(') {
					int endB = Bracket.getBracket(input, i);
					num = calculate(input.substring(i + 1, endB));
					i = endB + 1;
				} else if (now == '.') {
					if (num.isDouble())
						throw inputError;
					else
						num.setDouble(true);
				} else if (String.valueOf(now).isBlank())
					continue;
				else
					throw inputError;
			}
		}
		num.setValue(create(dig1, dig2));
		nums.add(num);

		//nums 계산
		i = 0;
		while (i < nums.size()) {
			switch (nums.get(i).getOper()) {
			case '*':
			case '/':
			case '%':
				nums.get(i).calc(nums.remove(i + 1));
			}
			i++;
		}

		while (nums.size() > 1) {
			nums.get(0).calc(nums.remove(1));
		}
		return nums.get(0);
	}
	
	private double create(List<Integer> list1, List<Integer> list2) {
		int above = 0;
		double under = 0;
		for(int dig : list2) {
			under *= 10;
			under += dig;
		}
		while(under >= 1) {
			under /= 10;
		}
		
		for(int dig : list1) {
			above *= 10;
			above += dig;
		}
		
		return above + under;
	}
}
