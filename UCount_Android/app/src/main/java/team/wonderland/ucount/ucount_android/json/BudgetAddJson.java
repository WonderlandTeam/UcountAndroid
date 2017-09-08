package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 预算添加信息
 * Created by CLL on 17/8/18.
 */
public class BudgetAddJson implements Serializable{
    private String username;
    private String consumeType;
    private double consumeMoney;
    private String consumeTime;

    public BudgetAddJson(){}

    public BudgetAddJson(String username, String consumeType, double consumeMoney, String consumeTime) {
        this.username = username;
        this.consumeType = consumeType;
        this.consumeMoney = consumeMoney;
        this.consumeTime = consumeTime;
    }

    public String toString(){
        String s=username+" "+consumeType+" "+consumeMoney+" "+consumeTime;
        return s;
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

    public double getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(double consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }
}
