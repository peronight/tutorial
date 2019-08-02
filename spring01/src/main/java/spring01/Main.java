package spring01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx1 = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		GenericXmlApplicationContext ctx2 = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter greet1 = ctx1.getBean("greeter", Greeter.class);
		Greeter greet1_1 = ctx1.getBean("greeter", Greeter.class);
		Greeter greet2 = ctx2.getBean("greeter", Greeter.class);
		Greeter greet2_1 = ctx2.getBean("greeter", Greeter.class);
		System.out.println(greet1.greet("윤준호"));
		System.out.println(greet1 == greet2);
		System.out.println(greet1 == greet1_1);
		ctx1.close();
	}
}
