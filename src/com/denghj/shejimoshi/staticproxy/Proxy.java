package com.denghj.shejimoshi.staticproxy;

import java.sql.SQLOutput;

/**
 * @author dhj
 * @Description
 * @Date Create in 16:28 2019/8/21
 */
public class Proxy implements Hose {

    private XiaoMing xiaoMing;
    public Proxy(XiaoMing xiaoMing){
        this.xiaoMing = xiaoMing;
    }

    @Override
    public void mai() {
        System.out.println("我是中介代理准备给小明买房子开始");
        xiaoMing.mai();
        System.out.println("我是中介代理给小明已经买完房子了");
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new XiaoMing());
        proxy.mai();
    }
}
