package io;

/**
 * 记录文件操作类的函数、方法
 * 特别记录Runtime类执行exe
 */


import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
//import java.io.*;
import java.lang.*;
import java.lang.Runtime;

public class Buffer {
    public static final String FILE_PATH_TEST = "tmp";

    /*
        Runtime类打开可执行文件
        */
    @Test
    public void execRuntime() {
        Runtime rt;
        try {
            rt = Runtime.getRuntime();
            File f4 = new File("C:/Program Files (x86)/Google/Chrome/Application/Chrome.exe");
            rt.exec(f4.toString());
        } catch (Exception e) {
        }
    }


    // ------------------------------

    /**
     * Runoob
     */
    //-------------------------------

    // 读取控制台的输入
    public static void bufferedReaderRead() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char c;
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');

        String str;
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("end"));
    }

    // 控制台的输出
    @Test
    public void printStreamWrite() throws IOException {
        PrintStream ps = new PrintStream(System.out);
        ps.print("A");
        ps.write('B');
    }


    // 文件读写（二进制）
    @Test
    public void fileStreamTest() {
        try {
            byte[] B = {11, 22, 33, 44};
            OutputStream os = new FileOutputStream("tmp/new.txt");
            for (int x = 0; x < B.length; x++) {
                os.write(B[x]);
            }
            os.close();

            InputStream is = new FileInputStream("tmp/new.txt");
            int size = is.available();
            for (int i = 0; i < size; i++) {
                System.out.println((char) is.read() + " ");
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 文件读写(中文输入)
    @Test
    public void fileStream() {
        try {
            FileOutputStream fos = new FileOutputStream("tmp/new.txt");
            OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            writer.append("中文输入");
            writer.append("\r\n");
            writer.append("English");
            writer.close();
            fos.close();

            FileInputStream fis = new FileInputStream("tmp/new.txt");
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuffer buffer = new StringBuffer();
            while (reader.ready()) {
                buffer.append((char) reader.read());
                System.out.println(buffer);
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------

    /**
     * shiyanlou
     */
    // -------------------------------------
    public static void main(String[] args) {
        File f1 = new File(FILE_PATH_TEST);
        File f2 = new File(f1 + "/new.txt");
        File f3 = new File(f1, "test190713.txt");

        /*
        File类
         */
        try {
            System.out.println("创建新文件 " + f3.createNewFile());
            System.out.println("删除新文件 " + f3.delete());
            // 创建目录
            System.out.println("创建目录 " + f1.mkdir());
            // 返回所有文件
            System.out.println("输出所有文件名：");
            File[] files = f1.listRoots();
            for (File file : files) {
                System.out.println(file);
                if (file.length() > 0) {
                    String[] filenames = file.list();
                    for (String filename : filenames) {
                        System.out.println(filename);
                    }
                }
            }
            // 返回所有文件，以File类型
            File[] fileGets = File.listRoots();
            for (File fileGet : fileGets) {
                System.out.println(fileGet);
                if (fileGet.length() > 0) {
                    File[] fileGetNames = fileGet.listFiles();
                    for (File fileGetName : fileGetNames) {
                        System.out.println(fileGetName.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        字节输入输出流
         */
        System.out.println("=============输出至new.txt中===========");
        try {
            File f5 = new File("src/IO/utility", "Buffer.java");
            FileInputStream fis = new FileInputStream(f5);
            boolean whetherRefresh = false;
            FileOutputStream fos = new FileOutputStream(f2, whetherRefresh);
            // 顺序读取输入流
            byte[] B = new byte[4];
            int n;
            while ((n = fis.read(B)) != -1) {
                String s = new String(B, 0, n);
//                byte[] outputBytes = s.getBytes();
//                System.out.println(s);
                fos.write(B, 0, n);
            }

            fis.close();
            fos.close();
            System.out.println("=====================================");
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        文件字符输入输出流，Unicode格式
         */
        try {
            File f5 = new File("src/IO/utility", "Buffer.java");
            FileReader fr = new FileReader(f5);
            FileWriter fw = new FileWriter(f2, false);
            char[] B = new char[2];  //注意是对字符的读取
            int n = -1;
            while ((n = fr.read(B)) != -1) {
                String s = new String(B, 0, n);
                char[] changeB = new char[2];
                s.getChars(0, n, changeB, 0);
                fw.write(changeB, 0, n);
            }
            fr.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        缓冲流，字符输入输出流的高级版
         */
        try {
            File f5 = new File("src/IO/utility", "Buffer.java");
            FileReader fr = new FileReader(f5);
            FileWriter fw = new FileWriter(f2, false);
            BufferedReader bfr = new BufferedReader(fr);
            BufferedWriter bfw = new BufferedWriter(fw);
            String s;
            while ((s = bfr.readLine()) != null) {
                bfw.write(s);
                bfw.newLine();
            }
            bfr.close();
            bfw.close();
        } catch (IOException e) {
            System.out.println("error: " + e);
        }


        /*
        随机流
         */
        System.out.println("===========随机流==============");
        try {
            File f5 = new File("tmp", "randomAccessFile.txt");
            f5.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(f5, "rw");
            // 输入字符串，然后倒序输出
            String s = "Happy Coding Life!";
            raf.seek(0);
            raf.writeChars(s);
            char[] recordChar = new char[18];
            s.getChars(0, 18, recordChar, 0);
            for (int i = recordChar.length - 1; i >= 0; i--) {
                raf.seek(i * 2);
                char c = raf.readChar();
                System.out.print(c);
            }

            raf.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }


        /*
        补充：Scanner对象解析文件
         */
        System.out.println("===========Scanner对象解析==============");
        try {
            Scanner sc = new Scanner(f2);
            while (sc.hasNext()) {
                try {
                    String s = sc.next();
                    System.out.println(s);
                } catch (Exception eSmall) {
                    eSmall.printStackTrace();
                } finally {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
