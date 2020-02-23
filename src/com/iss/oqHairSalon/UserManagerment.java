package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class UserManagerment {
    private static String loginName;

    public UserManagerment() {}

    public UserManagerment(String loginName) {
        setLoginName(loginName);

    }
    public void userManagerment() {
        Frame frame = new Frame();
        UsersInfo usersInfo = new UsersInfo();
        Map<String, User> users = usersInfo.getAllUsers();
        Scanner scanner = new Scanner(System.in);


        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println("当前所有用户信息如下：");
        System.out.println("            用户名                 密码");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if(!entry.getKey().equals("admin"))
            System.out.println("\t\t\t" + entry.getKey() + "\t\t\t\t\t" + entry.getValue().getLoginPassword());
        }
        System.out.println("\t\t1：删除用户\t2：修改信息\tq：返回上级");
        frame.print();
        System.out.print("请输入您的选择：");
        String select = scanner.next();
        switch (select) {
            case "1": {
                usersInfo.managerUsersInfo(loginName,select);
            }
            break;
            case "2": {
                usersInfo.managerUsersInfo(loginName,select);
            }
            break;
            case "q": {
                System.out.println("正在返回上一级...");
                try {
                    Thread.sleep(1000);
                }catch (Exception e1){
                    e1.printStackTrace();
                }finally {
                   MainPageForAdmin mainPageForAdmin = new MainPageForAdmin(loginName);
                   mainPageForAdmin.mainPageForAdmin();
                }
            }
            break;
            default: {
                System.out.println("您的输入有误，请重新输入！");
                try {           //延时一秒后返回主界面
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    UserManagerment userManagerment = new UserManagerment(loginName);
                    userManagerment.userManagerment();
                }
            }
        }
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        UserManagerment.loginName = loginName;
    }
}
