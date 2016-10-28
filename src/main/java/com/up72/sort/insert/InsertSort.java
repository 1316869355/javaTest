package com.up72.sort.insert;

/**
 * Created by Administrator on 2016/7/20.
 * <p>
 * 插入排序
 */
public class InsertSort {

    /**
     * 快速排序
     */
    public static void quickSort(Integer[] numbers) {

    }

    /**
     * 直接插入排序
     */
    public static void insertSort(Integer[] numbers) {
        print(numbers);
        Long sysStartTime = System.currentTimeMillis();
        int len = numbers.length;
        int i, j;
        for (i = 1; i < len; i++) {
            for (j = i - 1; j >= 0 && numbers[j] > numbers[j + 1]; j--) {
                int temp = numbers[j + 1];
                numbers[j + 1] = numbers[j];
                numbers[j] = temp;
            }
            print(numbers);
        }
        Long sysEndTime = System.currentTimeMillis();
        System.out.println("用时" + Math.abs(sysEndTime - sysStartTime));//输出排序用时
        print(numbers);//打印排序后数组
    }

    /**
     * 希尔排序
     */
    public static void shellSort(Integer[] numbers) {
        print(numbers);
        Long sysStartTime = System.currentTimeMillis();
        int len = numbers.length;
        int i, j;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            System.out.println(gap);
            for (i = 0; i <= gap; i++) {

                for (j = i + gap; j < len; j += gap) {
                    if(numbers[j] < numbers[j-gap]) {
                        int temp = numbers[j - gap];
                        numbers[j - gap] = numbers[j];
                        numbers[j] = temp;
                    }
                       /* int temp = numbers[j];
                        int k = j - gap;
                        while (k >= 0 && numbers[k] > temp)
                        {
                            numbers[k + gap] = numbers[k];
                            k -= gap;
                        }
                        numbers[k + gap] = temp;*/
                }
            }

            print(numbers);
        }
        Long sysEndTime = System.currentTimeMillis();
        System.out.println("用时" + Math.abs(sysEndTime - sysStartTime));//输出排序用时
        print(numbers);//打印排序后数组
    }

    /**
     * 冒泡排序
     *
     * @param numbers
     */
    public static void BubbleSort(Integer[] numbers) {
        print(numbers);
        Long sysStartTime = System.currentTimeMillis();
        int len = numbers.length;
        for (int j = len; j > 0; j--) {
            for (int i = 0; i < len - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                }
            }
            print(numbers);
        }
        Long sysEndTime = System.currentTimeMillis();
        System.out.println("用时" + Math.abs(sysEndTime - sysStartTime));//输出排序用时
        print(numbers);//打印排序后数组
    }

    /**
     * 打印数组
     *
     * @param object
     */
    public static void print(Object[] object) {
        String out = "";
        for (int i = 0; i < object.length; i++) {
            out += object[i] + "\t";
        }
        System.out.println(out);
    }
}
