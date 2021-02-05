package com.denghj.shejimoshi.staticproxy.demo01;

/**
 * 代理中介
 */
public class Proxy implements Hose {
    private LandLord landLord;

    public Proxy(LandLord landLord) {
        this.landLord = landLord;
    }

    @Override
    public void rent() {
        hetong();
        landLord.rent();
        giveKey();
        fare();
    }

    public  void  hetong(){
        System.out.println("签租赁合同");
    }
    public void giveKey(){
        System.out.println("给予钥匙");
    }
    public void fare(){
        System.out.println("收取中介费");
    }
}
