package com.iss.oqHairSalon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MembersInfo {
    private static Map<String, Member> members = new HashMap<String, Member>() {{
        put("20190108080120", new Member("20190108080120", "刘英贤", 750, "二级会员", 0.9));
        put("20190208080130", new Member("20190208080130", "张健", 75, "三级会员", 0.95));
        put("20190308080140", new Member("20190308080140", "邴英澳", 2000, "特级会员", 0.8));
        put("20190408080150", new Member("20190408080150", "王继豪", 1000, "一级会员", 0.85));
    }};


    public static void selectMembersInfo(String loginName) throws InterruptedException {
        Frame frame = new Frame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println();
        System.out.println("请输入将要查询的会员账号，输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入账号：");
        String inputId = scanner.next();
        if ("q".equals(inputId)) {
            System.out.println("正在返回上一级...");
            Thread.sleep(1000);
            MainPage mainPage = new MainPage();
            mainPage.mainPage(loginName);
        } else {
            if (!members.containsKey(inputId)) {
                System.out.println("该账号不存在，请重新输入");
                Thread.sleep(1000);
                MembersInfo membersInfo = new MembersInfo();
                membersInfo.selectMembersInfo(loginName);
            } else {
                Member m = new Member();
                for (Map.Entry<String, Member> entry : members.entrySet()) {
                    if (inputId.equals(entry.getKey())) {
                        m.setName(entry.getValue().getName());
                        m.setCost(entry.getValue().getCost());
                        m.setLevel(entry.getValue().getLevel());
                        m.setId(entry.getValue().getId());
                    }
                }
                System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                frame.print();
                System.out.println("该账号的查找信息结果如下：       ");
                System.out.println("\t\t账号" + "\t\t\t" + "姓名" + "\t\t" + "总消费" + "\t\t" + "会员等级");
                System.out.println("\t\t"+m.getId() + "\t" + m.getName() + "\t\t" + m.getCost() + "\t\t" + m.getLevel());
                System.out.println("\t\t\t1:会员消费 2:删除会员 3:修改信息 q:返回上级");
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
                        membersInfo1.deleteMemberById(loginName);
                    }
                    break;
                    case "3": {
                        MembersInfo membersInfo1 = new MembersInfo();
                        membersInfo1.updateMembersInfo(loginName);
                    }
                    break;
                    case "q": {
                        System.out.println("正在返回...");
                        Thread.sleep(1000);
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage(loginName);
                    }break;
                    default: {
                        System.out.println("您的输入有误，请重新输入！");
                            Thread.sleep(1000);
                            MembersInfo membersInfo1 = new MembersInfo();
                            membersInfo1.selectMembersInfo(loginName);
                    }
                }
            }
        }
    }


    public static void updateMembersInfo(String loginName) throws InterruptedException {
        Frame frame = new Frame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println();
        System.out.println("请输入将要修改的会员账号，输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入账号：");
        String inputId = scanner.next();
        if ("q".equals(inputId)) {
            System.out.println("正在返回上一级...");
            Thread.sleep(1000);
            MainPage mainPage = new MainPage();
            mainPage.mainPage(loginName);
        } else {
            if (!members.containsKey(inputId)) {
                System.out.println("该账号不存在，请重新输入");
                Thread.sleep(1000);
                MembersInfo membersInfo = new MembersInfo();
                membersInfo.updateMembersInfo(loginName);
            } else {
                do {
                    System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                    frame.print();
                    System.out.println();
                    System.out.println("请输入新的会员信息，输入q退出编辑：");
                    System.out.println();
                    frame.print();
                    System.out.print("请输入新的姓名：");
                    String newName = scanner.next();
                    if ("q".equals(newName)) {
                        System.out.println("正在退出...");
                        Thread.sleep(1000);
                        updateMembersInfo(loginName);
                    } else {
                        System.out.print("请输入新的消费金额");
                        String newCost = scanner.next();
                        try {
                            double cost = Double.parseDouble(newCost);
                            for (Map.Entry<String, Member> entry : members.entrySet()) {
                                if (inputId.equals(entry.getKey())) {
                                    entry.getValue().setName(newName);
                                    entry.getValue().updateMemberInfoByCost(2, cost);
                                }
                            }
                            System.out.println("更新成功，正在返回...");
                            Thread.sleep(1000);
                            MainPage.mainPage(loginName);
                        } catch (Exception e) {
                            if ("q".equals(newCost)) {
                                System.out.println("正在退出...");
                                Thread.sleep(1000);
                                updateMembersInfo(loginName);
                            } else {
                                System.out.println("您的输入有误，请重新输入");
                                Thread.sleep(1000);
                            }
                        }
                    }
                } while (true);
            }
        }
    }

    public static void addCost(String id, double price) {

        Member m1 = members.get(id);
        m1.updateMemberInfoByCost(1, price);
        members.remove(id);
        members.put(id, m1);

    }


    public MembersInfo() {
    }

    public void deleteMemberById(String loginName) throws InterruptedException {
        Frame frame = new Frame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println();
        System.out.println("请输入将要删除的会员账号，输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入账号：");
        String inputId = scanner.next();
        if ("q".equals(inputId)) {
            System.out.println("正在返回上一级...");
            Thread.sleep(1000);
            MainPage mainPage = new MainPage();
            mainPage.mainPage(loginName);
        } else {
            if (!members.containsKey(inputId)) {
                System.out.println("该账号不存在，请重新输入");
                Thread.sleep(1000);
                MembersInfo membersInfo = new MembersInfo();
                membersInfo.deleteMemberById(loginName);
            } else {
                String select = "";
                do {
                    System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                    frame.print();
                    System.out.println("账号" + inputId + "的信息将会被删除，是否继续?");
                    System.out.println("             1：继续       q：返回");
                    System.out.println();
                    frame.print();
                    System.out.print("请输入您的选择：");
                    select = scanner.next();
                    if ("1".equals(select)) {
                        members.remove(inputId);
                        System.out.println("删除成功，正在返回...");
                        Thread.sleep(1000);
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage(loginName);
                    } else if ("q".equals(select)) {
                        System.out.println("正在返回...");
                        Thread.sleep(1000);
                        MembersInfo membersInfo = new MembersInfo();
                        membersInfo.deleteMemberById(loginName);
                    } else {
                        System.out.println("您的输入有误，请重新输入");
                        Thread.sleep(1000);
                    }
                } while ((!"1".equals(select) && (!"q".equals(select))));
            }

        }
    }

    public void addMember(String loginName) throws InterruptedException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = fmt.format(date);
        double cost = 0;
        String level = "三级会员";
        double bargin = 0.95;
        Frame frame = new Frame();

        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println();
        System.out.println("请输入将要添加的会员姓名，输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入姓名：");
        String inputName = scanner.next();
        if ("q".equals(inputName)) {
            System.out.println("正在返回上一级...");
            Thread.sleep(1000);
            MainPage mainPage = new MainPage();
            mainPage.mainPage(loginName);
        } else {
            String select = "";
            do {
                System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                frame.print();
                System.out.println("\t" + inputName + "的会员账号为：" + id + ",是否添加？");
                System.out.println();
                System.out.println("             1：添加       q：返回");
                frame.print();
                System.out.print("请输入您的选择：");
                select = scanner.next();
                if ("1".equals(select)) {
                    Member newMember = new Member(id, inputName, cost, level, bargin);
                    members.put(id, newMember);
                    System.out.println("添加会员成功，正在返回...");
                    Thread.sleep(1000);
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage(loginName);
                } else if ("q".equals(select)) {
                    System.out.println("正在返回...");
                    Thread.sleep(1000);
                    addMember(loginName);
                } else {
                    System.out.println("您的输入有误，请重新输入");
                    Thread.sleep(1000);
                }
            } while ((!"1".equals(select) && (!"q".equals(select))));
        }
    }

    public static Map<String, Member> getMembersInfo() {//创建4条会员信息
        return members;
    }

    public double selectBarginById(String id) {//返回折扣值
        for (Map.Entry<String, Member> entry : members.entrySet()) {
            if (entry.getKey().equals(id))
                return entry.getValue().getBargin();
        }
        return 0;
    }

    public boolean isMember(String id) {
        return members.containsKey(id);
    }
}
