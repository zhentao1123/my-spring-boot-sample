package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	HelloService helloService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Test Run:
	 * java -jar sb-sample-noweb.jar 
	 * java -jar sb-sample-noweb.jar Tom
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------------------");
		if (args.length > 0) {
            System.out.println(helloService.sayHello(args[0].toString()));
        } else {
            System.out.println(helloService.sayHello());
        }
		System.out.println("-------------------------");
		//System.exit(0);
	}
}
