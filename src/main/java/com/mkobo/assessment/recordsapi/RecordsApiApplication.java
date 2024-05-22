package com.mkobo.assessment.recordsapi;

import com.mkobo.assessment.recordsapi.queue.Send;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RecordsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordsApiApplication.class, args);
	}

}
