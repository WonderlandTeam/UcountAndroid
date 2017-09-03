package team.wonderland.ucount.ucount_android.entity;

/**
 * 现金流动
 * Created by CLL on 17/9/2.
 */
public class MoneyFlow {
    private boolean isIn;//true为收入，false为支出
    private double money;
    private String type;
    private String time;
    private int img_id;

    public MoneyFlow(boolean isIn, double money, String type, String time,int img_id) {
        this.isIn = isIn;
        this.money = money;
        this.type = type;
        this.time = time;
        this.img_id=img_id;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
