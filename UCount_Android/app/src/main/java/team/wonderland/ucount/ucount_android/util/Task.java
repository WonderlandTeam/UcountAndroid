package team.wonderland.ucount.ucount_android.util;

/**
 * Created by liuyu on 2017/9/4.
 */

public class Task {
    private String name;
    private String endDate;
    private double target;
    private double have;
    private double daily;

    public Task(String name, String endDate, double target, double have, double daily) {
        this.name = name;
        this.endDate = endDate;
        this.target = target;
        this.have = have;
        this.daily = daily;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getHave() {
        return have;
    }

    public void setHave(double have) {
        this.have = have;
    }

    public double getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily = daily;
    }
}
