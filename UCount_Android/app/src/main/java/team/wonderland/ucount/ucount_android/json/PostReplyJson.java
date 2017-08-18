package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 帖子回复信息
 * Created by CLL on 17/8/18.
 */
public class PostReplyJson implements Serializable {
    private String username;//用户名
    private Long replyId;//回帖id
    private String content;//内容
    private String time;//发布时间
    private int supportNum;//点赞数
    private Long postId;//原帖id

    public PostReplyJson(String username, Long replyId, String content, String time, int supportNum, Long postId) {
        this.username = username;
        this.replyId = replyId;
        this.content = content;
        this.time = time;
        this.supportNum = supportNum;
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
