package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloControllerTest {

	@Test
	void helloController() {
		HelloController helloController = new HelloController(name -> name);

		String ret = helloController.hello("Test");

		assertThat(ret).isEqualTo("Test");
	}

	@Test
	void failsHelloController() {
		HelloController helloController = new HelloController(name -> name);

		Assertions.assertThatThrownBy(() -> {
			String ret = helloController.hello(null);
		}).isInstanceOf(IllegalArgumentException.class);

		Assertions.assertThatThrownBy(() -> {
			String ret = helloController.hello("");
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
