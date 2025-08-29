package myspring.di.xml.annot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//<bean id="helloBean" class="myspring.di.annot.HelloBean" />
@Component("helloBean")
public class HelloBean {
	@Value("어노테이션") //<property name="name" value="어노테이션"/>
	String name;
	
	//<property name="printer" ref="stringPrinter">
	@Autowired
	@Qualifier("stringPrinterBean")
	PrinterBean printer;
	List<String> names;

	public HelloBean() {
		System.out.println(this.getClass().getName()+" 생성자가 호출됨.");
	}

	public HelloBean(String name, PrinterBean printer) {
		System.out.println(this.getClass().getName()+"Overloaded 생성자가 호출됨.");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

	public void setNames(List<String> list) {
		this.names = list;
	}
	
	//만약 메서드 이름이 setFirstName이라면... (spring-beans.xml 확인)
//	public void setName(String name) {
//		System.out.println(this.getClass().getName()+" setName() 호출됨."+name);
//		this.name = name;
//	}
//
//	public void setPrinter(PrinterBean printer) {
//		System.out.println(this.getClass().getName()+" getPrinter() 호출됨. "
//				+printer.getClass().getName());
//		this.printer = printer;
//	}

	public String sayHello() {
		return "Hello " + name;
	}

	public void print() {
		this.printer.print(sayHello());
	}

}
