package spring.example.SpringAnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class TextEditor {

    private SpellChecker spellChecker;

    // 构造函数中的 @Autowired
    @Autowired
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor");
        this.spellChecker = spellChecker;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    //===== getter and setter =====//
    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    // @Resource 注释可以使用name属性, 在遵循 by-name 自动连接语义的设计下,
    //-> 注入name属性值对应的Bean.
    /*@Resource(name = "spellChecker")*/@Resource
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

}
