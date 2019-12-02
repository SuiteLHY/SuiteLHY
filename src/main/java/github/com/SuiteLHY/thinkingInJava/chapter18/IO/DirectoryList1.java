package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import github.com.SuiteLHY.thinkingInJava.DemoUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 目录列表器 - 使用匿名内部类设计的改良版
 *
 */
@Component
public class DirectoryList1 {

    private final String[] directoryList;

    private final String regex;

    public DirectoryList1() {
        File path = new File(".");
        this.regex = "src|target";
        String[] currentDirectoryList = path.list(new FilenameFilter() {
            // 注意匿名内部类接受的外部参数必须是effective final的
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        if (null != currentDirectoryList) {
            Arrays.sort(currentDirectoryList, String.CASE_INSENSITIVE_ORDER);
            this.directoryList = currentDirectoryList;
        } else {
            this.directoryList = new String[]{};
        }
    }

    public void demo() {
        for (String dir : directoryList) {
            DemoUtils.show(dir);
        }
    }

}
