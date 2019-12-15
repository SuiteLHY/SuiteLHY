package spring.example.SpringAOP.annotationBasedAOP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.example.SpringAOP.model.Student;

@Configuration
@Import({AspectConfig.class})
public class AppConfig {

    @Bean
    public Student student() {
        Student result = new Student();
        result.setName("李明");
        result.setAge(18);
        return result;
    }

}
