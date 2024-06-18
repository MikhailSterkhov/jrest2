package com.jrest.test.model;

import com.jrest.mvc.model.util.HashingUtil;

public class HashingTest {

    public static void main(String[] args) {
        System.out.println(HashingUtil.encodeBase64("password"));
        System.out.println(HashingUtil.encodeSha256("password"));
        System.out.println(HashingUtil.encodeSha512("password"));
    }
}
