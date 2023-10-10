package MyTest.test;

import MyTest.test.domain.chatService.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestApplication implements CommandLineRunner {

	@Autowired
	private ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args){
		System.out.println("테스트입니다.");

		chatService.enterChatRoom("테스트라고..!");
	}
}
