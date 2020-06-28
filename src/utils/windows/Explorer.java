package utils.windows;

import com.sun.istack.NotNull;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description 工具类：文件浏览器
 * 获取指定路径下的所有文件，并将指定文件添加到输出流。
 */

public class Explorer {
    private static String filePath = File.listRoots()[1].getPath();

    /**
     * 递归获取所有文件
     *
     * @param filePath
     * @return
     */
    public static void getChildFiles(@NotNull File filePath, String tag) {
        System.out.println(tag + "-" + filePath.getName());

        // length() && isDirectory()
        try {
            if (filePath.isDirectory()) {
                File[] childFiles = filePath.listFiles();
                for (File childFile : childFiles) {
                    getChildFiles(childFile, tag + "|");
                }
            }
        } catch (Throwable e) {
            System.out.println("deny to access! " + e);
        }

    }


    /**
     * 获取目录下所有文件信息，并存入Map
     *
     * @param filePath
     */
    public static void recurseLookUp(String filePath) {
        // key：文件路径字符串, value：文件
        Map<String, File> hierarchy = new HashMap<>();
        hierarchy.put(filePath, new File(filePath));
        // 如果文件是文件夹类型，则将子文件加入hierarchy
        hierarchy = getFiles(hierarchy, filePath);
        // 输入为:quit，则退出
        while (!(filePath = new Scanner(System.in).nextLine()).equals(":quit")) {
            // 输入为:read并指定文件名，则输出文件内容
            if (filePath.equals(":read")) {
                getFileDetails(hierarchy.get(new Scanner(System.in).nextLine()));
                continue;
            }
            // 继续循环子文件
            hierarchy = getFiles(hierarchy, filePath);
        }
    }


    /**
     * 将文件夹内所有的子文件加入Map
     *
     * @param hierarchy
     * @param fileName
     * @return
     */
    private static Map<String, File> getFiles(Map<String, File> hierarchy, String fileName) {
        File filePath = hierarchy.get(fileName);
        Map<String, File> newHierarchy = new HashMap<>();
        System.out.println("★-------" + filePath.getName() + "------★");

        // 如果是文件夹，则遍历获取所有子文件
        try {
            if (filePath.isDirectory()) {
                File[] childFiles = filePath.listFiles();
                for (File childFile : childFiles) {
                    // 格式化输出子文件的路径
                    System.out.println(getHierarchy(childFile) + "--" + childFile.getName());
                    newHierarchy.put(childFile.getName(), childFile);
                }
            }
        } catch (Throwable e) {
            System.out.println("deny to access! " + e);
        } finally {
            System.out.println("★-------" + "END OF LIST" + "------★");
        }

        return newHierarchy;
    }


    /**
     * 根据文件路径的层次，进行格式化输出，增加tag
     *
     * @param file
     * @return
     */
    private static int getHierarchy(File file) {
        int tag = 0;
        char[] filePath = file.getAbsolutePath().toCharArray();
        for (int i = 0; i < filePath.length; i++) {
            if (filePath[i] == '/' || filePath[i] == '\\') {
                tag++;
            }
        }
        return tag;
    }

    /**
     * 获取文件内容
     *
     * @param file
     */
    private static void getFileDetails(File file) {
        try {
            FileReader fr = new FileReader(file);
            char[] chars = new char[10];
            int n;
            while ((n = fr.read(chars)) != -1) {
                System.out.print(chars.toString());
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException{
//        recurseLookUp(filePath);

        // 当前使用的系统
        System.out.println(System.getProperty("os.name"));
        // java.nio.Paths与Path的使用
        Path path = Paths.get("tmp", "new.txt");
        // Path与URI转换
        URI uri = path.toUri();
        // 路径转换为文件，按理来说路径就是有意义的字符串，而不是文件的概念
        File file = path.toFile();
        System.out.println(path.toAbsolutePath().getRoot());
        System.out.println(uri);
        System.out.println(file.getAbsolutePath());

        // 两种方式都可，对Path类中的每一个path进行拆分，注意是不包括根目录的
        Path absPath = path.toAbsolutePath();
        for (Path p : absPath) {
            System.out.println(p.toAbsolutePath());
        }
        for (int i = 0; i < absPath.getNameCount(); i++) {
            System.out.println(absPath.getName(i).toAbsolutePath());
        }
        // 以文件后缀名进行判断Path，结果是失败的
        System.out.println("文件后缀判断结果：" + absPath.endsWith(".txt"));
        // 以根目录来归类Path，结果正常
        System.out.println("根目录判断结果：" + absPath.startsWith(absPath.getRoot()));

        // java.nio.file.Files类
        // 写入1000行数组
        UUID uuid = UUID.randomUUID();
        Random random = new Random(100);
        byte[] bytes = new byte[1000];
        random.nextBytes(bytes);
        Files.write(path, bytes);
        System.out.println("当前文件行数：" + Files.size(path));
        // 对字节数据进行读取
        Files.readAllLines(path).stream().skip(998).map(i -> i).forEach(System.out::print);
        System.out.println("当前文件行数：" + Files.size(path));
        // 直接对“小文件”进行读写
        List<String> lines = Files.readAllLines(path);
        lines.stream().filter(line -> line.startsWith("E")).map(line -> line + "直接读写").forEach(System.out::println);


    }
}
