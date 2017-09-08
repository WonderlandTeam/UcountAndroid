package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 预算信息
 * Created by CLL on 17/8/18.
 */
public class BudgetInfoJson implements Serializable{
    private Long id;
    private String username;
    private String consumeType;
    private double budgetMoney;
    private String bugdetTime;
    private double consume;
    private double remain;

    public BudgetInfoJson(){}

    public BudgetInfoJson(Long id, String username, String consumeType, double budgetMoney, String bugdetTime, double consume, double remain) {
        this.id = id;
        this.username = username;
        this.consumeType = consumeType;
        this.budgetMoney = budgetMoney;
        this.bugdetTime = bugdetTime;
        this.consume = consume;
        this.remain = remain;
    }


    public String toString(){
        String s=id+" "+username+" "+consumeType+" "+budgetMoney+" "+bugdetTime+" "+consume+" "+remain;
        return s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(double budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public String getBugdetTime() {
        return bugdetTime;
    }

    public void setBugdetTime(String bugdetTime) {
        this.bugdetTime = bugdetTime;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }

}
