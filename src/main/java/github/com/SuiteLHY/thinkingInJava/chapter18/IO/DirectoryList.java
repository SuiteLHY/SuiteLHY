package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import github.com.SuiteLHY.thinkingInJava.DemoUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 目录列表器
 *
 * 因为 list() 实现了基本的功能，而且按照 FilenameFilter 类型形式的对象提供的<b>策略</b>,
 * 完善其所提供功能所需的算法，所以可以说这是一个<b>策略模式</b>的例子.
 *
 * list() 方法会为此目录下的每个文件名调用 accept(), 来判断该文件是否包含在内, 判断结果由
 * accept() 返回的布尔值表示. accept() 方法会使用一个正则表达式的 matcher 对象来进行匹配.
 *
 */
@Component
public class DirectoryList {

    private final String[] directoryList;

    public DirectoryList() {
        File path = new File(".");
        String[] currentDirectoryList = path.list(new DirectoryFilter("src|target"));
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

    public static void main(String[] args) {
        File path = new File(".");
        String[] directoryList;
        if (args.length == 0) {
            directoryList = path.list();
        } else {
            directoryList = path.list(new DirectoryFilter(args[0]));
        }
        Arrays.sort(directoryList, String.CASE_INSENSITIVE_ORDER);
        for (String dir : directoryList) {
            DemoUtils.show(dir);
        }
    }

}

/**
 * DirectoryFilter这个类存在的唯一作用就是提供 <code>accept()</code> 方法.
 *
 * 创建这个类的目的在于把 accept() 方法提供给 list() 使用, 使 list() 可以回调 accept(),
 * 进而以决定哪些文件包含在列表中. 这种结构也常常称为"回调".
 *
 */
class DirectoryFilter implements FilenameFilter {

    private Pattern pattern;

    public DirectoryFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }

}
