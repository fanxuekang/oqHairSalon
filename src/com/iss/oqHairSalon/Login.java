package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class Login {

    public Login(){};

    public static void login() throws InterruptedException {
        UsersInfo usersInfo = new UsersInfo();
        Map<String,User> users = usersInfo.getAllUsers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统           ");
        Frame frame = new Frame();
        frame.print();
        System.out.println();
        System.out.println("欢迎登录,请输入用户名和密码，用户名输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入用户名：");
        String inputName = scanner.next();


        if(inputName.equals("q")){//判断是返回还是继续输入密码
            System.out.println("正在返回上一级...");
            try {
                Thread.sleep(1000);
            }catch (Exception e1){
                e1.printStackTrace();
            }finally {
                OqHairSalon oqHairSalon = new OqHairSalon();
                oqHairSalon.main(new String[]{});
            }
        }else{
            if(usersInfo.isUser(inputName)){
                System.out.print("请输入密码：");
                String inputPassword = scanner.next();
                if(usersInfo.getPwdByName(inputName).equals(inputPassword)){
                    System.out.println("欢迎您！" + inputName);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(inputName.equals("admin")){
                            MainPageForAdmin mainPageForAdmin = new MainPageForAdmin(inputName);
                            mainPageForAdmin.mainPageForAdmin();
                        }else{
                            MainPage mainPage = new MainPage();
                            mainPage.mainPage(inputName);//调用mainPage方法
                        }
                    }
                }else{
                    System.out.println("密码不正确！请重新输入！");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Login login = new Login();
                        login.login();//调用mainPage方法
                    }
                }
            }else{
                System.out.println("无此用户，请重新输入！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Login login = new Login();
                    login.login();//调用mainPage方法
                }
            }
        }
    }
}
