package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import github.com.SuiteLHY.thinkingInJava.DemoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;

/**
 * chapter18.IO, Test entry
 *
 */
@SpringBootTest
public class IOApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoad() {
    }

    @Test
    void directoryList() {
        DirectoryList directoryList;
        Assert.notNull(directoryList = context.getBean(DirectoryList.class),
                "获取测试单元失败");
        directoryList.demo();
    }

    @Test
    void directoryList1() {
        DirectoryList1 directoryList;
        Assert.notNull(directoryList = context.getBean(DirectoryList1.class),
                "获取测试单元失败");
        directoryList.demo();
    }

    @Test
    void directoryUtil() {
        File[] files;
        Assert.notNull(files = DirectoryUtil.local(".", "[A-Z][a-zA-Z\\d_]*.java"),
                "获取测试单元失败");
        DemoUtils.show(files.length);
        DemoUtils.show(null);

        DirectoryUtil.TreeInfo treeInfo;
        Assert.notNull(treeInfo = DirectoryUtil.TreeInfo.walk(".", "[A-Z][a-zA-Z\\d_]*.java"),
                "获取测试单元失败");
        DemoUtils.show(treeInfo.size());
        DemoUtils.show(treeInfo);
        DemoUtils.show(null);
    }

    @Test
    void processFiles() {
        ProcessFiles.demo();
    }

    @Test
    void randomAccessFile() throws IOException {
    }

    @Test
    void typicalUsesOfIOStreams() throws IOException {
        /*DemoUtils.show("The result of TypicalUsesOfIOStreams.BufferedInputFile.demo():");
        TypicalUsesOfIOStreams.BufferedInputFile.demo();
        DemoUtils.show(null);

        DemoUtils.show("The result of TypicalUsesOfIOStreams.MemoryInput.demo():");
        TypicalUsesOfIOStreams.MemoryInput.demo();
        DemoUtils.show(null);

        DemoUtils.show("The result of TypicalUsesOfIOStreams.FormattedMemoryInput.demo():");
        TypicalUsesOfIOStreams.FormattedMemoryInputUtil.demo();
        DemoUtils.show(null);*/

        DemoUtils.show("The result of TypicalUsesOfIOStreams.BasicFileOutputUtil.demo():");
        TypicalUsesOfIOStreams.BasicFileOutputUtil.demo();
        DemoUtils.show(null);

        DemoUtils.show("The result of TypicalUsesOfIOStreams.StoringAndRecoveringData.demo():");
        TypicalUsesOfIOStreams.StoringAndRecoveringData.demo();
        DemoUtils.show(null);
    }

}
