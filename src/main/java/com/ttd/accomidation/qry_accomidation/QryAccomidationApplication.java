package com.ttd.accomidation.qry_accomidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
public class QryAccomidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QryAccomidationApplication.class, args);
	}
}
