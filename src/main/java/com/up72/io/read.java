package com.up72.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/20.
 */
public class read {
    private static final List<String> FORBIDWORD = new ArrayList<String>();

    public static void readLoadForbidWord() {
        File forbidFile = new File("forbid-word.txt");
        if (forbidFile.exists()) {
            FileInputStream in = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            try {
                in = new FileInputStream(forbidFile);
                inputStreamReader = new InputStreamReader(in, "UTF-8");
                reader = new BufferedReader(inputStreamReader);
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (line.contains(",") && line.lastIndexOf(",") == line.length() - 1) {
                        FORBIDWORD.add(line.substring(0, line.length() - 1));
                    } else {
                        FORBIDWORD.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("找不到违禁词文件");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("读取违禁词文件失败");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("关闭流失败");
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("关闭流失败");
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("关闭文件流失败");
                    }
                }
            }
        }
    }

    public static void reWriteForbidWord(List<String> dbWords) throws IOException {
//        FORBIDWORD = dbWords;
        String path = read.class.getResource("/").getFile().replaceAll("\\\\", "/");
//        String path = "D:/ws/testjava/";
        System.out.println(path);
        path += "forbidWord.txt";
        File forbidFile = new File(path);
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(forbidFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (String word : dbWords) {
//            bufferedWriter.write(word);
                bufferedWriter.write(word);
                bufferedWriter.newLine();
            }
/*        try {
            StringBuilder sb = new StringBuilder();
            for (String word : dbWords) {
                sb.append(word + ",");
                sb.append(System.getProperty("line.separator"));
            }
                Files.write(sb, new File(path), Charsets.UTF_8);
            }catch(IOException e)    {
                e.printStackTrace();
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("没有找到违禁词");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写出出错");
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            words.add("没有找" + i);
        }
        reWriteForbidWord(words);
    }
}
