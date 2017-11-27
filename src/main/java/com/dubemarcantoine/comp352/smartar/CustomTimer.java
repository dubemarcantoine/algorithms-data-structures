package com.dubemarcantoine.comp352.smartar;

public class CustomTimer {

    private long lastTime;

    public void start() {
        this.lastTime = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - this.lastTime;
    }

}
