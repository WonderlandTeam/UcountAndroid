package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 帖子回复信息
 * Created by CLL on 17/8/18.
 */
public class PostReplyJson implements Serializable {
    public Long replyId;
    public String username;
    public String content;
    public String time;
    public int supportNum;
    public boolean isSupported;

    public PostReplyJson(Long replyId, String username, String content, String time, int supportNum, boolean isSupported) {
        this.replyId = replyId;
        this.username = username;
        this.content = content;
        this.time = time;
        this.supportNum = supportNum;
        this.isSupported = isSupported;
    }

    @Override
    public String toString() {
        return "PostReplyJson{" +
                "replyId=" + replyId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", supportNum=" + supportNum +
                ", isSupported=" + isSupported +
                '}';
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }
}
