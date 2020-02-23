package com.iss.oqHairSalon;

import java.util.Map;
import java.util.Scanner;

public class ServiceSelect {
    private static String id;
    private static String loginName;
    public ServiceSelect(String id,String loginName){
        setId(id);
        setLoginName(loginName);
    }

    public void serviceSelect(){
        Frame f = new Frame();
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " +loginName);
        f.print();
        System.out.println("请选择服务项目");
        System.out.println("1.剪发-30元    2.洗头-10元    3.刮脸-30元    4.吹风-5元");
        System.out.println("5.烫发-200元    6.做发-100元    7.染发-150元    8.假发-200元");
        System.out.println("q.返回上一级");
        f.print();

        double[] prices = {30,10,30,5,200,100,150,200};

        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print("请输入服务项目编号，以空格分隔：");
            String inputSelect = scanner.nextLine();
            if(inputSelect.equals("q")){
                System.out.println("正在返回...");
                try{           //延时一秒后返回主界面
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    MainPage mainPage = new MainPage();
                    mainPage.mainPage(loginName);
                }
            }else{
                String[] select = inputSelect.split(" ");
                double price = 0;
                for(int i = 0;i < select.length;i++){
                    price += prices[Integer.parseInt(select[i])-1];
                }
                MembersInfo membersInfo = new MembersInfo();
                Map<String, Member> members = membersInfo.getMembersInfo();
                System.out.println("            欢迎使用欧泉美发店会员管理系统      " +loginName);
                Frame frame = new Frame();
                frame.print();
                System.out.println();
                System.out.println("消费总额为："+price+"\t折扣为："+membersInfo.selectBarginById(id)+
                        "\t应收金额为："+price*members.get(id).getBargin());
                price = price*members.get(id).getBargin();
                System.out.println("是否确认？\ty.是\tn.否");
                frame.print();
                System.out.print("请输入您的选择：");
                String assure = scanner.next();
                if(assure.equals("y")){
                    MembersInfo membersInfo1 = new MembersInfo();
                        membersInfo1.addCost(id,price);
                        System.out.println("录入成功！正在返回...");
                        Thread.sleep(1000);
                        MainPage mainPage = new MainPage();
                        mainPage.mainPage(loginName);
                }else{
                    try {
                        System.out.println("您选择了“否”或输入内容不正确，正在返回...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } finally {
                        ServiceSelect serviceSelect = new ServiceSelect(id,loginName);
                        serviceSelect.serviceSelect();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("您的输入有误，请重新输入！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                ServiceSelect serviceSelect = new ServiceSelect(id,loginName);
                serviceSelect.serviceSelect();
            }

        }

    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        ServiceSelect.id = id;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        ServiceSelect.loginName = loginName;
    }

}
