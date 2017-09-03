package team.wonderland.ucount.ucount_android.util;

import android.content.res.Resources;

import team.wonderland.ucount.ucount_android.activity.MainActivity;

/**
 * Created by liuyu on 2017/9/3.
 */

public class Budget {
    private int id;
    private String typename;
    private double num;
    private String srcName;

    public Budget(int id, String typename, double num,String srcName) {
        this.id = id;
        this.typename = typename;
        this.num = num;
        this.srcName = srcName;
    }

        public Budget(String srcName,String typename, double num) {
        this.typename = typename;
        this.num = num;
        this.srcName = srcName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    // TODO:返回图片资源的id
    public int getSrcId() {
        Resources resources = MainActivity.resources;
        return resources.getIdentifier(srcName, "drawable", MainActivity.PACKAGE_NAME);
    }
}
