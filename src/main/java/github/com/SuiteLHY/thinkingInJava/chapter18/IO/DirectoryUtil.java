package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import github.com.SuiteLHY.thinkingInJava.DemoUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 文件目录实用工具
 *
 */
public final class DirectoryUtil {

    /**
     * 对文件目录下一层级的文件, 进行经过基于正则的文件过滤的文件选择
     * @param dir
     * @param regex
     * @return
     */
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }

    public static File[] local(String path, String regex) {
        return local(new File(path), regex);
    }

    public static class TreeInfo {

        public List<File> directories = new ArrayList<>();

        public List<File> files = new ArrayList<>();

        public Iterator<File> iterator() {
            return files.iterator();
        }

        public int size() {
            return files.size();
        }

        public boolean isEmpty() {
            return files.size() == 0 && directories.size() == 0;
        }

        public void addAll(TreeInfo treeInfo) {
            directories.addAll(treeInfo.directories);
            files.addAll(treeInfo.files);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("directories=");
            sb.append(directories);
            sb.append("\nfiles=");
            sb.append(files);
            return sb.toString();
        }

        public static TreeInfo walk(File start, String regex) {
            return recurseDirs(start, regex);
        }

        public static TreeInfo walk(String start, String regex) {
            return walk(new File(start), regex);
        }

        public static TreeInfo walk(File start) {
            return walk(start, ".*");
        }

        public static TreeInfo walk(String start) {
            return walk(start, ".*");
        }

        static TreeInfo recurseDirs(File startDir, String regex) {
            TreeInfo result = new TreeInfo();
            for (File item : startDir.listFiles()) {
                if (item.isDirectory()) {
                    result.directories.add(item);
                    result.addAll(recurseDirs(item, regex));
                } else if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
            return result;
        }

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            DemoUtils.show(TreeInfo.walk("."));
        } else {
            for (String arg : args) {
                DemoUtils.show(TreeInfo.walk(arg));
            }
        }
    }

}
