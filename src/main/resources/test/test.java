package github.com.SuiteLHY.thinkingInJava.chapter18.IO;

import com.sun.istack.internal.NotNull;
import github.com.SuiteLHY.thinkingInJava.DemoUtils;

import java.io.*;
import java.util.*;

/**
 * I/O流的典型使用方式
 *
 */
public class TypicalUsesOfIOStreams {

    static final String CONFIG_DIR_PATH = "src\\main\\resources\\config";

    static final String CONFIG_FILE_NAME = "testInfo.properties";

    /**
     * 实现缓冲输入文件的类
     *
     * 打开�?个文件用于字符输�?
     * 使用 <code>BufferedReader</code> 获取输入流�??
     */
    public static class BufferedInputFileUtil {

        private String filepath;

        private File file;

        /**
         * 构�?�方�?
         *
         * @param filepath
         * @throws RuntimeException
         */
        public BufferedInputFileUtil(@NotNull String filepath)
                throws RuntimeException {
            File file = new File(filepath);
            if (!file.exists()) {
                throw new RuntimeException("指定文件路径不存在！");
            }
            if (file.isDirectory())  {
                throw new RuntimeException("指定的文件路径不能是文件夹！");
            }
            this.filepath = filepath;
            this.file = file;
        }

        /**
         * 读取指定文件数据输出到String
         *
         * @param filePath
         * @return
         * @throws IOException
         */
        public static String read(@NotNull String filePath)
                throws IOException {
            BufferedReader in = new BufferedReader(
                    new FileReader(filePath)
            );
            return readBufferedReader(in);
        }

        /**
         * 读取指定文件数据输出到String
         *
         * @param file
         * @return
         * @throws IOException
         */
        public static String read(@NotNull File file)
                throws IOException {
            BufferedReader in = new BufferedReader(
                    new FileReader(file)
            );
            return readBufferedReader(in);
        }

        public String read() throws IOException {
            return read(this.filepath);
        }

        /**
         * 读取指定文件数据输出到List<String>
         *
         * @param filename
         * @return
         * @throws IOException
         */
        public static List<String> readLine(@NotNull String filename)
                throws IOException {
            BufferedReader in = new BufferedReader(
                    new FileReader(filename)
            );
            return readLineBufferedReader(in);
        }

        public List<String> readLine() throws IOException {
            return readLine(this.filepath);
        }

        /**
         * 读取BufferedReader的数据输出到String
         * 注意：I/O流关闭操作的不规范可能会埋下隐患�?
         *
         * @param in
         * @return
         * @throws IOException
         */
        private static String readBufferedReader(BufferedReader in)
                throws IOException {
            StringBuilder sb = new StringBuilder();
            try {
                String s;
                while (null != (s = in.readLine())) {
                    /*sb.append(s + "\n");// 原著示例中的不规范使用方式，引以为戒�?*/
                    sb.append(s).append('\n');
                }
            } catch (IOException e) {
                throw e;
            } finally {
                try {
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
            return sb.toString();
        }

        /**
         * 读取BufferedReader的数据输出到持有String的List
         * 注意：I/O流关闭操作的不规范可能会埋下隐患�?
         *
         * @param in
         * @return
         * @throws IOException
         */
        private static List<String> readLineBufferedReader(BufferedReader in)
                throws IOException {
            List<String> result = new ArrayList<>();
            try {
                String s;
                while (null != (s = in.readLine())) {
                    result.add(s);
                }
            } catch (IOException e) {
                throw e;
            } finally {
                try {
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
            return result;
        }

        //===== 演示用方�? =====//
        /**
         * 示例：缓冲输入文�?
         *
         * @throws IOException
         */
        public static void demo() throws IOException {
            String directoryPath = ".";
            String filename = TypicalUsesOfIOStreams.class.getSimpleName() + ".java";
            Map<String, String> fileInfoMap = new HashMap<>();
            //=== 搜索匹配文件 ===//
            new ProcessFiles(new ProcessFiles.Strategy() {
                @Override
                public void process(File file) {
                    fileInfoMap.put(file.getName(), file.getAbsolutePath());
                }
            }, "[A-Z][a-zA-Z\\d_]*.java").start(new String[] {directoryPath});
            //======//
            if (fileInfoMap.containsKey(filename)) {
                //=== 实现缓冲输入文件 ===//
                BufferedInputFileUtil inputFile = new BufferedInputFileUtil(fileInfoMap.get(filename));

                DemoUtils.show("===== ↓↓�? inputFile.read() ↓↓�? =====");
                DemoUtils.show(inputFile.read());
                DemoUtils.show("===== ↑↑�? inputFile.read() ↑↑�? =====");
                DemoUtils.show(null);

                DemoUtils.show("===== ↓↓�? inputFile.readLine() ↓↓�? =====");
                for (String line : inputFile.readLine()) {
                    DemoUtils.show(line);
                }
                DemoUtils.show("===== ↑↑�? inputFile.readLine() ↑↑�? =====");
                //======//
            } else {
                DemoUtils.show("指定文件夹路径下不存在指定规则的匹配文件�?");
            }
        }

    }

    /**
     * 实现从内存输入的�?
     *
     * 从String对象输入
     * 使用 <code>StringReader</code> 获取输入�?
     */
    public static class MemoryInputUtil {

        //===== 演示用方�? =====//
        public static void demo() throws IOException {
            final String filename = TypicalUsesOfIOStreams.class.getSimpleName() + ".java";
            //=== 搜索匹配文件 ===//
            String regex = "[A-Z][a-zA-Z\\d_]*.java";
            File file = null;
            List<File> files = DirectoryUtil.localAll(".", regex);
            for (File each : files) {
                if (null == each) continue;
                if (filename.equals(each.getName())) {
                    file = each;
                    break;
                }
            }
            //=== 实现从String对象输入 ===//
            if (null != file && file.exists()) {
                DemoUtils.show("===== ↓↓�? MemoryInput.demo() ↓↓�? =====");
                StringReader in = new StringReader(
                        BufferedInputFileUtil.read(file.getAbsolutePath())
                );
                for (int each; (each = in.read()) != -1;) {
                    System.out.print((char) each);
                }
                DemoUtils.show("===== ↑↑�? MemoryInput.demo() ↑↑�? =====");
            }
            //======//
        }

    }

    /**
     * 实现格式化字符输入的�?
     *
     * 读取格式化的数据，可以使�? <code>DataInputStream</code>�?
     * 它是�?个面向字节的I/O类�??
     */
    public static class FormattedMemoryInputUtil {

        public static void demo() throws IOException {
            final String filename = TypicalUsesOfIOStreams.class.getSimpleName() + ".java";
            String regex = filename;
            File file = null;
            String filepath;
            List<File> files = DirectoryUtil.localAll(".", regex);
            for (File each : files) {
                if (null == each) continue;
                if (filename.equals(each.getName())) {
                    file = each;
                    break;
                }
            }
            if (null != file) {
                filepath = file.getAbsolutePath();
                //=== 实现格式化字符输�? ===//
                byte[] inputBytes = BufferedInputFileUtil.read(filepath).getBytes();
                DataInputStream in = new DataInputStream(
                        new ByteArrayInputStream(inputBytes)
                );
                // �? available() 查看剩余的可供存取的字节数量，以确定输入流的末端
                while (in.available() != 0) {
                    System.out.print((char) in.readByte());
                }
                //======//
            }
        }

    }

    /**
     * 实现基本的文件输出的�?
     *
     * 使用 <code>FileWriter</code> 对象,  <code>BufferedWriter</code> 对象,
     *  <code>PrintWriter</code> 对象向输出文件写入数据�??
     */
    public static class BasicFileOutputUtil {

        public static void demo() throws IOException {
            final String filename = TypicalUsesOfIOStreams.class.getSimpleName() + ".java";
            String regex = "[A-Z][a-zA-Z\\d_]*.java";
            File file = null;
            List<File> files = DirectoryUtil.localAll(".", regex);
            for (File each : files) {
                if (null == each) continue;
                if (filename.equals(each.getName())) {
                    file = each;
                    break;
                }
            }
            if (null != file) {
                //=== 获取输入 ===//
                String fileData = BufferedInputFileUtil.read(file);
                BufferedReader in = new BufferedReader(
                        new StringReader(fileData)
                );
                //=== 实现基本的文件输�? ===//
                String testFilePath = getTestFilePath();
                File testFile = new File(testFilePath);
                if (!testFile.exists()) {
                    testFile.createNewFile();
                }
                // 输出�?
                PrintWriter out = null;
                try {
                    out = new PrintWriter(
                            new BufferedWriter(new FileWriter(testFilePath))
                    );
                    int lineCount = 0;
                    for (String each; null != (each = in.readLine()); ) {
                        out.println(each);
                        ++lineCount;
                    }
                    DemoUtils.show("写入�?" + lineCount + "行数�?");
                } catch (IOException e) {
                    throw e;
                } finally {
                    //-- 根据打开顺序的�?�方向关闭I/O�?
                    if (null != out) {
                        out.close();
                    }
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
                //======//
                DemoUtils.show(new File(".").getAbsolutePath());
            }
        }

    }

    static String getTestFilePath() {
        return "src\\main\\resources\\test\\test.java";
    }

}
