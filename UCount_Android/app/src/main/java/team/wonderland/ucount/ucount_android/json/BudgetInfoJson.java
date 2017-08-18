package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 预算信息
 * Created by CLL on 17/8/18.
 */
public class BudgetInfoJson implements Serializable{
    private Long budgetID;//ID
    private String username;//用户名
    private String consumeType;//消费类型
    private Double consumeMoney;//消费金额
    private Calendar consumeTime;//消费时间
    private Calendar createTime;//创建时间

    public Long getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(Long budgetID) {
        this.budgetID = budgetID;
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

    public Double getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Double consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Calendar getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Calendar consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
}
