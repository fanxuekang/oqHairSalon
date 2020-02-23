package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class MainPage {

    public MainPage() {
    }


    public static void mainPage(String loginName) throws InterruptedException {
        MembersInfo membersInfo = new MembersInfo();
        Map<String, Member> members = membersInfo.getMembersInfo();
        Scanner scanner = new Scanner(System.in);
        Frame frame = new Frame();

        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println("当前所有会员信息如下：");
        System.out.println("\t\t账号" + "\t\t\t" + "姓名" + "\t\t" + "总消费" + "\t\t" + "会员等级");
        for (Map.Entry<String, Member> entry : members.entrySet()) {
            System.out.println("\t\t"+entry.getKey() + "\t" + entry.getValue().getName() +
                    "\t\t" + entry.getValue().getCost() + "\t\t" + entry.getValue().getLevel());
        }

        if ("admin".equals(loginName)) {
            System.out.println("1:会员消费 2:添加会员 3:删除会员 4:修改信息 5:查找信息 q:返回上级");
        } else {
            System.out.println("1:会员消费 2:添加会员 3:删除会员 4:修改信息 5:查找信息 q:关闭系统");
        }
        frame.print();
        System.out.print("请输入您的选择：");
        String select = scanner.next();
        switch (select) {
            case "1": {
                MemberConsumption memberConsumption = new MemberConsumption(loginName);
                memberConsumption.memberConsumption();
            }
            break;
            case "2": {
                MembersInfo membersInfo1 = new MembersInfo();
                membersInfo1.addMember(loginName);
            }
            break;
            case "3": {
                MembersInfo membersInfo1 = new MembersInfo();
                membersInfo1.deleteMemberById(loginName);
            }
            break;
            case "4": {
                MembersInfo membersInfo1 = new MembersInfo();
                membersInfo1.updateMembersInfo(loginName);
            }
            break;
            case "5": {
                MembersInfo membersInfo1 = new MembersInfo();
                membersInfo1.selectMembersInfo(loginName);
            }
            break;
            case "q": {
                if (!"admin".equals(loginName)) {
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
                            MainPage mainPage = new MainPage();
                            mainPage.mainPage(loginName);
                        } else {
                            System.out.println("您的输入有误，请重新输入");
                            Thread.sleep(1000);
                        }
                    } while (true);
                } else {
                    MainPageForAdmin.mainPageForAdmin();
                }
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
    }

}
