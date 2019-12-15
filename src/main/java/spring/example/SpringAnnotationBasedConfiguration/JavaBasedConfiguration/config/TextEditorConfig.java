package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config;

import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.SpellChecker;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.TextEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {

    @Bean
    public TextEditor textEditor() {
        return new TextEditor(spellChecker());
    }

    @Bean
    public SpellChecker spellChecker() {
        return new SpellChecker();
    }

}
