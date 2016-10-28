package com.up72.sort.insert;

/**
 * Created by Administrator on 2016/7/20.
 */
public class TestInsertSort {
    public static void main(String[] args) {
        Integer[] unSotrArray = {45, 21, 67, 16, 34, 4, 89, 82, 1, 73, 64};
        InsertSort.shellSort(unSotrArray);
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
    }
}
