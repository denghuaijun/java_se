package com.denghj.shejimoshi.adapter.demo01;

public class Usb {

    public void conn(IAdapter adapter){
        adapter.transfer();
    }

    public static void main(String[] args) {
        Intenet intenet = new Intenet();
        IAdapter adapter = new Adapater(intenet);
        Usb usb = new Usb();
        usb.conn(adapter);
    }
}
