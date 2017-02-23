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

    public static void exchange(int A[], int i, int j)        // 交换A[i]和A[j]
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int partition(int A[], int left, int right)  // 划分函数
    {
        int pivot = A[right];                    // 选择最后一个元素作为基准
        int tail = left - 1;                     // tail为小于基准的子数组最后一个元素的索引
        for (int i = left; i < right; i++)       // 遍历基准以外的其他元素
        {
            if (A[i] <= pivot)                   // 把小于等于基准的元素放到前一个子数组中
            {
                tail++;
                exchange(A, tail, i);
            }
        }
        exchange(A, tail + 1, right);            // 最后把基准放到前一个子数组的后边,剩下的子数组既是大于基准的子数组
                                                    // 该操作很有可能把后面元素的稳定性打乱,所以快速排序是不稳定的排序算法
        return tail + 1;                         // 返回基准的索引
    }

    public static void quicksort(int A[], int left, int right)
    {
        int pivot_index;                        // 基准的索引
        if (left < right)
        {
            pivot_index = partition(A, left, right);
            quicksort(A, left, pivot_index-1);
            quicksort(A, pivot_index+1, right);
        }

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

    public static void main(String[] args){
        int A[] = { 5, 2, 9, 4, 7, 6, 1, 3, 8 };// 从小到大快速排序
        int n = A.length;
        quicksort(A, 0, n - 1);
    }
}
