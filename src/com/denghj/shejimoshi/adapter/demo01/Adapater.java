package com.denghj.shejimoshi.adapter.demo01;

public class Adapater implements IAdapter {
    private Intenet intenet;

    public Adapater(Intenet intenet) {
        this.intenet = intenet;
    }

    @Override
    public void transfer() {
        intenet.connect();
    }
}
