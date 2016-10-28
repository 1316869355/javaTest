package com.up72.pattern;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/15.
 */
public class TestPattern {
    public static void main(String[] args){
        Pattern ptn = Pattern.compile("@(\\S+?)\\s");
        Pattern ptn2 = Pattern.compile("\\s@(\\S+?)\\s");
        Set<String> atFriendSet = new HashSet<String>();
        String con = "posdkf急急急急急急就看看@sdsdf @234df @哈哈1 @哈哈2 sdf";
//        Matcher m  = con.matcher("\\s@(\\S+?)\\s");
        String temp = con.replace("@", " @") + " ";//获取评论中@的人
        Matcher matcher = ptn.matcher(temp);
//        con =  "@测试008  @蒙特罗 恩";
//        Matcher matcher = ptn.matcher(con);
//        while (matcher.find()){
//            System.out.println(matcher.group());
//        }
        String content ="ces 测试测试</p><p><br/></p><p><img src=\"/ueditor/jsp/upload/image/20161020/1476955591708035449.jpg\" title=\"\" alt=\"u3558.jpg\"/></p><p><br/></p><p>测试第二行</p><p><img src=\"/ueditor/jsp/upload/image/20161020/1476955591715035663.jpg\" style=\"\"/></p><p><img src=\"/ueditor/jsp/upload/image/20161020/1476955591722049129.jpg\" style=\"\"/></p><p><img src=\"/ueditor/jsp/upload/image/20161020/1476955591727028811.jpg\" style=\"\"/></p><p>第三行</p><p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/upload/20161020/uKISnfolEi5Wi6wF.wmv\" data-setup=\"{}\"><source src=\"/upload/20161020/uKISnfolEi5Wi6wF.wmv\" type=\"video/wmv\"/></video>结尾结尾结尾，终于！！！！";
        content = content.replaceAll("","");
        content = content.replaceAll("<(?!img|video)(.*?>|.*?video>)|(data-setup=\"\\{.?\\}\")", "");
        List<Map<String,Object>> temp1 = getContentList(content);
        for(Map map: temp1){
            Set keys = map.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()){
                String key = (String)iterator.next();
                System.out.println( key + ":"+ map.get(key));
            }
        }
    }

    /**
     * 截取文字，图片，视频
     * @param model
     * @return
     */
    public static List getContentList(String model){
        //截取
        model = model.replaceAll("\\{|\\}","");
        List<Map> list = new ArrayList<Map>();
        Pattern pattern = Pattern.compile("<(img|video).*?src=\"(\\S+?)\"(.*?>|.*?video>)");
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
                map.put("width",null);
            }
            Matcher heightMatcher = Pattern.compile("height=\"(\\d+)\"").matcher(img);
            if(heightMatcher.find()) {
                map.put("height",heightMatcher.group(1));
            }else{
                //默认
                map.put("height",null);
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
}
