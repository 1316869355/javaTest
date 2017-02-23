package com.up72.sort.insert;

/**
 * Created by Administrator on 2016/7/20.
 */
public class TestInsertSort {
    public static void main(String[] args) {
        int[] unSotrArray = {45, 21, 67, 16, 34, 4, 89, 82, 1, 73, 64};
//        InsertSort.shellSort(unSotrArray);
//        InsertSort.insertSort(unSotrArray);
        /*    for (i = gap; i < len; i++) {
        for (j = i - gap; j >= 0; j -= gap) {
            if (numbers[j+gap] < numbers[j]) {
                int temp = numbers[j + gap];
                numbers[j + gap] = numbers[j];
                numbers[j] = temp;
            }
        }
    }*/
        System.out.println("排序前：");
        printArr(unSotrArray);

//        bubbleSort(unSotrArray); //冒泡排序
        inserSort(unSotrArray); //插入排序

        System.out.println("排序后：");
        printArr(unSotrArray);
    }

    public static void bubbleSort(int[] arr){
        for (int j = 0; j < arr.length; j++)
        for(int i = 1, swp=0; i < arr.length; i++){
            int ahead = i-1;
            if(arr[i] < arr[ahead]){
                swp = arr[ahead];
                arr[ahead] = arr[i];
                arr[i] = swp;
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void inserSort(int[] arr){
        for (int j = 1; j < arr.length; j++)
        for(int i = j, swp=0; i >= 1; i--) {
            int ahead = i-1;
            if(arr[i] < arr[ahead]){
                swp = arr[i];
                arr[i] = arr[ahead];
                arr[ahead] = swp;
            }
        }
    }

    public static void printArr(int[] arr){
        for (int j = 0; j < arr.length; j++)
            System.out.print(arr[j] + "\t");

        System.out.println();
    }
}
