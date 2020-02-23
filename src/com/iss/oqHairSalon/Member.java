package com.iss.oqHairSalon;

public class Member { //会员信息类
    private String id;
    private String name;
    private double cost;
    private String level;
    private double bargin;//存放相应的折扣

    public Member(){}
    public Member(String id,String name,double cost,String level,double bargin){
        setId(id);
        setName(name);
        setCost(cost);
        setLevel(level);
        setBargin(bargin);
    }
    public String getId() {
        return id;
    }


    public void updateMemberInfoByCost(int a,double cost){
        if(a == 1){
            cost += getCost();
            this.cost=cost;
        }else{
            this.cost = cost;
        }

        if(cost < 300){
            setLevel("三级会员");
        }else if(cost < 800){
            setLevel("二级会员");
            setBargin(0.9);
        }else if(cost < 1500){
            setLevel("一级会员");
            setBargin(0.85);
        }else {
            setLevel("特级会员");
            setBargin(0.8);
        }
    }


    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getBargin() {
        return bargin;
    }

    public void setBargin(double bargin) {
        this.bargin = bargin;
    }
}
