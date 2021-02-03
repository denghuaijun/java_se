package com.denghj.shejimoshi.builder.demo01;

/**
 * 房子对象，要构建的对象
 */
public class Home {
    private String homeType;
    private String address;
    private String pay;

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Home{" +
                "homeType='" + homeType + '\'' +
                ", address='" + address + '\'' +
                ", pay='" + pay + '\'' +
                '}';
    }
}
