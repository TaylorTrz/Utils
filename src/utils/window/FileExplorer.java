package utils.window;

import com.sun.istack.NotNull;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @description 工具类：文件浏览器
 * 获取指定路径下的所有文件，并将指定文件添加到输出流。
 */

public class FileExplorer {
    private static String filePath = File.listRoots()[1].getPath();

    /**
     * Recurse to access all of the files
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
     * 获取目录下所有文件信息，并存入hierarchy映射
     * @param filePath
     */
    public static void recurseLookUp(String filePath) {
        Map<String, File> hierarchy = new HashMap<>();
        hierarchy.put(filePath, new File(filePath));
        hierarchy = getFiles(hierarchy, filePath);
        while (!(filePath = new Scanner(System.in).nextLine()).equals(":quit")) {
            if (filePath.equals(":read")) {
                getFileDetails(hierarchy.get(new Scanner(System.in).nextLine()));
                continue;
            }
            hierarchy = getFiles(hierarchy, filePath);
        }
    }

    private static Map<String, File> getFiles(Map<String, File> hierarchy, String fileName) {
        File filePath = hierarchy.get(fileName);
        Map<String, File> newHierarchy = new HashMap<>();
        System.out.println("★-------" + filePath.getName() + "------★");

        try {
            if (filePath.isDirectory()) {
                File[] childFiles = filePath.listFiles();
                for (File childFile : childFiles) {
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
