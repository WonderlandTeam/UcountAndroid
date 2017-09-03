package team.wonderland.ucount.ucount_android.fragment;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetItem {
    private String date;
    private String type;
    private String num;
    private String comment;

    public AssetItem(String date, String type, String num) {
        this.date = date;
        this.type = type;
        this.num = num;
    }

    public AssetItem(String date, String type, String num, String comment) {
        this.date = date;
        this.type = type;
        this.num = num;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getNum() {
        return num;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
