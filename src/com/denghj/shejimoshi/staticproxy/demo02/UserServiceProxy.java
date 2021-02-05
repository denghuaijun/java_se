package com.denghj.shejimoshi.staticproxy.demo02;

/**
 * 代理对象，并在除业务代码之后，实现公共业务代码的切入，打印日志
 */
public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        printLog("add");
        userService.add();
    }

    @Override
    public void delete() {
        printLog("delete");
        userService.delete();
    }

    @Override
    public void update() {
        printLog("update");
        userService.update();
    }

    @Override
    public void select() {
        printLog("select");
        userService.select();
    }

    /**
     * 公共业务方法
     */
    public void printLog(String msg){
        System.out.println("日志。。。。"+msg);
    }
}
