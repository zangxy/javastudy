package com.zoey.javastudy.juc;

import java.io.IOException;

public class ThisEscape {
    private int num = 8;

    public ThisEscape() {
        new Thread(() -> System.out.println(this.num)).start();
    }

    public static void main(String[] args) throws IOException {
        new ThisEscape();
        System.in.read();
    }

}
