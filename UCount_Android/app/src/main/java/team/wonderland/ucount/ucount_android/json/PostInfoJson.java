package team.wonderland.ucount.ucount_android.json;

import java.io.Serializable;

/**
 * 帖子信息
 * Created by CLL on 17/8/18.
 */
public class PostInfoJson implements Serializable {

    public Long postId;                 // 帖子id
    public String username;             // 用户名
    public String title;                // 标题
    public String content;              // 内容
    public String time;                 // 时间
    public int supportNum;              // 点赞数
    public boolean isCollected;         // 用户是否收藏
    public boolean isSupported;         // 用户是否点赞

    public PostInfoJson() {
    }

    public PostInfoJson(Long postId, String username, String title, String content, String time, int supportNum, boolean isCollected, boolean isSupported) {
        this.postId = postId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.time = time;
        this.supportNum = supportNum;
        this.isCollected = isCollected;
        this.isSupported = isSupported;
    }

    @Override
    public String toString() {
        return "PostInfoJson{" +
                "postId=" + postId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", supportNum=" + supportNum +
                ", isCollected=" + isCollected +
                ", isSupported=" + isSupported +
                '}';
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

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }
}
