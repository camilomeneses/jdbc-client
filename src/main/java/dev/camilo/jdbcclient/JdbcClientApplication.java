package dev.camilo.jdbcclient;

import dev.camilo.jdbcclient.post.Post;
import dev.camilo.jdbcclient.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class JdbcClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcClientApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(@Qualifier("clientPostService") PostService postService) {
		return args -> {
			postService.create(new Post("1234", "Hello World", "hello-world", LocalDate.now(), 1, "java, spring"));
		};
	}
}
