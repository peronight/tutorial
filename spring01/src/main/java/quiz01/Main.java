package quiz01;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Scanner sc = new Scanner(System.in);
		System.out.println("첫번째 정수를 입력하세요");
		int a = Integer.parseInt(sc.nextLine());
		System.out.println("연산자를 입력하세요");
		String oper = sc.nextLine();
		System.out.println("두번째 정수를 입력하세요");
		int b = Integer.parseInt(sc.nextLine());
		Calculator calc = ctx.getBean("calc", Calculator.class);
		System.out.println(calc.calc(a, oper, b));
		sc.close();
		ctx.close();
	}
}
