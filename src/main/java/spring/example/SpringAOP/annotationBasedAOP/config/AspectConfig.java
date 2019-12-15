package spring.example.SpringAOP.annotationBasedAOP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.example.SpringAOP.annotationBasedAOP.aspect.Logging;

@Configuration
public class AspectConfig {

    @Bean
    public Logging log() {
        return new Logging();
    }

}
