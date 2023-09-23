package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class HelloApiTest {

	@Test
	void helloApi() {
		TestRestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> res =
			rest.getForEntity("http://localhost:8080/app/hello?name={name}", String.class, "Spring");

		// status code 200
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
		// header(content-type) text/plain
		assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
		// body Hello Spring
		assertThat(res.getBody()).isEqualTo("*Hello Spring*");
	}

	@Test
	void failHelloApi() {
		TestRestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> res =
			rest.getForEntity("http://localhost:8080/app/hello?name=", String.class);

		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
