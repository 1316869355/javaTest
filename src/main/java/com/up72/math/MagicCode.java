package com.up72.math;

/**
 * Created by Administrator on 2016/10/11.
 */
public class MagicCode {
    public static float invSqrt(float x){
        float xhalf = 0.5f*x;
        int i = (int)x; // get bits for floating VALUE
        i = 0x5f375a86 - (i>>1); // gives initial guess y0
        x = (float)i; // convert bits BACK to float
        x = x*(1.5f-xhalf*x*x); // Newton step, repeating increases accuracy
        return x;
    }

    public static double sysSqrt(float x){
        double a = x;
        return Math.sqrt(a);
    }

    public static void main(String[] args){
        Long stMills = System.currentTimeMillis();
        float x = 2f;
        System.out.print("magic cal "+ invSqrt(x));

        System.out.println();

        System.out.print("sys cal "+ sysSqrt(x));
    }
}
