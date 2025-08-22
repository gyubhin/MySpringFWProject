package myspring.di.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.junit.jupiter.api.BeforeEach;

//static import
import static org.junit.jupiter.api.Assertions.*;

public class HelloJUnit5Test {
	ApplicationContext context;
	
	@BeforeEach
	void createContainer() {
		//1. Container 객체 생성
		context = new GenericXmlApplicationContext("classpath:spring-beans.xml");
	}

	@Test
	void helloBean() {
		//2. Container가 생성한 Hello 스프링빈을 요청하기
		Hello helloById = (Hello)context.getBean("hello");
		Hello helloByType = context.getBean("hello", Hello.class);
		
		//주소를 비교해서 Singleton 인지 확인하기
		System.out.println(helloById == helloByType);
		
		//Assertions.assertSame() 사용 -> testcase 성공(싱글톤)
		assertSame(helloById, helloByType);
		//notsame으로 물어보면 같기때문에 testcase 실패
		//assertNotSame(helloById, helloByType);
		
		//<property name="name" value="스프링"/> 설정 테스트
		//값 비교하기
		assertEquals("Hello 스프링", helloByType.sayHello());
		
		//<property name="printer" ref="stringPrinter"/>설정 테스트
		helloByType.print();
		
		//StringPrinter 스프링빈을 요청하기
		Printer printer = context.getBean("stringPrinter", Printer.class);
		assertEquals("Hello 스프링", printer.toString());
	}
}
