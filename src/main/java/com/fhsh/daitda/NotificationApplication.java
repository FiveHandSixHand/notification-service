package com.fhsh.daitda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 엔티티가 있는 최상위 패키지를 명시합니다.
@EntityScan(basePackages = "com.fhsh")
// 레포지토리가 있는 최상위 패키지를 명시합니다. 꼭없어도 되긴하는데 일단만들었습니다 확인용
@EnableJpaRepositories(basePackages = "com.fhsh")
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
