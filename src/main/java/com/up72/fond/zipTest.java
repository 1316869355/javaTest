package com.up72.fond;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2016/12/1.
 */
public class zipTest {

    public static String deCompressFile(String file){
        ArrayList<String> allFileName = new ArrayList<String>();
        File zipFile = new File(file);
        String deCompressFilePath = file.substring(0, file.lastIndexOf("/")+1);
        try {
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zins = new ZipInputStream(fis);
            ZipEntry ze = null;
            byte[] ch = new byte[1024];
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(deCompressFilePath + ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if (ze.isDirectory()) {
                    if (!zfile.exists())
                        zfile.mkdirs();
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    FileOutputStream fouts = new FileOutputStream(zfile);
                    int i;
                    allFileName.add(zfile.getAbsolutePath());
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
            }
            fis.close();
            zins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件");
    } catch (IOException e) {
        e.printStackTrace();
    }
        return zipFile.getAbsolutePath();
    }

    public static void main(String[] args){
        String path = "e:/360Downloads/maven.zip";
        deCompressFile(path);
    }
}
