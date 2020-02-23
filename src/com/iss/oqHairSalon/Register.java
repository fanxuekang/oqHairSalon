package com.iss.oqHairSalon;

import java.util.Scanner;

public class Register {
    private String name = "";
    private String password = "";

    public Register() {
    }

    public Register(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void register() throws InterruptedException {
        UsersInfo users = new UsersInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统           ");
        Frame frame = new Frame();
        frame.print();
        System.out.println();
        System.out.println("请输入您的注册信息，用户名输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入用户名：");
        String inputName = scanner.next();

        if (inputName.equals("q")) {       //判断是返回还是继续输入密码
            System.out.println("正在返回上一级...");
            try {
                Thread.sleep(1000);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                OqHairSalon oqHairSalon = new OqHairSalon();
                oqHairSalon.main(new String[]{});
            }
        } else if (users.containsUser(inputName)) {
            System.out.println("该用户已存在，请重新输入！");
            Thread.sleep(1000);
            register();
        } else {
            System.out.print("请输入密码：");
            String inputPassword1 = scanner.next();
            System.out.print("请确认密码：");
            String inputPassword2 = scanner.next();
            if (inputPassword1.equals(inputPassword2)) {
                OqHairSalon oqHairSalon = new OqHairSalon();
                DbManager.addUser(inputName,inputPassword1);
//                User reguser = new User(inputName, inputPassword1);
//                UsersInfo usersInfo = new UsersInfo(inputName, reguser);
                System.out.println("注册成功，请登录");
                try {           //延时一秒后返回主界面
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Login login = new Login();
                    login.login();
                }

            } else {
                System.out.println("两次密码输入不一致，请重新输入！");
                try {           //延时一秒后返回主界面
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    register();
                }
            }
        }

    }
}
