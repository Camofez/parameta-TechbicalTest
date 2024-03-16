package parametaTechnicalTest.parametaTechnicalTest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;


@SpringBootApplication
@EnableWs
public class ParametaTechnicalTestApplication extends WsConfigurerAdapter {

	public static void main(String[] args) {

		SpringApplication.run(ParametaTechnicalTestApplication.class, args);
	}

}






