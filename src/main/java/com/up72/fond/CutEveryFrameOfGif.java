package com.up72.fond;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/9/1.
 */
public class CutEveryFrameOfGif {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        try{
            GifDecoder gd = new GifDecoder();
            //要处理的图片
            int status = gd.read(new FileInputStream(new File("E:/testImageDeal/ec84.gif")));
            if (status != GifDecoder.STATUS_OK) {
                return;
            }
            //

            AnimatedGifEncoder ge = new AnimatedGifEncoder();

            //这里是关键，设置要替换成透明的颜色
            ge.setTransparent(Color.WHITE);
            //
            ge.start(new FileOutputStream(new File("E:/testImageDeal/out_04.gif")));
            ge.setRepeat(0);

            for (int i = 0; i < gd.getFrameCount(); i++) {
                //取得gif的每一帧
                BufferedImage frame = gd.getFrame(i);
                //你可以对每一帧做点什么，比如缩放什么的，这里就什么都不做了

                int width = frame.getWidth();
                int height = frame.getHeight();
                int delay = gd.getDelay(i);
                ge.setDelay(delay);
                ge.addFrame(frame);

            }
            //输出图片
            ge.finish();

            System.out.println(System.currentTimeMillis()-startTime);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
