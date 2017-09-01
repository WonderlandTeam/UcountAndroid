package team.wonderland.ucount.ucount_android.entity;

/**
 * 个人中心的帖子列表
 * Created by CLL on 17/9/1.
 */
public class MyPost {
    private String header;
    private String username;
    private String time;
    private int praiseNum;
    private int replyNum;

    public MyPost(String header, String username, String time, int praiseNum, int replyNum) {
        this.header = header;
        this.username = username;
        this.time = time;
        this.praiseNum = praiseNum;
        this.replyNum = replyNum;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }
}
