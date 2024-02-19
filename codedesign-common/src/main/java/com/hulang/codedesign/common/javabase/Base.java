package com.hulang.codedesign.common.javabase;

/**
 * @description: Base
 * @date: 2024/2/19 22:03
 * @author: HuLang
 * @version: V1.0
 **/
public class Base {
    public static void main(String[] args) {
        final int[] iArr = {1, 2, 3, 4, 5};
        iArr[2] = 10;
        // 遍历数据
        for (int i = 0; i < iArr.length; i++) {
            System.out.println("iArr[" + i + "] = " + iArr[i]);
        }

    }
}

