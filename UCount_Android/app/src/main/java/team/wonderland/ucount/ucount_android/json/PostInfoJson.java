package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 帖子信息
 * Created by CLL on 17/8/18.
 */
public class PostInfoJson implements Serializable {
    private String username;//用户名
    private Long postId;//帖子id
    private String title;//主题
    private String content;//内容
    private String time;//发布时间
    private int supportNum;//点赞数

    public PostInfoJson(String username, Long postId, String title, String content, String time, int supportNum) {
        this.username = username;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.supportNum = supportNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
