package team.wonderland.ucount.ucount_android.json;

/**
 * 攒钱计划修改信息（攒钱金额）
 * Created by CLL on 17/8/25.
 */
public class TaskModifyJson {
    double money;

    public TaskModifyJson(double money) {
        this.money = money;
    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
