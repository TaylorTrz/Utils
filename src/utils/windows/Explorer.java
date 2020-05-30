package utils.windows;

import com.sun.istack.NotNull;

import java.io.*;
import java.util.*;

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


    public static void main(String[] args) {
//        getChildFiles(new File(filePath), "");
        recurseLookUp(filePath);
    }
}
