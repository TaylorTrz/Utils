package utils.window;

import com.sun.istack.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

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
    private static void getChildFiles(@NotNull File filePath, String tag) {
        System.out.println(tag + filePath.getName());

        // length() && isDirectory()
        try {
            if (filePath.isDirectory()) {
                File[] childFiles = filePath.listFiles();
                for (File childFile : childFiles) {
                    getChildFiles(childFile, tag + "    ");
                }
            }
        } catch (Throwable e) {
            System.out.println("deny to access! " + e);
        }

    }

    public static void main(String[] args) {
//        printAllFiles("C:/Documents and Settings");
        getChildFiles(new File(filePath), "");
    }
}
