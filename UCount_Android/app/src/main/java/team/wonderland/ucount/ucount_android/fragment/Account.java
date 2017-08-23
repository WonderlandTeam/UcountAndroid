package team.wonderland.ucount.ucount_android.fragment;

import java.io.Serializable;

/**
 * Created by liuyu on 2017/8/23.
 */

public class Account implements Serializable {
    private String name;
    private double total;
    private int imgId;

    public Account(String name, double total, int imgId) {
        this.name = name;
        this.total = total;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
