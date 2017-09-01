package team.wonderland.ucount.ucount_android.entity;

/**
 * 消息提醒
 * Created by CLL on 17/9/1.
 */
public class Message {
    private int imgId;
    private String header;
    private String context;
    private String time;

    public Message(int imgId, String header, String context,String time) {
        this.imgId = imgId;
        this.header = header;
        this.context = context;
        this.time=time;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
