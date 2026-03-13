package kz.sellora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "kz.sellora.core.model.entity")
public class SelloraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelloraApplication.class, args);
	}

}
