package com.luwei.supermarket.util;

public class UtilDemo {

    public static void main(String[] args) {
        IDGenerator idGenerator = new IDGenerator();
        System.err.println(idGenerator.generateNo(IDGenerator.Type.IMPORT_ORDER_NO));

    }
}
