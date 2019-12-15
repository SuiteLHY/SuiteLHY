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
     * 打开一个文件用于字符输入
     * 使用 <code>BufferedReader</code> 获取输入流。
     */
    public static class BufferedInputFileUtil {

        private String filepath;

        private File file;

        /**
         * 构造方法
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
         * 注意：I/O流关闭操作的不规范可能会埋下隐患！
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
                    /*sb.append(s + "\n");// 原著示例中的不规范使用方式，引以为戒！*/
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
         * 注意：I/O流关闭操作的不规范可能会埋下隐患！
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

        //===== 演示用方法 =====//
        /**
         * 示例：缓冲输入文件
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

                DemoUtils.show("===== ↓↓↓ inputFile.read() ↓↓↓ =====");
                DemoUtils.show(inputFile.read());
                DemoUtils.show("===== ↑↑↑ inputFile.read() ↑↑↑ =====");
                DemoUtils.show(null);

                DemoUtils.show("===== ↓↓↓ inputFile.readLine() ↓↓↓ =====");
                for (String line : inputFile.readLine()) {
                    DemoUtils.show(line);
                }
                DemoUtils.show("===== ↑↑↑ inputFile.readLine() ↑↑↑ =====");
                //======//
            } else {
                DemoUtils.show("指定文件夹路径下不存在指定规则的匹配文件！");
            }
        }

    }

    /**
     * 实现从内存输入的类
     *
     * 从String对象输入
     * 使用 <code>StringReader</code> 获取输入流
     */
    public static class MemoryInputUtil {

        //===== 演示用方法 =====//
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
                DemoUtils.show("===== ↓↓↓ MemoryInput.demo() ↓↓↓ =====");
                StringReader in = new StringReader(
                        BufferedInputFileUtil.read(file.getAbsolutePath())
                );
                for (int each; (each = in.read()) != -1;) {
                    System.out.print((char) each);
                }
                DemoUtils.show("===== ↑↑↑ MemoryInput.demo() ↑↑↑ =====");
            }
            //======//
        }

    }

    /**
     * 实现格式化字符输入的类
     *
     * 读取格式化的数据，可以使用 <code>DataInputStream</code>，
     * 它是一个面向字节的I/O类。
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
                //=== 实现格式化字符输入 ===//
                byte[] inputBytes = BufferedInputFileUtil.read(filepath).getBytes();
                DataInputStream in = new DataInputStream(
                        new ByteArrayInputStream(inputBytes)
                );
                // 用 available() 查看剩余的可供存取的字节数量，以确定输入流的末端
                while (in.available() != 0) {
                    System.out.print((char) in.readByte());
                }
                //======//
            }
        }

    }

    /**
     * 实现基本的文件输出的类
     *
     * 使用 <code>FileWriter</code> 对象,  <code>BufferedWriter</code> 对象,
     *  <code>PrintWriter</code> 对象向输出文件写入数据。
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
                //=== 实现基本的文件输出 ===//
                File testFile = getTestFile();
                // 输出流
                PrintWriter out = null;
                try {
                    /*// 业务冗余：包含了不必要的装饰工作
                    out = new PrintWriter(
                            new BufferedWriter(new FileWriter(testFilePath))
                    );*/
                    /*out = new PrintWriter(testFilePath);*/
                    out = new PrintWriter(testFile);
                    int lineCount = 0;
                    for (String each; null != (each = in.readLine()); ) {
                        out.println(each);
                        ++lineCount;
                    }
                    DemoUtils.show("写入了" + lineCount + "行数据");
                } catch (IOException e) {
                    throw e;
                } finally {
                    //-- 根据打开顺序的逆方向关闭I/O流
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

    /**
     * 实现按照Java的UTF-8编码格式，存储和恢复数据
     *
     * 注意：按照指定格式读写数据，应该是整个文件的数据使用统一的格式。
     * 另外，对象序列化和XML，可能更容易实现和控制的存储和读取复杂数据结构的方式。
     */
    public static class StoringAndRecoveringData {

        public static void demo() {
            DataOutputStream out = null;
            //=== 实现数据存储：将数据按照Java的UTF-8编码格式写入输出流 ===//
            try {
                out = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(getTestTxt()))
                );
                out.writeDouble(3.14159);
                // 将数据按照Java的UTF-8编码格式写入流
                out.writeUTF("\nA new line\n");
                out.writeInt(978153);
                out.writeUTF("Square root of 2");
                out.writeUTF("=====");
                out.writeUTF("3.14159");
                out.writeUTF("978153");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //=== 实现数据恢复：从输入流中读取符合Java的UTF-8编码格式的数据 ===//
            DataInputStream in = null;
            try {
                in = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(getTestTxt()))
                );
                DemoUtils.show(in.readDouble());
                // Only readUTF() will recover the Java-UTF String properly
                DemoUtils.show(in.readUTF());
                DemoUtils.show(in.readInt());
                DemoUtils.show(in.readUTF());
                DemoUtils.show(in.readUTF());
                DemoUtils.show(in.readUTF());
                DemoUtils.show(in.readUTF());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != in) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 实现随机地访问和读写文件 - 示例类
     *
     * 使用 <code>RandomAccessFile</code>
     */
    public static class UsingRandomAccessFile {

        public static void demo() throws IOException {
            DemoUtils.show("===== ↓↓↓ UsingRandomAccessFile.demo() ↓↓↓ =====");
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(getTestFile(), "r");
                String each;
                for (long i = 1; null != (each = randomAccessFile.readLine()); i++) {
                    DemoUtils.show(((i < 1000)
                            ? ((i < 100) ? ((i < 10) ? "000" : "00") : "0")
                            : "")
                            + i + " | " + each
                    );
                }
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e) {
                throw e;
            } finally {
                if (null != randomAccessFile) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            }
            DemoUtils.show("===== ↑↑↑ UsingRandomAccessFile.demo() ↑↑↑ =====");
        }

        public static void demo1() throws IOException {
            DemoUtils.show("===== ↓↓↓ UsingRandomAccessFile.demo1() ↓↓↓ =====");
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(getTestFile(), "rw");
                String each;
                for (long i = 1; null != (each = randomAccessFile.readLine()); i++) {
                    DemoUtils.show(((i < 1000)
                            ? ((i < 100) ? ((i < 10) ? "000" : "00") : "0")
                            : "")
                            + i + " | " + each
                    );
                }
                randomAccessFile.writeUTF("/* random written content */");
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e) {
                throw e;
            } finally {
                if (null != randomAccessFile) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            }
            DemoUtils.show("===== ↑↑↑ UsingRandomAccessFile.demo1() ↑↑↑ =====");
        }

    }

    /**
     * 测试文件信息类
     */
    private static class Test {
        static final String testFilePath = "src\\main\\resources\\test\\test.java";
        static final String testTxtPath = "src\\main\\resources\\test\\test.txt";
        // 通过【单例模式 - 饿汉式】进行初始化
        static final File testFile = new File(testFilePath);
        static final File testTxt = new File(testTxtPath);
        static File getTestFile() throws IOException {
            return getFile(testFile);
        }
        static File getTestTxt() throws IOException {
            return getFile(testTxt);
        }
        private static File getFile(File file) throws IOException {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw e;
                }
            }
            return file;
        }
    }

    private static String getTestFilePath() {
        return Test.testFilePath;
    }

    static File getTestFile() {
        try {
            return Test.getTestFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static File getTestTxt() {
        try {
            return Test.getTestTxt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
 /* random written content */