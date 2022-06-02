package com.zoey.javastudy.designpattern.creationalpattern;

/**
 * 懒汉式，线程不安全，仅适应于单线程工作
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(10);
            instance = new Singleton();
        }
        return instance;
    }

    public void getMessage() {
        System.out.println("饿汉式-线程不安全：" + instance.hashCode());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Singleton.getInstance().getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}