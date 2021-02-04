package com.denghj.shejimoshi.adapter.demo02;

public class Usb {

    public void conn(IAdapter adapter){
        adapter.transfer();
    }

    public static void main(String[] args) {
        Intenet intenet = new Intenet();
        IAdapter adapter = new Adapater();
        Usb usb = new Usb();
        usb.conn(adapter);
    }
}
