package calculator;

public class MyNum {
	private double value;
	private char oper;
	private boolean isDouble;

	public MyNum() {
	}

	public MyNum(long value, char oper) {
		setValue(value);
		setOper(oper);
	}

	public MyNum(double value, char oper) {
		setValue(value);
		setOper(oper);
		this.isDouble = true;
	}
	
	public String toString() {
		return isDouble ? String.valueOf(value) : String.valueOf((int)value);
	}

	public void calc(MyNum num) throws Exception {
		Exception zeroDivision = new Exception("0으로 나눌 수 없습니다.");

		switch (oper) {
		case '+':
			value += num.value;
			break;
		case '-':
			value -= num.value;
			break;
		case '*':
			value *= num.value;
			break;
		case '/':
			if (num.value == 0)
				throw zeroDivision;
			value /= num.value;
			break;
		case '%':
			if (num.value == 0)
				throw zeroDivision;
			value %= num.value;
			break;
		default:
			throw new Exception("잘못된 수식입니다.");
		}
		
		oper = num.oper;
		if (num.isDouble)
			isDouble = true;
	}

	public void append(int num) {
		value *= 10;
		value += num;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public char getOper() {
		return oper;
	}

	public void setOper(char oper) {
		this.oper = oper;
	}

	public boolean isDouble() {
		return isDouble;
	}
	public void setDouble(boolean isDouble) {
		this.isDouble = isDouble;
	}
}
