package kristar.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "kristar.projects.repository.user")
@EntityScan(basePackages = "kristar.projects.model")
@EnableSpringDataWebSupport(pageSerializationMode
        = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class OnlineBookStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }
}
