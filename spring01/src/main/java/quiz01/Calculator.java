package quiz01;

public class Calculator {
	public String calc(int a, String oper, int b) {
		String str = "";
		int tmp = 0;
		int result = 1;
		switch(oper) {
			case "+" :
				tmp = a + b;
				break;
			case "-" :
				tmp = a - b;
				break;
			case "*" :
				tmp = a * b;
				break;
			case "%" :
				tmp = a / b;
				break;
			default :
				str = "연산자를 잘못 입력하셨습니다. +,-,*,% 중 하나를 입력하세요.";
				result = 0;
		}
		if(result != 0) {
			str = a + oper + b + " = " + tmp;
		}
		return str;
	}
}
