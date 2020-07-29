package com.project.wangyuming.core.util;

public class Timer {

    private static Long TIME;

    private Timer(){}

    public static com.project.wangyuming.core.util.Timer start() {
        com.project.wangyuming.core.util.Timer instance = new com.project.wangyuming.core.util.Timer();
        instance.TIME = System.nanoTime();
        return instance;
    }

    public void reset() {
        TIME = System.nanoTime();
    }

    public Long end() {
        return System.nanoTime() - TIME;
    }

}
