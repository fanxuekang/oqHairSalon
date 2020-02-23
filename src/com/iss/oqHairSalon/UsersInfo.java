package com.iss.oqHairSalon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UsersInfo {
    private static Map<String, User> users = new HashMap<String, User>();

    public UsersInfo() {
//        User user1 = new User("admin", "123");
//        users.put("admin", user1);
        users = DbManager.getAllUsers();
    }

    public static boolean containsUser(String loginName) {
        return users.containsKey(loginName);
    }


    public static void managerUsersInfo(String loginName, String mode) {

        UsersInfo usersInfo = new UsersInfo();
        Frame frame = new Frame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
        frame.print();
        System.out.println();
        if ("1".equals(mode)) {
            System.out.println("请输入要删除的用户名，输入q返回上一级");
        } else {
            System.out.println("请输入要修改信息的用户名，输入q返回上一级");
        }
        System.out.println();
        frame.print();
        System.out.print("请输入内容：");
        String inputName = scanner.next();
        if (inputName.equals("q")) {//判断是返回还是继续输入密码
            System.out.println("正在返回上一级...");
            try {
                Thread.sleep(1000);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                UserManagerment userManagerment = new UserManagerment(loginName);
                userManagerment.userManagerment();
            }
        } else {
            if (isUser(inputName)) {

                if ("1".equals(mode)) {
                    System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                    frame.print();
                    System.out.println("是否确认删除？");
                    System.out.println("\t\t\ty：是\tn：否");
                    System.out.println();
                    frame.print();
                    System.out.print("请输入您的选择：");
                    String assuer = scanner.next();
                    switch (assuer) {
                        case "y": {
                            users.remove(inputName);
                            System.out.println("删除成功，正在返回...");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                UserManagerment userManagerment = new UserManagerment(loginName);
                                userManagerment.userManagerment();
                            }
                        }
                        break;
                        case "n": {
                            System.out.println("正在退出...");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                UserManagerment userManagerment = new UserManagerment(loginName);
                                userManagerment.userManagerment();
                            }
                        }
                        break;
                        default: {
                            System.out.println("您的输入有误，请重新输入！");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                usersInfo.managerUsersInfo(loginName, mode);//刷新当前页面
                            }
                        }
                    }
                } else {
                    System.out.println("            欢迎使用欧泉美发店会员管理系统      " + loginName);
                    frame.print();
                    System.out.println();
                    System.out.println("请输入新的用户信息");
                    System.out.println();
                    frame.print();
                    System.out.print("新用户名：");
                    String newInputName = scanner.next();
                    System.out.print("新密码：");
                    String newInputPassword1 = scanner.next();
                    System.out.print("新密码确认：");
                    String newInputPassword2 = scanner.next();
                    if (!newInputPassword1.equals(newInputPassword2)) {
                        System.out.println("两次密码不一致，请重新输入！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            usersInfo.managerUsersInfo(loginName, "2");//刷新当前页面
                        }
                    } else {
                        users.remove(inputName);
                        User user = new User(newInputName, newInputPassword1);
                        users.put(newInputName, user);
                        System.out.println("更新成功！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            UserManagerment userManagerment = new UserManagerment(loginName);
                            userManagerment.userManagerment();
                        }
                    }
                }
            } else {
                System.out.println("无此用户，请重新输入！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    UserManagerment userManagerment = new UserManagerment(loginName);//没有改用户就返回用户信息管理界面
                    userManagerment.userManagerment();
                }
            }
        }
    }


    public UsersInfo(String name, User user) {
        users.put(user.getLoginName(), user);
    }

    public String getPwdByName(String name) {
        return users.get(name).getLoginPassword();
    }

    public static boolean isUser(String loginName) {
        return users.containsKey(loginName);
    }

    public Map<String, User> getAllUsers() {
        return this.users;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
