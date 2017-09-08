package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 预算修改信息
 * Created by CLL on 17/8/18.
 */
public class BudgetModifyJson implements Serializable {
    double money;

    public BudgetModifyJson(){}
    public BudgetModifyJson(double money) {
        this.money = money;
    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
