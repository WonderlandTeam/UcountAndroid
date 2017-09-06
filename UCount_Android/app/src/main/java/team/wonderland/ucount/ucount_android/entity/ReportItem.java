package team.wonderland.ucount.ucount_android.entity;

/**
 * 收支报表类目
 * Created by CLL on 17/9/6.
 */
public class ReportItem {
    private int icon_id;
    private String type;
    private String percent;
    private double money;

    public ReportItem(int icon_id, String type, String percent, double money) {
        this.icon_id = icon_id;
        this.type = type;
        this.percent = percent;
        this.money = money;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
