package ks46team02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ks46team02Application {

	private static final Logger log = LoggerFactory.getLogger(Ks46team02Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Ks46team02Application.class, args);
	}

}
