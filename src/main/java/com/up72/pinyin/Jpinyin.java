package com.up72.pinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.up72.model.Person;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Jpinyin {

    public List<Person> personList = new ArrayList<Person>();
    public List<Map<Character, List>> sortedPerson = new ArrayList<Map<Character, List>>();

    public void initList() {
        String[] names = new String[]{"?lk0","周杰伦", "赵宝刚", "王宝强", "孙红雷","*kkni","&jh","1kd", "安捷", "巴金", "陈凯歌", "邓小平", "鄂佳佳", "范文", "姜维", "刘备", "孙权", "曹操", "关羽", "祁连", "莫文蔚"};
        for (String name : names) {
            personList.add(new Person(name));
        }
    }

    /**
     * 遍历要存的集合
     */
    public void sortedPerson() {
        String temp = "";
        for (Person p : personList) {
            try {
                temp = PinyinHelper.getShortPinyin(p.getName());
                char firstChar = temp.charAt(0);
                putPersonToLM(firstChar, p);
            } catch (PinyinException e) {
                e.printStackTrace();
            }
        }
        print(sortedPerson);
    }

    /**
     * 添加到集合中，并排序
     * @param c
     * @param p
     */
    public void putPersonToLM(char c, Person p) {
        boolean isContain = false;
        int insertIndex = 0;//插入下标
        Map<Character, List> mpl = new HashMap<Character, List>();
        List newPl = new ArrayList<Person>();
        if ((int) c >= 97 && (int) c <= 122) {
            for (int i = 0; i < sortedPerson.size(); i++) {
                Map map = sortedPerson.get(i);
                if (map.containsKey(c)) {
                    mpl = map;
                    isContain = true;
                    insertIndex = i;
                    break;
                } else {
                    Iterator<Character> it = map.keySet().iterator();
                    if (it.hasNext() && it.next() > c) {
                        insertIndex = i;
                        break;
                    } else {
                        insertIndex = i + 1;
                    }
                }
            }
            if(isContain){//已存在相应的key
                sortedPerson.get(insertIndex).get(c).add(p);
            } else {
                newPl.add(p);
                mpl.put(c, newPl);
                sortedPerson.add(insertIndex, mpl);
            }
        } else {
            //首字母不是a-z的，存入到‘0’map为
            if(sortedPerson.size()>0){
                sortedPerson.get(0).get('0').add(p);
            }else{
                newPl.add(p);
                mpl.put('0', newPl);
                sortedPerson.add(0, mpl);
            }
        }

    }

    public void print(List<Map<Character, List>> sorted) {
        for (Map<Character, List> d : sorted) {
            for (Character c : d.keySet()) {
                System.out.println(d.keySet().toString() + ", size:" + d.get(c).size());
            }
        }
    }

    public static void testPerint(String str) {
        String pinyin = "";
        try {
            /*pinyin = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
            System.out.println(pinyin);
            pinyin = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
            System.out.println(pinyin);
            pinyin = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE); // ni,hao,shi,jie
            System.out.println(pinyin);*/
            pinyin = PinyinHelper.getShortPinyin(str); // nhsj
            System.out.println(pinyin.toLowerCase().charAt(0));
            System.out.println(pinyin.toLowerCase().charAt(0) * 1);

//            PinyinHelper.addPinyinDict("user.dict");  // 添加用户自定义字典
        } catch (PinyinException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Jpinyin t = new Jpinyin();
        t.initList(); //初始化
        t.sortedPerson();
    }
}
