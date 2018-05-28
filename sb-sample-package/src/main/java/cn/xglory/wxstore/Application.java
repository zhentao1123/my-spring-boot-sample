package cn.xglory.wxstore;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		/*
		SpringApplication app = new SpringApplication(Application.class);
		Map<String, Object> defProperties = new HashMap<>();
		defProperties.put("spring.profiles.default", "dev");
		app.setDefaultProperties(defProperties);
		app.run(args);
		*/
	}
}
