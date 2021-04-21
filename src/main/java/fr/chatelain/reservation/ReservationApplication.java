package fr.chatelain.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		System.err.println(System.getProperty("db_hostname"));
		SpringApplication.run(ReservationApplication.class, args);
	}

}
