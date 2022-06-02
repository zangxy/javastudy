package com.zoey.javastudy.designpattern.creationalpattern;

/**
 * 单例模式
 */
public class SingletonPattern {
    public static void main(String[] args) {
        //1-1、懒汉式(线程不安全,仅适应于单线程工作)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    LazyAndUnsafeSingleton.getInstance().getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //1-2、懒汉式(线程安全，同步方法)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    LazyAndSyncMethodSingleton.getInstance().getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //1-3、懒汉式(线程安全，同步代码块)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    LazyAndSyncBlockSingleton.getInstance().getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //1-4、饿汉式(静态常量)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                EagerAndStaticConstSingleton.getInstance().getMessage();
            }).start();
        }

        //1-5、饿汉式（静态代码块）
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                EagerAndStaticBlockSingleton.getInstance().getMessage();
            }).start();
        }

        //1-6、双重检查
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DoubleCheckLockSingleton.getInstance().getMessage();
            }).start();
        }


        //1-7、静态内部类
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                StaticInnerClassSingleton.getInstance().getMessage();
            }).start();
        }


    }
}


/**
 * 1-1、懒汉式(线程不安全,仅适应于单线程工作)
 */
class LazyAndUnsafeSingleton {
    //static修饰，保障其能够被静态方法访问
    private static LazyAndUnsafeSingleton instance;

    //构造器私有化，避免外部创建对象
    private LazyAndUnsafeSingleton() {
    }

    //外部直接调用静态方法实例化对象
    public static LazyAndUnsafeSingleton getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(10);
            instance = new LazyAndUnsafeSingleton();
        }
        return instance;
    }

    public void getMessage() {
        System.out.println("1-1、懒汉式(线程不安全,仅适应于单线程工作)：" + instance.hashCode());
    }
}


/**
 * 1-2、懒汉式(线程安全，同步方法)
 */
class LazyAndSyncMethodSingleton {
    //static修饰，保障其能够被静态方法访问
    private static LazyAndSyncMethodSingleton instance;

    //构造器私有化，避免外部创建对象
    private LazyAndSyncMethodSingleton() {
    }

    //外部直接调用静态方法实例化对象
    public static synchronized LazyAndSyncMethodSingleton getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(10);
            instance = new LazyAndSyncMethodSingleton();
        }
        return instance;
    }

    public void getMessage() {
        System.out.println("1-2、懒汉式(线程安全，同步方法)：" + instance.hashCode());
    }
}


/**
 * 1-3、懒汉式(线程安全，同步代码块)
 */
class LazyAndSyncBlockSingleton {
    //static修饰，保障其能够被静态方法访问
    private static LazyAndSyncBlockSingleton instance;

    //构造器私有化，避免外部创建对象
    private LazyAndSyncBlockSingleton() {
    }

    //外部直接调用静态方法实例化对象
    public static LazyAndSyncBlockSingleton getInstance() throws InterruptedException {
        synchronized (LazyAndSyncBlockSingleton.class) {
            if (instance == null) {
                Thread.sleep(10);
                instance = new LazyAndSyncBlockSingleton();
            }
        }
        return instance;
    }

    public void getMessage() {
        System.out.println("1-3、懒汉式(线程安全，同步代码块)：" + instance.hashCode());
    }
}


/**
 * 1-4、饿汉式(静态常量)
 */
class EagerAndStaticConstSingleton {
    //static修饰，保障其能够被静态方法访问
    private static EagerAndStaticConstSingleton instance = new EagerAndStaticConstSingleton();

    //构造器私有化，避免外部创建对象
    private EagerAndStaticConstSingleton() {
    }

    //外部直接调用静态方法实例化对象
    public static EagerAndStaticConstSingleton getInstance() {
        return instance;
    }

    public void getMessage() {
        System.out.println("1-4、饿汉式(静态常量)：" + instance.hashCode());
    }
}


/**
 * 1-5、饿汉式（静态代码块）
 */
class EagerAndStaticBlockSingleton {
    //static修饰，保障其能够被静态方法访问
    private static EagerAndStaticBlockSingleton instance;

    //构造器私有化，避免外部创建对象
    private EagerAndStaticBlockSingleton() {
    }

    //静态代码块生产实例
    static {
        instance = new EagerAndStaticBlockSingleton();
    }

    //外部直接调用静态方法实例化对象
    public static EagerAndStaticBlockSingleton getInstance() {
        return instance;
    }

    public void getMessage() {
        System.out.println("1-5、饿汉式（静态代码块）：" + instance.hashCode());
    }
}


/**
 * 1-6、双重检查
 */
class DoubleCheckLockSingleton {
    //static修饰，保障其能够被静态方法访问
    private static DoubleCheckLockSingleton instance;

    //构造器私有化，避免外部创建对象
    private DoubleCheckLockSingleton() {
    }

    //外部直接调用静态方法实例化对象
    public static DoubleCheckLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }

    public void getMessage() {
        System.out.println("1-6、双重检查：" + instance.hashCode());
    }
}


/**
 * 1-7、静态内部类
 */
class StaticInnerClassSingleton {

    //构造器私有化，避免外部创建对象
    private StaticInnerClassSingleton() {
    }

    private static class StaticInnerClassInstance {
        //static修饰，保障其能够被静态方法访问
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();

    }

    //外部直接调用静态方法实例化对象
    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassInstance.instance;
    }

    public void getMessage() {
        System.out.println("1-7、静态内部类：" + StaticInnerClassSingleton.getInstance().hashCode());
    }
}