package com.facedamon.smart;

public class Main {

    public static void main(String[] args) {
        String seq = "100017";

        if (!"".equals(seq) && seq.length() < 10){
            System.out.println(String.format("%010d",Integer.valueOf(seq)));
        }
    }

}
