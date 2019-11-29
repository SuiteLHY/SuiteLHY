package github.com.SuiteLHY.thinkingInJava.chapter17.ContainersInDepth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
public class ContainersInDepthApplicationTests {

    @Autowired
    public ApplicationContext context;

    @Test
    void contextLoads() {
    }

    @Test
    void iteratingOverCollections() {
        IteratingOverCollections testUnit;
        Assert.notNull(testUnit = context.getBean(IteratingOverCollections.class),
                "获取测试单元失败");
        testUnit.codeAfterThinking();
    }

}
