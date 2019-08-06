package spring01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx1 = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter greet1 = ctx1.getBean("greeter", Greeter.class);
		System.out.println(greet1.greet("윤준호"));
		ctx1.close();
	}
}
