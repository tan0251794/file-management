package min.project.tms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@OpenAPIDefinition(info = @Info(title = "TMS API", version = "1.0", description = "Template Management Service"))
public class TmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TmsApplication.class, args);
	}
}
