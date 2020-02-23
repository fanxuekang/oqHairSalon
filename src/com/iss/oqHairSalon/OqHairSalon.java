package com.iss.oqHairSalon;

import java.util.Scanner;


public class OqHairSalon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统           ");
        Frame frame = new Frame();
        frame.print();
        System.out.println("\t\t\t\t\t1:登录系统 \n\t\t\t\t\t2:新用户注册");
        frame.print();
        System.out.print("请输入您的选择：");
        try {
            int select1 = scanner.nextInt();
            Login login = new Login();
            if (select1 == 1) {
                login.login();//调用login方法
            } else if (select1 == 2) {
                Register register = new Register();
                register.register();
            } else {
                System.out.println("您的输入有误，请重新输入！");
                Thread.sleep(1000);
                OqHairSalon oqHairSalon = new OqHairSalon();
                oqHairSalon.main(new String[]{});
            }

        } catch (Exception e) {
            System.out.println("您的输入有误，请重新输入！");
            try {
                Thread.sleep(1000);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                OqHairSalon oqHairSalon = new OqHairSalon();
                oqHairSalon.main(new String[]{});
            }
        }
    }
}
