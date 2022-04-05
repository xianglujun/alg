package com.learn.lang;

/**
 * 该实例用于实验父线程与子线程的通信方式, 以及如何通过ThreadLocal进行数据公兴
 */
public class InheritThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        // 父线程插入数据
        Thread parent = new Thread("父线程") {
            @Override
            public void run() {
                local.set("父线程内容");
                inheritableThreadLocal.set("可继承的变量信息...");
                Thread child = new Thread("子线程") {
                    @Override
                    public void run() {
                        String r = local.get();
                        System.out.println("子线程读取父线程内容: " + r);
                        System.out.println("子线程读取可继承变量: " + inheritableThreadLocal.get());

                        inheritableThreadLocal.set("子线程内容");
                    }
                };

                child.start();
                try {
                    child.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + inheritableThreadLocal.get());
            }
        };
        parent.start();
    }
}
