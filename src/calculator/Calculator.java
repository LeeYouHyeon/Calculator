package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
	public MyNum calculate(String input) throws Exception {
		Exception inputError = new Exception("잘못된 수식입니다.");

		int i = 0;
		boolean flag = false;
		List<MyNum> nums = new ArrayList<>();
		MyNum num = new MyNum();

		// nums 만들기
		while (i < input.length()) {
			char now = input.charAt(i);
			if (flag) {
				if (now >= '0' && now <= '9') {
					num.append(now - '0');
				} else {
					switch (now) {
					case '+':
					case '-':
					case '*':
					case '/':
					case '%':
						num.setOper(now);
						nums.add(num);
						num = new MyNum();
						flag = false;
						break;
					default:
						throw inputError;
					}

				}
				i++;
			} else {
				if (now >= '0' && now <= '9') {
					num.append(now - '0');
					flag = true;
					i++;
				} else if (now == '(') {
					int endB = Bracket.getBracket(input, i);
					num = calculate(input.substring(i + 1, endB));
					i = endB + 1;
				} else
					throw inputError;
			}
		}
		nums.add(num);
		
		//nums 계산
		i = 0;
		while(i < nums.size()) {
			switch(nums.get(i).getOper()) {
			case '*':
			case '/':
			case '%':
				nums.get(i).calc(nums.remove(i + 1));
			}
			i++;
		}
		
		while(nums.size() > 1) {
			nums.get(0).calc(nums.remove(1));
		}
		return nums.get(0);
	}
}
