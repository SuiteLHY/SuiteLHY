package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import com.sun.istack.internal.NotNull;
import github.com.SuiteLHY.thinkingInJava.DemoUtils;

import java.io.File;
import java.io.IOException;

/**
 * 操作粒度更细的文件目录工具
 *
 * (这是 <b>策略模式</b> 的另一个示例)
 */
public class ProcessFiles {

    /**
     * Strategy接口内嵌在 <code>ProcessFiles</code> 对象中, 这使得你在实现它的时候,
     * 必须先实现 <code>ProcessFiles.Strategy</code> .
     */
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;

    private String regex;

    /**
     * 构造方法
     * @param strategy
     * @param regex
     */
    public ProcessFiles(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex;
    }

    /**
     * 对文件夹下一层级的文件, 进行基于正则的文件过滤, 最后执行
     * @param root
     * @throws IOException
     */
    public void processDirectoryTree(File root) throws IOException {
        for (File file : DirectoryUtil.local(root.getAbsolutePath(),
                regex)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public void start(@NotNull String[] paths) {
        try {
            for (String path : paths) {
                File file;
                if (null == path || !(file = new File(path)).exists()) {
                    continue;
                }
                handle(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(@NotNull File[] files) {
        try {
            for (File file : files) {
                if (!file.exists()) {
                    continue;
                }
                handle(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handle(@NotNull File file) throws IOException {
        if (file.isDirectory()) {
            /*processDirectoryTree(file);
            return;*/
            // 修改为抓取所有层级的文件
            for (File item : file.listFiles()) {
                handle(item);
            }
        } else if (file.getName().matches(regex)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    //===== 演示用的方法 =====//
    public static void demo() {
        try {
            new ProcessFiles(new ProcessFiles.Strategy() {
                @Override
                public void process(File file) {
                    DemoUtils.show(file.getName());
                }
            }, "[A-Z][a-zA-Z\\d_]*.java").start(new String[]{"."});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
