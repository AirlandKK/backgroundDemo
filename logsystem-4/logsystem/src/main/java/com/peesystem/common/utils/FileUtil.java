package com.peesystem.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/3/23.
 */
public class FileUtil {


    public static String readFile(String path){
        String content = null;
        try {
            //读取文件
            FileInputStream fis = new FileInputStream(path);
            //fis.available():文件的长度
            byte[] b=new byte[fis.available()];
            //skip:跳过n个字节后再开始读取
            fis.read(b);
            content = new String(b);
            fis.close();
        } catch (FileNotFoundException e) {
            //系统强制解决的问题：文件没有找到
            e.printStackTrace();
        } catch (IOException e) {
            //文件读写异常
            e.printStackTrace();
        }
        return content;
    }

    public static void writeFile(String data, String path){
        File file =new File(path);
        Writer out = null;
        try {
            out = new FileWriter(file);
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static List<File> getFileList(String strPath) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    continue;
                }else{
                    fileList.add(files[i]);
                }
            }

        }
        return fileList;
    }

    public static void main1(String[] args){
        String content = readFile("F:\\coding\\c-course\\src\\main\\resources\\static\\file/start.c");
        System.out.println(content);
    }
}
