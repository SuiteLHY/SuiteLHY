package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config;

import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

    @Bean
    public HelloWorld helloWorld() {
        HelloWorld result = new HelloWorld();
        result.setMessage("Hello, world!");
        return result;
    }

}
