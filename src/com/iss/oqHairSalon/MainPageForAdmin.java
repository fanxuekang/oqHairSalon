package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class MainPageForAdmin {
    private static String loginName = "";

    public MainPageForAdmin() {
    }

    public MainPageForAdmin(String loginName) {
        setLoginName(loginName);
    }


    public static void mainPageForAdmin() {

        Frame frame = new Frame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println("请选择您的操作：            ");
        System.out.println("                      1：用户管理");
        System.out.println("                      2：会员管理");
        System.out.println("                      q：关闭系统");
        frame.print();
        System.out.print("请输入您的选择：");
        try {
            String select = scanner.next();
            switch (select) {
                case "1": {
                    UserManagerment userManagerment = new UserManagerment(loginName);
                    userManagerment.userManagerment();
                }
                break;
                case "2": {
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage(loginName);
                }
                break;
                case "q": {
                    do {
                        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                        frame.print();
                        System.out.println("确认关闭系统？\n \t\t\t\t y：是  n：否");
                        System.out.println();
                        frame.print();
                        System.out.print("请输入您的选择：");
                        String outAssuer = scanner.next();
                        if (outAssuer.equals("y")) {
                            return;
                        } else if (outAssuer.equals("n")) {
                            System.out.println("正在返回...");
                            Thread.sleep(1000);
                            MainPageForAdmin mainPageForAdmin = new MainPageForAdmin(loginName);
                            mainPageForAdmin.mainPageForAdmin();
                        } else {
                            System.out.println("您的输入有误，请重新输入");
                            Thread.sleep(1000);
                        }
                    } while (true);
                }
                default: {
                    System.out.println("您的输入有误，请重新输入！");
                    try {           //延时一秒后返回主界面
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage(loginName);
                    }
                }
            }
        } catch (Exception e1) {
            System.out.println("您的输入有误，请重新输入！");
            try {           //延时一秒后返回主界面
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                MainPageForAdmin mainPageForAdmin = new MainPageForAdmin(loginName);
                mainPageForAdmin.mainPageForAdmin();
            }
        }

    }


    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        MainPageForAdmin.loginName = loginName;
    }
}
