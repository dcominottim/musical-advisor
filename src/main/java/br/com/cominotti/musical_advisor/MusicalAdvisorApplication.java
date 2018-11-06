package br.com.cominotti.musical_advisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableHystrix
@EnableHystrixDashboard
public class MusicalAdvisorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicalAdvisorApplication.class, args);
	}
}
