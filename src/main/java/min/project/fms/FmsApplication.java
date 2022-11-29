package min.project.fms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"min.project.fms"})
@RestController
@EnableTransactionManagement
@OpenAPIDefinition(info = @Info(title = "FMS API", version = "1.0", description = "File Management Service"))
public class FmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FmsApplication.class, args);
	}
}
