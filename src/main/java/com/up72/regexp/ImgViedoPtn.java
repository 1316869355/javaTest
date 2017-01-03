package com.up72.regexp;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/28.
 */
public class ImgViedoPtn {

    //获取文档中的图片路径
    public static String getImages(String content){
        String images = "";
        //查询图片
        Pattern imgPattern = Pattern.compile("<img .*?src=\"(\\S+?)\"");
        Matcher imgMatcher = imgPattern.matcher(content);
        while (imgMatcher.find()) {
            images = images + imgMatcher.group(1);
            images = images + ";";
        }
        if(StringUtils.isNotEmpty(images)){
            images = images.substring(0, images.length() - 1);
        }
        return images;
    }
    //获取文档中的视频路径
    public static String getVideos(String content){
        String videos = "";
        //查询视频
        Pattern videoPattern = Pattern.compile("<video .*?src=\"(\\S+?)\"");
        Matcher videoMatcher = videoPattern.matcher(content);
        while (videoMatcher.find()) {
            videos = videos + videoMatcher.group(1);
            videos = videos + ";";
        }
        if(StringUtils.isNotEmpty(videos)){
            videos = videos.substring(0, videos.length() - 1);
        }
        return videos;
    }

    public static List getContentList(String model){
        //截取
        model = model.replaceAll("\\{|\\}","");
        List<Map> list = new ArrayList<Map>();
        Pattern pattern = Pattern.compile("<(img|video).*?src=\"(\\S+?)\".*?>");
        Matcher matcher = pattern.matcher(model);
        while (matcher.find()){
            Map map = new HashMap();
            String img = matcher.group();
            String type = matcher.group(1);
            String url = matcher.group(2);
            int start = matcher.start();
            String[] strs =  model.split(img);
            if(start > 0){
                Map textMap = new HashMap();
                textMap.put("content",strs[0]);
                textMap.put("type",1);
                textMap.put("height",null);
                textMap.put("width",null);
                list.add(textMap);
            }

            map.put("content",url);
            if(type.contains("img")){
                map.put("type",2);
            }else{
                map.put("type",3);
            }
            Matcher widthMatcher = Pattern.compile("width=\"(\\d+)\"").matcher(img);
            if(widthMatcher.find()){
                map.put("width",widthMatcher.group(1));
            }else{
                //默认
                map.put("width",200);
            }
            Matcher heightMatcher = Pattern.compile("height=\"(\\d+)\"").matcher(img);
            if(heightMatcher.find()) {
                map.put("height",heightMatcher.group(1));
            }else{
                //默认
                map.put("height",200);
            }
            list.add(map);

            if(strs.length > 1){
                model = strs[1];
                matcher = pattern.matcher(model);
            }else{
                model = "";
                break;
            }
        }
        if(model.length() > 0){
            Map textMap = new HashMap();
            textMap.put("content",model);
            textMap.put("type",1);
            textMap.put("height",null);
            textMap.put("width",null);
            list.add(textMap);
        }
        return list;
    }

    public static void main(String[] args){
        String content = "<p>ces 测试测试</p><img src=\"/ueditor/jsp/upload/image/20161020/1476955508955072496.jpg\" title=\"\" alt=\"u3558.jpg\"/>测试第二行<img src=\"/ueditor/jsp/upload/image/20161020/1476955508983054245.jpg\" style=\"\"/><img src=\"/ueditor/jsp/upload/image/20161020/1476955509010039562.jpg\" style=\"\"/><p><img src=\"/ueditor/jsp/upload/image/20161020/1476955509013098720.jpg\" style=\"\"/>第三行<video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/upload/20161020/uKISnfolEi5Wi6wF.wmv\"><source src=\"/upload/20161020/uKISnfolEi5Wi6wF.wmv\" type=\"video/wmv\"/></video>结尾结尾结尾，终于</p><p>！！！！</p>";

//        content = content.replaceAll("<(?!img|video)[.*?|.*?video]>|(data-setup=\"\\{.?\\}\")", "");
        content = content.replaceAll("<(?!img|video)(.*?|.*?video)>|(data-setup=\"\\{.?\\}\")", "");
//        content = content.replaceAll("<p.*?>(.*?)<\\/video>", "");
//        content = content.replaceAll("<source.*?>", "");
        System.out.println(content);
//        System.out.println(getVideos(content));

        List<Map<String, Object>> test = getContentList(content);

        for(Map<String, Object> map: test){
            System.out.println(map.get("type"));
            System.out.println(map.get("content"));

            System.out.println();
        }
    }
}
