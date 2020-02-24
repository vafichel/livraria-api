package br.com.livraria;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LivrariaApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class LivrariaApplicationTests {

	@Test
	void contextLoads() {
	}

}
