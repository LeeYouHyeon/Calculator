package calculator;

public class Bracket {
	public static boolean isValid(String input) {
		int bracket = 0;
		for (char c : input.toCharArray()) {
			switch (c) {
			case '(':
				bracket++;
				break;
			case ')':
				if (--bracket < 0)
					return false;
				break;
			default:
				;
			}
		}

		return bracket == 0;
	}

	// �Է�: ��ȣ�� �����ϴ� string
	// ���: �� ��ȣ ������ index
	// ��) "((()) + () )*... => *�� index
	public static int getBracket(String input, int start) {
		assert (input.charAt(start) == '(');
		
		int bracket = 0;
		for (int i = start; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case '(':
				bracket++;
				break;
			case ')':
				if (--bracket == 0)
					return i;
			default:
			}
		}
		return -1;
	}
}
