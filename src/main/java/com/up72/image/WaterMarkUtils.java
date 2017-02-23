package com.up72.image;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @use java给图片添加水印文字
 * @ProjectName stuff
 * @Author <a href="mailto:mhmyqn@qq.com">mumaoqiang</a></br>
 * @Date 2012-7-19 下午10:27:54 </br>
 * @FullName com.mmq.watermark.WaterMarkUtils.java </br>
 * @JDK 1.6.0 </br>
 * @Version 1.0 </br>
 */
public class WaterMarkUtils {
    /**
     * 图片添加水印
     *
     * @param srcImgPath       需要添加水印的图片的路径
     * @param outImgPath       添加水印后图片输出路径
     * @param markContentColor 水印文字的颜色
     * @param waterMarkContent 水印的文字
     */
    public void mark(String srcImgPath, String outImgPath, Color markContentColor, String waterMarkContent) {
        try {
//            Color markContentColor = new Color(99, 99, 99);
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);
            Image srcImg = ImageIO.read(srcImgFile);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            //加上空白高度
            int srcImgHeightAdded = 38;

            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight + srcImgHeightAdded, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, srcImgWidth, srcImgHeight);

            g.drawImage(srcImg, 0, srcImgHeightAdded, srcImgWidth, srcImgHeight, null);

            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//抗锯齿

            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            g.drawString(waterMarkContent, 15, 25);
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = null;
            if (createFile(outImgPath)) {
                outImgStream = new FileOutputStream(outImgPath);
                ImageIO.write(bufImg, "jpg", outImgStream);
            }
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件路径
     *
     */
    public static boolean createFile(String filePath){
        boolean result = true;
        try {
            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        result = file.getParentFile().mkdirs();
                    }
                    if (result) {
                        result = file.createNewFile();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent 水印的文字
     * @param g
     * @return 水印文字总长度
     */
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args) {
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        new WaterMarkUtils().mark("F:/RD/field_card.jpg", "F:/RD/afterWatermark_X15_Y-30.jpg", new Color(99, 99, 99), "我的球场卡片");
    }
}
