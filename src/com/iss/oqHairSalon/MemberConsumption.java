package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class MemberConsumption {

    private static Map<String, Member> members;
    private static String  loginName;

    public MemberConsumption(){}
    public MemberConsumption(String loginName){
        setLoginName(loginName);
    }


    public static void memberConsumption() throws  InterruptedException{
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " +loginName);
        Frame frame = new Frame();
        frame.print();
        System.out.println();
        System.out.println("请输入会员账号,输入“q”返回上一级");
        System.out.println();
        frame.print();
        System.out.print("请输入会员账号：");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        if(id.equals("q")){//判断是返回还是继续输入密码
            System.out.println("正在返回上一级...");
                Thread.sleep(1000);
                MainPage mainPage = new MainPage();
                mainPage.mainPage(loginName);
        }else{
            MembersInfo membersInfo = new MembersInfo();
            if(membersInfo.isMember(id)){ //如果是会员
                ServiceSelect serviceSelect = new ServiceSelect(id,loginName);
                serviceSelect.serviceSelect();
            }else {
                System.out.println("账号不存在，请重新输入！");
                    Thread.sleep(1000);
                    memberConsumption();
            }
        }
    }

    public static Map<String, Member> getMembers() {
        return members;
    }

    public static void setMembers(Map<String, Member> members) {
        MemberConsumption.members = members;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
