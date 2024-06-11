package com.stoyanov.explore;

import org.springframework.boot.SpringApplication;

public class TestExploreApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExploreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
