package com.sanjay.enote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🔹 Purpose:
 * Ye class hamara main entry point hai Spring Boot application ke liye.
 * Jab bhi application start hoti hai, sabse pehle isi class ka `main()` method call hota hai.
 *
 * 🔹 Real World Use:
 * - Ye bootstrapping ka kaam karta hai (initialize Spring context, auto-configurations, etc).
 * - Yahi se sari services, beans, controllers load hote hain.
 *
 * 🔹 Developer Note:
 * - `@SpringBootApplication` annotation ke andar 3 cheezein bundled hoti hain:
 *    1. @Configuration – configuration class mark karta hai.
 *    2. @EnableAutoConfiguration – Spring Boot ko auto configuration allow karta hai.
 *    3. @ComponentScan – current package aur sub-packages me beans scan karta hai.
 */
@SpringBootApplication
public class EnoteApIsServicesApplication {

	/**
	 * 🔹 Application Start Point
	 *
	 * @param args - Command-line arguments (CLI se aaye huye arguments)
	 *
	 * Real-world kaam: Ye hi wo method hai jisse Spring Boot app start hoti hai.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EnoteApIsServicesApplication.class, args);
	}

}
